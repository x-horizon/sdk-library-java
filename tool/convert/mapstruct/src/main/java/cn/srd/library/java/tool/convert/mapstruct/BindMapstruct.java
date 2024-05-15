// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.mapstruct;

import java.lang.annotation.*;

/**
 * 用于标记哪些是 Mapstruct 类
 *
 * @author wjm
 * @since 2021-05-01 14:13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Deprecated
public @interface BindMapstruct {

}