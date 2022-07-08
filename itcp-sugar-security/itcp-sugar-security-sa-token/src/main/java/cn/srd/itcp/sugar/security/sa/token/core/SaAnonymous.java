package cn.srd.itcp.sugar.security.sa.token.core;

import cn.srd.itcp.sugar.security.sa.token.support.SaAnonymousSupporter;
import cn.srd.itcp.sugar.security.sa.token.support.SaConfig;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.lang.annotation.*;

/**
 * 匿名访问者：无需鉴权即可访问；
 * <pre>
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
