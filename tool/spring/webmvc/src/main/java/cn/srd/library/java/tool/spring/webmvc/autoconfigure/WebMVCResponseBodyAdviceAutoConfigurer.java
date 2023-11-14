// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.spring.webmvc.autoconfigure;

import cn.srd.library.java.tool.spring.webmvc.advice.WebMVCResponseBodyAdvice;
import cn.srd.library.java.tool.spring.webmvc.advice.WebMVCResponseBodyAdviceSwitcher;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Tool Spring WebMVC Response Body Advice
 *
 * @author wjm
 * @since 2023-11-09 21:01
 */
@AutoConfiguration
@ConditionalOnBean(WebMVCResponseBodyAdviceSwitcher.class)
public class WebMVCResponseBodyAdviceAutoConfigurer {

    @Bean
    public WebMVCResponseBodyAdvice webMVCResponseBodyAdvice() {
        return new WebMVCResponseBodyAdvice();
    }

}
