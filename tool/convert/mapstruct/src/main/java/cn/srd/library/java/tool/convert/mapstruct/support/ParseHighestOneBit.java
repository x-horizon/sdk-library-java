// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.mapstruct.support;

import org.mapstruct.Qualifier;

import java.lang.annotation.*;

/**
 * 解析最高位
 *
 * @author xiongjing
 * @since 2022-11-14 09:38
 */
@Qualifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface ParseHighestOneBit {

}