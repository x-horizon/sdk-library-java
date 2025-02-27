package org.horizon.sdk.library.java.tool.spring.webflux.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * enable spring webflux exception interceptor
 *
 * @author wjm
 * @see WebFluxExceptionInterceptorRegistrar
 * @see WebFluxAutoConfigurer#webFluxExceptionInterceptor()
 * @since 2024-07-04 14:29
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(WebFluxExceptionInterceptorRegistrar.class)
public @interface EnableWebFluxExceptionInterceptor {

}