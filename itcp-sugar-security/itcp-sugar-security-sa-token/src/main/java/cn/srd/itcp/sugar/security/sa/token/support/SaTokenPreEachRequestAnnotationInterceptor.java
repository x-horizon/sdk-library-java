package cn.srd.itcp.sugar.security.sa.token.support;

import cn.dev33.satoken.strategy.SaStrategy;
import cn.srd.itcp.sugar.security.sa.token.common.core.*;
import cn.srd.itcp.sugar.security.sa.token.core.Anonymous;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * <pre>
 * 对标记了以下注解的方法或类，在每次请求之前触发的拦截器
 * {@link Anonymous Anonymous}
 * {@link RequireLogin RequireLogin}
 * {@link HasAllResourcePermissions HasAllPermissions}
 * {@link HasAnyResourcePermission HasAnyPermission}
 * {@link HasAllRoles HasAllRoles}
 * {@link HasAnyRole HasAnyRole}
 * </pre>
 *
 * @author wjm
 * @since 2022-07-16 18:16:22
 */
@Component
public class SaTokenPreEachRequestAnnotationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        SaStrategy.me.checkMethodAnnotation.accept(((HandlerMethod) handler).getMethod());
        return true;
    }

}
