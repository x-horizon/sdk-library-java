// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.tool;

import cn.srd.library.java.orm.contract.model.base.PO;
import com.mybatisflex.core.util.LambdaGetter;

/**
 * @param <T> the entity extends {@link PO}
 * @author wjm
 * @since 2023-12-03 23:39
 */
@FunctionalInterface
public interface ColumnValueGetter<T extends PO> extends LambdaGetter<T> {

}