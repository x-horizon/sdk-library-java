// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.spring.webmvc.autoconfigure;

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