// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.mapstruct.support;

import org.mapstruct.Qualifier;

import java.lang.annotation.*;

/**
 * Mapstruct 属性映射注解标记，标记了该注解的类可以作为 Mapstruct 中的转换方法
 *
 * @author wjm
 * @since 2021-03-11 10:25
 */
@Qualifier
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface MapstructMappingQualify {

}