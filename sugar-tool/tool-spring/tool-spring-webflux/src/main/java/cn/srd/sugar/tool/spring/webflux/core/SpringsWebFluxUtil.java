package cn.srd.sugar.tool.spring.webflux.core;

import cn.srd.sugar.tool.spring.webflux.support.ReactiveRequestContextFilter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Mono;

/**
 * Spring WebFlux 工具
 *
 * @author wjm
 * @since 2023-02-04 17:49:16
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpringsWebFluxUtil {

    /**
     * 获取请求对象
     *
     * @return 请求对象
     */
    public static Mono<ServerHttpRequest> getServerHttpRequest() {
        return Mono.deferContextual(Mono::just).map(context -> context.get(ReactiveRequestContextFilter.CONTEXT_KEY));
    }

}
