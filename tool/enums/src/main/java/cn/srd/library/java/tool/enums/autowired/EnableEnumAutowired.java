// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.enums.autowired;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * enable {@link EnumAutowired} and specify the package paths to scan all enums marked with {@link EnumAutowired}
 *
 * @author wjm
 * @see EnumAutowired
 * @see EnumAutowiredSupport
 * @since 2021-09-08 16:07
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnableEnumAutowiredSwitcher.class)
public @interface EnableEnumAutowired {

    /**
     * the package paths to scan all enums marked with {@link EnumAutowired}
     *
     * @return the package paths to scan all enums marked with {@link EnumAutowired}
     */
    String[] scanPackagePaths() default {};

}
