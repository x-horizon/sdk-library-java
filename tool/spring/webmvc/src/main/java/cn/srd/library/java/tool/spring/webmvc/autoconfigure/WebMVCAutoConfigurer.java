// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.spring.webmvc.autoconfigure;

import cn.srd.library.java.tool.spring.webmvc.advice.WebMVCExceptionHandler;
import cn.srd.library.java.tool.spring.webmvc.advice.WebMVCExceptionHandlerSwitcher;
import cn.srd.library.java.tool.spring.webmvc.advice.WebMVCResponseBodyAdvice;
import cn.srd.library.java.tool.spring.webmvc.advice.WebMVCResponseBodyAdviceSwitcher;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Tool Spring WebMVC
 *
 * @author wjm
 * @since 2023-11-09 21:01
 */
@AutoConfiguration
public class WebMVCAutoConfigurer {

    @Bean
    @ConditionalOnBean(WebMVCResponseBodyAdviceSwitcher.class)
    public WebMVCResponseBodyAdvice webMVCResponseBodyAdvice() {
        return new WebMVCResponseBodyAdvice();
    }

    @Bean
    @ConditionalOnBean(WebMVCExceptionHandlerSwitcher.class)
    public WebMVCExceptionHandler webMVCExceptionHandler() {
        return new WebMVCExceptionHandler();
    }

}