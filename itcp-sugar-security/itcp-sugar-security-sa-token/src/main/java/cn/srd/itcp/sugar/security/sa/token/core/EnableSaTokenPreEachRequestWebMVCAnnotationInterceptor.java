package cn.srd.itcp.sugar.security.sa.token.core;

import cn.srd.itcp.sugar.security.sa.token.support.SaTokenPreEachRequestWebMVCAnnotationInterceptor;

import java.lang.annotation.*;

/**
 * 启用 {@link SaTokenPreEachRequestWebMVCAnnotationInterceptor} 的功能
 *
 * @author wjm
 * @since 2023-02-03 16:04:13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface EnableSaTokenPreEachRequestWebMVCAnnotationInterceptor {

}
