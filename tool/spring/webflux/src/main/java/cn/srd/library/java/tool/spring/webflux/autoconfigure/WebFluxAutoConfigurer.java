// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.spring.webflux.autoconfigure;

import cn.srd.library.java.tool.spring.webflux.interceptor.WebFluxExceptionInterceptor;
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