package cn.srd.library.java.tool.spring.webflux.support;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * Reactive 请求上下文拦截器
 *
 * @author wjm
 * @since 2023-02-04 17:49:16
 */
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
public class ReactiveRequestContextFilter implements WebFilter {

    /**
     * 用于缓存请求上下文的 key
     */
    public static final Class<ServerHttpRequest> CONTEXT_KEY = ServerHttpRequest.class;

    /**
     * 拦截请求并将其存入上下文，方便从任意 web 上下文中获取
     *
     * @param exchange the current server exchange
     * @param chain    provides a way to delegate to the next filter
     * @return Mono
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        return chain.filter(exchange).contextWrite(context -> context.put(CONTEXT_KEY, request));
    }

}