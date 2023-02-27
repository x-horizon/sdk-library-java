package cn.srd.itcp.sugar.security.sa.token.webmvc.core;

import cn.srd.itcp.sugar.security.sa.token.webmvc.support.AnonymousSupporter;
import cn.srd.itcp.sugar.security.sa.token.webmvc.support.SaTokenWebMVCConfig;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.lang.annotation.*;

/**
 * <pre>
 *   该注解为：被该注解标记了的方法无需登录可进入，如：
 *
 *     &#064;{@link Anonymous}
 *     &#064;PostMapping
 *     public void test(Test test) {
 *     }
 *
 *   可标记在函数上；
 *   可标记在类上，效果等同于标记在此类的所有方法上；
 * </pre>
 *
 * @author wjm
 * @see AnonymousSupporter#afterPropertiesSet()
 * @see SaTokenWebMVCConfig#addInterceptors(InterceptorRegistry)
 * @since 2022-07-07
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Deprecated
public @interface Anonymous {
}
