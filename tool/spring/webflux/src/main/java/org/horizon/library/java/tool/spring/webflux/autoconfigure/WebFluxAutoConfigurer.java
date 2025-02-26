package org.horizon.library.java.tool.spring.webflux.autoconfigure;

import org.horizon.library.java.tool.spring.webflux.interceptor.WebFluxExceptionInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Tool Spring WebFlux
 *
 * @author wjm
 * @since 2024-07-04 14:30
 */
@AutoConfiguration
public class WebFluxAutoConfigurer {

    @Bean
    @ConditionalOnBean(WebFluxExceptionInterceptorRegistrar.class)
    public WebFluxExceptionInterceptor webFluxExceptionInterceptor() {
        return new WebFluxExceptionInterceptor();
    }

}