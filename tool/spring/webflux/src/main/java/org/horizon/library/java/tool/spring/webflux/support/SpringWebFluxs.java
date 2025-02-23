package org.horizon.library.java.tool.spring.webflux.support;

import org.horizon.library.java.tool.spring.webflux.filter.ReactiveRequestContextFilter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Mono;

/**
 * toolkit for spring web flux
 *
 * @author wjm
 * @since 2023-02-04 17:49
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpringWebFluxs {

    /**
     * get {@link ServerHttpRequest}
     *
     * @return {@link ServerHttpRequest}
     */
    public static Mono<ServerHttpRequest> getServerHttpRequest() {
        return Mono.deferContextual(Mono::just).map(context -> context.get(ReactiveRequestContextFilter.CONTEXT_KEY));
    }

}