// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.tool;

import com.mybatisflex.core.util.LambdaGetter;

/**
 * @author wjm
 * @since 2023-12-03 23:39
 */
@FunctionalInterface
public interface ColumnNameGetter<T> extends LambdaGetter<T> {

}