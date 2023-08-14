package cn.srd.library.java.security.sa.token.spring.webmvc.core;

import cn.srd.library.java.security.sa.token.spring.webmvc.support.SaTokenPreEachRequestWebMVCAnnotationInterceptor;
import org.springframework.context.annotation.Import;

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
@Import(SaTokenPreEachRequestWebMVCAnnotationInterceptor.class)
public @interface EnableSaTokenPreEachRequestWebMVCAnnotationInterceptor {

}
