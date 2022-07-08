package cn.srd.itcp.sugar.security.sa.token.core;

import cn.srd.itcp.sugar.security.sa.token.support.SaAnonymousSupporter;
import cn.srd.itcp.sugar.security.sa.token.support.SaConfig;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.lang.annotation.*;

/**
 * <pre>
 *   该注解为：被该注解标记了的方法无需鉴权，如：
 *
 *     &#064;{@link SaAnonymous}
 *     &#064;PostMapping
 *     public void test(Test test) {
 *     }
 *
 *   可标记在函数上；
 *   可标记在类上，效果等同于标记在此类的所有方法上；
 * </pre>
 *
 * @author wjm
 * @date 2022-07-07
 * @see SaAnonymousSupporter
 * @see SaConfig#addInterceptors(InterceptorRegistry)
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SaAnonymous {
}
