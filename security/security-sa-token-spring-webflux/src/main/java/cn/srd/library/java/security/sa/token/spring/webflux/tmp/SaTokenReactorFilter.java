package cn.srd.library.java.security.sa.token.spring.webflux.tmp;

import cn.dev33.satoken.exception.BackResultException;
import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.exception.StopMatchException;
import cn.dev33.satoken.filter.SaFilterAuthStrategy;
import cn.dev33.satoken.filter.SaFilterErrorStrategy;
import cn.dev33.satoken.reactor.context.SaReactorHolder;
import cn.dev33.satoken.reactor.context.SaReactorSyncHolder;
import cn.dev33.satoken.reactor.error.SaReactorSpringBootErrorCode;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.util.SaTokenConsts;
import org.springframework.core.annotation.Order;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TODO wjm copy from {@link cn.dev33.satoken.reactor.filter.SaReactorFilter}，copy reason please see code line 201，后续官方版本支持后去掉
 *
 * @author kong
 */
@Order(SaTokenConsts.ASSEMBLY_ORDER)
public class SaTokenReactorFilter implements WebFilter {

    // ------------------------ 设置此过滤器 拦截 & 放行 的路由

    /**
     * 拦截路由
     */
    private List<String> includeList = new ArrayList<>();

    /**
     * 放行路由
     */
    private List<String> excludeList = new ArrayList<>();

    /**
     * 添加 [拦截路由]
     *
     * @param paths 路由
     * @return 对象自身
     */
    public SaTokenReactorFilter addInclude(String... paths) {
        includeList.addAll(Arrays.asList(paths));
        return this;
    }

    /**
     * 添加 [放行路由]
     *
     * @param paths 路由
     * @return 对象自身
     */
    public SaTokenReactorFilter addExclude(String... paths) {
        excludeList.addAll(Arrays.asList(paths));
        return this;
    }

    /**
     * 写入 [拦截路由] 集合
     *
     * @param pathList 路由集合
     * @return 对象自身
     */
    public SaTokenReactorFilter setIncludeList(List<String> pathList) {
        includeList = pathList;
        return this;
    }

    /**
     * 写入 [放行路由] 集合
     *
     * @param pathList 路由集合
     * @return 对象自身
     */
    public SaTokenReactorFilter setExcludeList(List<String> pathList) {
        excludeList = pathList;
        return this;
    }

    /**
     * 获取 [拦截路由] 集合
     *
     * @return see note
     */
    public List<String> getIncludeList() {
        return includeList;
    }

    /**
     * 获取 [放行路由] 集合
     *
     * @return see note
     */
    public List<String> getExcludeList() {
        return excludeList;
    }

    // ------------------------ 钩子函数

    /**
     * 认证函数：每次请求执行
     */
    public SaFilterAuthStrategy auth = r -> {
    };

    /**
     * 异常处理函数：每次[认证函数]发生异常时执行此函数
     */
    public SaFilterErrorStrategy error = e -> {
        throw new SaTokenException(e).setCode(SaReactorSpringBootErrorCode.CODE_20205);
    };

    /**
     * 前置函数：在每次[认证函数]之前执行
     */
    public SaFilterAuthStrategy beforeAuth = r -> {
    };

    /**
     * 写入[认证函数]: 每次请求执行
     *
     * @param auth see note
     * @return 对象自身
     */
    public SaTokenReactorFilter setAuth(SaFilterAuthStrategy auth) {
        this.auth = auth;
        return this;
    }

    /**
     * 写入[异常处理函数]：每次[认证函数]发生异常时执行此函数
     *
     * @param error see note
     * @return 对象自身
     */
    public SaTokenReactorFilter setError(SaFilterErrorStrategy error) {
        this.error = error;
        return this;
    }

    /**
     * 写入[前置函数]：在每次[认证函数]之前执行
     *
     * @param beforeAuth see note
     * @return 对象自身
     */
    public SaTokenReactorFilter setBeforeAuth(SaFilterAuthStrategy beforeAuth) {
        this.beforeAuth = beforeAuth;
        return this;
    }

    // ------------------------ filter

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        // 写入WebFilterChain对象
        exchange.getAttributes().put(SaReactorHolder.CHAIN_KEY, chain);

        // ---------- 全局认证处理
        try {
            // 写入全局上下文 (同步)
            SaReactorSyncHolder.setContext(exchange);

            // 执行全局过滤器
            SaRouter.match(includeList).notMatch(excludeList).check(r -> {
                beforeAuth.run(null);
                auth.run(null);
            });

        } catch (StopMatchException e) {

        } catch (Throwable e) {
            // 1. 获取异常处理策略结果
            String result = (e instanceof BackResultException) ? e.getMessage() : String.valueOf(error.run(e));

            // 2. 写入输出流
            if (exchange.getResponse().getHeaders().getFirst("Content-Type") == null) {
                exchange.getResponse().getHeaders().set("Content-Type", "text/plain; charset=utf-8");
            }
            return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(result.getBytes())));

        } finally {
            // 清除上下文
            SaReactorSyncHolder.clearContext();
        }

        // ---------- 执行

        // 写入全局上下文 (同步)
        SaReactorSyncHolder.setContext(exchange);

        /**
         * TODO wjm 20230204:
         * TODO wjm 1、update "chain.filter(exchange).subscriberContext" => "chain.filter(exchange).contextWrite"
         * TODO wjm 2、reactor-core 包在 v3.5.0 版本删除了已被弃用的 subscriberContext 函数，详见：https://github.com/reactor/reactor-core/releases/tag/v3.5.0
         */
        // 执行
        return chain.filter(exchange).contextWrite(ctx -> {
            // 写入全局上下文 (异步)
            ctx = ctx.put(SaReactorHolder.CONTEXT_KEY, exchange);
            return ctx;
        }).doFinally(r -> {
            // 清除上下文
            SaReactorSyncHolder.clearContext();
        });
    }

}
