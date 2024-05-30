// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.enums.autoconfigure;

import cn.srd.library.java.tool.enums.EnumAutowired;
import cn.srd.library.java.tool.enums.strategy.EnumAutowiredCollector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * enable {@link EnumAutowired} and specify the package paths to scan all enums marked with {@link EnumAutowired}
 *
 * @author wjm
 * @see EnumAutowired
 * @see EnumAutowiredCollector
 * @since 2021-09-08 16:07
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnumAutowiredSwitcher.class)
public @interface EnableEnumAutowired {

    /**
     * the package paths to scan all enums marked with {@link EnumAutowired}
     *
     * @return the package paths to scan all enums marked with {@link EnumAutowired}
     */
    String[] scanPackagePaths() default {};

}