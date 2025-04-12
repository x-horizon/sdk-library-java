package org.horizon.sdk.library.java.tool.spring.webflux.interceptor;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import lombok.extern.slf4j.Slf4j;
import org.horizon.sdk.library.java.contract.constant.module.ModuleView;
import org.horizon.sdk.library.java.contract.constant.spring.SpringInitializeConstant;
import org.horizon.sdk.library.java.contract.constant.web.HttpStatus;
import org.horizon.sdk.library.java.contract.model.protocol.WebResponse;
import org.horizon.sdk.library.java.contract.model.throwable.*;
import org.horizon.sdk.library.java.tool.convert.api.Converts;
import org.horizon.sdk.library.java.tool.spring.contract.interceptor.WebExceptionInterceptor;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.resource.NoResourceFoundException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

import static org.horizon.sdk.library.java.contract.model.protocol.WebResponse.error;

/**
 * spring webflux exception interceptor
 *
 * @author wjm
 * @since 2024-07-04 11:51
 */
@Slf4j
@Order(SpringInitializeConstant.HIGH_INITIALIZE_PRIORITY)
public class WebFluxExceptionInterceptor extends WebExceptionInterceptor implements ErrorWebExceptionHandler {

    @Override
    protected String getModuleView() {
        return ModuleView.TOOL_SPRING_WEBFLUX_SYSTEM;
    }

    @Override
    @NonNull
    public Mono<Void> handle(ServerWebExchange exchange, Throwable throwable) {
        String requestUri = exchange.getRequest().getPath().toString();
        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(Converts.onJackson().toString(switch (throwable) {
                    case NoResourceFoundException exception -> whenNoResourceFoundException(requestUri, exception);
                    case UnrecognizedPropertyException exception -> whenUnrecognizedPropertyException(requestUri, exception);
                    case InvalidIdException ignore -> whenInvalidIdException(requestUri);
                    case InvalidArgumentException exception -> whenInvalidArgumentException(requestUri, exception);
                    case UnauthenticatedException exception -> whenUnauthenticatedException(requestUri, exception);
                    case UnauthorizedException exception -> whenUnauthorizedException(requestUri, exception);
                    case UnsupportedException ignore -> whenUnsupportedException(requestUri);
                    case DataNotFoundException exception -> whenDataNotFoundException(requestUri, exception);
                    case ClientException exception -> whenClientException(requestUri, exception);
                    case RunningException exception -> whenRunningException(requestUri, exception);
                    default -> whenThrowable(requestUri, throwable);
                }).getBytes(StandardCharsets.UTF_8)))
        );
    }

    /**
     * <p>handle the exception sample as following:</p>
     *
     * <ol>
     *   <li>
     *     define a controller with invalid mapping:
     *     <pre>{@code
     *     @RestController
     *     @RequestMapping("/foo")
     *     public class FooController {
     *
     *         @PostMapping("/sayHello")
     *         public void sayHello(@RequestBody FooVO fooVO) {
     *             System.out.println(fooVO);
     *         }
     *
     *     }
     *     }</pre>
     *   </li>
     *
     *   <li>
     *     send a POST request to {@code /foo/sayHello2} →
     *     throws {@link NoResourceFoundException} (no handler method found)
     *   </li>
     * </ol>
     *
     * @param uri       the HTTP request URI (e.g. {@code "/foo/sayHello2"})
     * @param exception the unresolved request exception
     * @return the web response
     */
    public WebResponse<Void> whenNoResourceFoundException(String uri, NoResourceFoundException exception) {
        log.warn(formatMessage(uri, exception.getReason()));
        return error(HttpStatus.NOT_FOUND, exception.getReason());
    }

}