package cn.srd.itcp.sugar.security.sa.token.reactor.support;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.srd.itcp.sugar.tool.core.Objects;
import cn.srd.itcp.sugar.tool.core.StringsUtil;
import cn.srd.itcp.sugar.tool.exceptions.RunningException;
import cn.srd.itcp.sugar.tool.web.HttpStatusEnum;
import cn.srd.itcp.sugar.tool.web.WebResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiFunction;

import static cn.srd.itcp.sugar.tool.web.WebResponse.error;

/**
 * spring web reactor sa-token 全局异常处理器  TODO wjm 必须使用 @Import 引入 只作用在webflux 环境下 , 优先级低于 {@link ResponseStatusExceptionHandler} 执行
 *
 * @author wjm
 * @since 2023-02-04 17:49:16
 */
@Slf4j
@Order(-1)
// @Configuration
public class SaTokenWebReactorExceptionHandler implements ErrorWebExceptionHandler {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * TODO wjm 泛型实现得不够好，该类代码后续待优化
     */
    public static final Map<Class<? extends Throwable>, BiFunction<ServerWebExchange, Throwable, WebResponse<?>>> SPECIFIC_EXCEPTION_HANDLERS = new HashMap<>();

    /**
     * 兜底处理，要按顺序添加
     */
    public static final Map<Class<? extends Throwable>, BiFunction<ServerWebExchange, Throwable, WebResponse<?>>> FINAL_EXCEPTION_HANDLERS = new LinkedHashMap<>();

    /**
     * TODO wjm 需分拆到其他模块中
     */
    @PostConstruct
    public void init() {
        SPECIFIC_EXCEPTION_HANDLERS.put(NotLoginException.class, (exchange, throwable) -> {
            log.warn("请求资源地址：'{}'，错误信息：未能通过登录认证", exchange.getRequest().getPath());
            return error(HttpStatusEnum.NOT_LOGIN, "未能通过登录认证");
        });
        SPECIFIC_EXCEPTION_HANDLERS.put(NotPermissionException.class, (exchange, throwable) -> {
            log.warn("请求资源地址：'{}'，错误信息：未能通过权限认证", exchange.getRequest().getPath());
            return error(HttpStatusEnum.NOT_PERMISSION, "未能通过权限认证");
        });
        SPECIFIC_EXCEPTION_HANDLERS.put(NotRoleException.class, (exchange, throwable) -> {
            log.warn("请求资源地址：'{}'，错误信息：未能通过角色认证", exchange.getRequest().getPath());
            return error(HttpStatusEnum.NOT_ROLE, "未能通过角色认证");
        });

        FINAL_EXCEPTION_HANDLERS.put(RunningException.class, (exchange, throwable) -> {
            log.warn("请求资源地址：'{}'，错误信息：'{}'", exchange.getRequest().getPath(), throwable);
            return error(((RunningException) throwable).getExceptionTemplate().getCode(), ((RunningException) throwable).getExceptionTemplate().getDescription());
        });

        FINAL_EXCEPTION_HANDLERS.put(RuntimeException.class, (exchange, throwable) -> {
            log.error("请求资源地址：'{}'，错误信息：'{}'", exchange.getRequest().getPath(), throwable);
            return error(HttpStatusEnum.INTERNAL_ERROR);
        });

        FINAL_EXCEPTION_HANDLERS.put(Exception.class, (exchange, throwable) -> {
            log.error("请求资源地址：'{}'，错误信息：'{}'", exchange.getRequest().getPath(), throwable);
            return error(HttpStatusEnum.INTERNAL_ERROR);
        });

        FINAL_EXCEPTION_HANDLERS.put(Throwable.class, (exchange, throwable) -> {
            log.error("请求资源地址：'{}'，错误信息：'{}'", exchange.getRequest().getPath(), throwable);
            return error(HttpStatusEnum.INTERNAL_ERROR);
        });
    }

    private WebResponse<?> match(ServerWebExchange exchange, Throwable throwable) {
        Throwable throwableToMatch = throwable;
        Class<? extends Throwable> throwableClassToMatch = throwableToMatch.getClass();
        for (Class<? extends Throwable> specificExceptionClass : SPECIFIC_EXCEPTION_HANDLERS.keySet()) {
            if (throwableClassToMatch == specificExceptionClass) {
                return SPECIFIC_EXCEPTION_HANDLERS.get(throwableClassToMatch).apply(exchange, throwableToMatch);
            }
            throwableToMatch = throwableToMatch.getCause();
            while (Objects.isNotNull(throwableToMatch)) {
                throwableClassToMatch = throwableToMatch.getClass();
                if (throwableClassToMatch == specificExceptionClass) {
                    return SPECIFIC_EXCEPTION_HANDLERS.get(throwableClassToMatch).apply(exchange, throwableToMatch);
                }
                throwableToMatch = throwableToMatch.getCause();
            }
        }

        throwableToMatch = throwable;
        throwableClassToMatch = throwableToMatch.getClass();
        for (Class<? extends Throwable> finalExceptionClass : FINAL_EXCEPTION_HANDLERS.keySet()) {
            if (finalExceptionClass.isAssignableFrom(throwableClassToMatch)) {
                return FINAL_EXCEPTION_HANDLERS.get(throwableClassToMatch).apply(exchange, throwableToMatch);
            }
        }

        throw new RuntimeException(StringsUtil.format("web reactor exception handler: cannot find handler to handle [{}], it may not be registered, please check!", throwable.getClass()));
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
                return response.bufferFactory().wrap(objectMapper.writeValueAsBytes(match(exchange, throwable)));
            } catch (JsonProcessingException exception) {
                throw new RuntimeException(exception);
            }
        }));
    }

}