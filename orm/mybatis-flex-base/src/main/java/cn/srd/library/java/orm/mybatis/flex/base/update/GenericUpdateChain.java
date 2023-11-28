// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.update;

import cn.srd.library.java.orm.contract.model.base.PO;
import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.query.QueryWrapperAdapter;
import com.mybatisflex.core.update.PropertySetter;
import com.mybatisflex.core.util.LambdaGetter;

import java.io.Serial;

/**
 * @author wjm
 * @since 2023-11-28 22:57
 */
public class GenericUpdateChain<T extends PO> extends QueryWrapperAdapter<GenericUpdateChain<T>> implements PropertySetter<GenericUpdateChain<T>> {

    @Serial private static final long serialVersionUID = -1638361030106862568L;

    @Override
    public GenericUpdateChain<T> set(String property, Object value, boolean isEffective) {
        return null;
    }

    @Override
    public GenericUpdateChain<T> set(QueryColumn property, Object value, boolean isEffective) {
        return null;
    }

    @Override
    public <U> GenericUpdateChain<T> set(LambdaGetter<U> property, Object value, boolean isEffective) {
        return null;
    }

    @Override
    public GenericUpdateChain<T> setRaw(String property, Object value, boolean isEffective) {
        return null;
    }

    @Override
    public GenericUpdateChain<T> setRaw(QueryColumn property, Object value, boolean isEffective) {
        return null;
    }

    @Override
    public <U> GenericUpdateChain<T> setRaw(LambdaGetter<U> property, Object value, boolean isEffective) {
        return null;
    }

}
