// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.spring.webmvc;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * enable web mvc response body advice
 *
 * @author wjm
 * @since 2022-07-16 18:16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({WebMVCResponseBodyAdvice.class, ControllerAdvicePackagePathReplacer.class})
public @interface EnableWebMVCResponseBodyAdvice {

    String[] advicePackagePaths() default {};

}
