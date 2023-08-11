package cn.srd.sugar.tool.spring.webflux.support;

import cn.srd.sugar.contract.throwable.core.RunningException;
import cn.srd.sugar.tool.lang.core.object.Objects;
import cn.srd.sugar.tool.lang.web.HttpStatusEnum;
import cn.srd.sugar.tool.lang.web.WebResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiFunction;

import static cn.srd.sugar.tool.lang.web.WebResponse.error;

/**
 * spring web flux global exception handler
 *
 * @author wjm
 * @since 2023-02-04 17:49:16
 */
@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE - 1)
public class WebFluxExceptionHandler implements ErrorWebExceptionHandler {

    /**
     * singleton object mapper
     */
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 异常处理器集合，有顺序要求，粒度需要从小到大
     */
    private static final Map<Class<? extends Throwable>, BiFunction<ServerWebExchange, Throwable, WebResponse<?>>> EXCEPTION_HANDLERS = new LinkedHashMap<>();

    /**
     * init
     */
    @PostConstruct
    public void init() {
        EXCEPTION_HANDLERS.put(ResponseStatusException.class, (exchange, throwable) -> {
            log.warn("请求资源地址：'{}'，错误信息：'{}'", exchange.getRequest().getPath(), throwable);
            return error(((ResponseStatusException) throwable).getStatusCode().value(), ((ResponseStatusException) throwable).getMessage());
        });
        EXCEPTION_HANDLERS.put(RunningException.class, (exchange, throwable) -> {
            log.warn("请求资源地址：'{}'，错误信息：'{}'", exchange.getRequest().getPath(), throwable);
            return error(((RunningException) throwable).getExceptionTemplate().getCode(), ((RunningException) throwable).getExceptionTemplate().getDescription());
        });
        EXCEPTION_HANDLERS.put(RuntimeException.class, (exchange, throwable) -> {
            log.error("请求资源地址：'{}'，错误信息：'{}'", exchange.getRequest().getPath(), throwable);
            return error(HttpStatusEnum.INTERNAL_ERROR);
        });
        EXCEPTION_HANDLERS.put(Exception.class, (exchange, throwable) -> {
            log.error("请求资源地址：'{}'，错误信息：'{}'", exchange.getRequest().getPath(), throwable);
            return error(HttpStatusEnum.INTERNAL_ERROR);
        });
        EXCEPTION_HANDLERS.put(Throwable.class, (exchange, throwable) -> {
            log.error("请求资源地址：'{}'，错误信息：'{}'", exchange.getRequest().getPath(), throwable);
            return error(HttpStatusEnum.INTERNAL_ERROR);
        });
        EXCEPTION_HANDLERS.put(NotRegisterHandlerException.class, (exchange, throwable) -> {
            log.error("请求资源地址：'{}'，错误信息：'cannot find handler to handle [{}], it may not be registered, please consider adding it, the detail error message is: {}'", exchange.getRequest().getPath(), throwable.getClass(), throwable);
            return error(HttpStatusEnum.INTERNAL_ERROR);
        });
    }

    /**
     * 匹配异常处理器用于处理异常
     *
     * @param exchange  the current exchange
     * @param throwable the exception to handle
     * @return common web response
     */
    private WebResponse<?> matchExceptionHandlerToHandle(ServerWebExchange exchange, Throwable throwable) {
        Class<? extends Throwable> throwableClassToMatch = throwable.getClass();
        for (Class<? extends Throwable> exceptionHandler : EXCEPTION_HANDLERS.keySet()) {
            if (exceptionHandler.isAssignableFrom(throwableClassToMatch)) {
                BiFunction<ServerWebExchange, Throwable, WebResponse<?>> exceptionHandle = EXCEPTION_HANDLERS.get(throwableClassToMatch);
                if (Objects.isNotNull(exceptionHandle)) {
                    return exceptionHandle.apply(exchange, throwable);
                }
            }
        }
        return EXCEPTION_HANDLERS.get(NotRegisterHandlerException.class).apply(exchange, throwable);
    }

    @NonNull
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, @NonNull Throwable throwable) {
        ServerHttpResponse response = exchange.getResponse();
        if (response.isCommitted()) {
            return Mono.error(throwable);
        }
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return response.writeWith(Mono.fromSupplier(() -> {
            try {
                return response.bufferFactory().wrap(OBJECT_MAPPER.writeValueAsBytes(matchExceptionHandlerToHandle(exchange, throwable)));
            } catch (JsonProcessingException exception) {
                throw new RuntimeException(exception);
            }
        }));
    }

}