// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.spring.webmvc.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * enable webmvc response body advice
 *
 * @author wjm
 * @see WebMVCResponseBodyAdviceSwitcher
 * @see WebMVCAutoConfigurer#webMVCResponseBodyAdvice()
 * @since 2020-07-16 18:16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(WebMVCResponseBodyAdviceSwitcher.class)
public @interface EnableWebMVCResponseBodyAdvice {

    String[] advicePackagePaths() default {};

}