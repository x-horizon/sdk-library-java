// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.mapstruct.utils;

import org.mapstruct.Qualifier;

import java.lang.annotation.*;

/**
 * Mapstruct 属性映射转换器注解，Enum =&gt; Enum 的数字字段值
 *
 * @author wjm
 * @since 2021-03-11 10:25
 */
@Qualifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface MapstructEnumToEnumCode {

}