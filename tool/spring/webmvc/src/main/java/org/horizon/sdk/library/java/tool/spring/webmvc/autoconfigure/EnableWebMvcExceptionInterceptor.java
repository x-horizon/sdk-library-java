package org.horizon.sdk.library.java.tool.spring.webmvc.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * enable spring webmvc exception interceptor
 *
 * @author wjm
 * @see WebMvcExceptionInterceptorRegistrar
 * @see WebMvcAutoConfigurer#webMVCExceptionInterceptor()
 * @since 2022-07-16 18:16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(WebMvcExceptionInterceptorRegistrar.class)
public @interface EnableWebMvcExceptionInterceptor {

}