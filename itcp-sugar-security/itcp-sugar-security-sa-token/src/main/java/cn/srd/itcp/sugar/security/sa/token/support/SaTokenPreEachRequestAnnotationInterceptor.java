package cn.srd.itcp.sugar.security.sa.token.support;

import cn.dev33.satoken.strategy.SaStrategy;
import cn.srd.itcp.sugar.security.sa.token.core.HasAllResourcePermissions;
import cn.srd.itcp.sugar.security.sa.token.core.HasAnyResourcePermission;
import cn.srd.itcp.sugar.tools.core.Objects;
import cn.srd.itcp.sugar.tools.core.SpringsUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * 对标记了以下注解的方法或类，在每次请求之前触发的拦截器
 * {@link cn.srd.itcp.sugar.security.sa.token.core.Anonymous Anonymous}
 * {@link cn.srd.itcp.sugar.security.sa.token.core.RequireLogin RequireLogin}
 * {@link HasAllResourcePermissions HasAllPermissions}
 * {@link HasAnyResourcePermission HasAnyPermission}
 * {@link cn.srd.itcp.sugar.security.sa.token.core.HasAllRoles HasAllRoles}
 * {@link cn.srd.itcp.sugar.security.sa.token.core.HasAnyRole HasAnyRole}
 * </pre>
 *
 * @author wjm
 * @date 2022-07-16 18:16:22
 */
@Component
public class SaTokenPreEachRequestAnnotationInterceptor implements HandlerInterceptor {

    private SugarSaTokenProperties sugarSaTokenProperties;

    @PostConstruct
    private void init() {
        sugarSaTokenProperties = SpringsUtil.getBean(SugarSaTokenProperties.class);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 未配置拦截器 或 显式启用了拦截器，则开启拦截
        if (Objects.isNull(sugarSaTokenProperties) || Objects.isTrue(sugarSaTokenProperties.getInterceptor().getEnable())) {
            if (!(handler instanceof HandlerMethod)) {
                return true;
            }
            SaStrategy.me.checkMethodAnnotation.accept(((HandlerMethod) handler).getMethod());
            return true;
        }
        return true;
    }

}
