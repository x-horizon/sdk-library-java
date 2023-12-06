// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.update;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.mybatis.flex.base.query.QueryChainer;
import cn.srd.library.java.orm.mybatis.flex.base.query.QueryConditional;
import cn.srd.library.java.orm.mybatis.flex.base.tool.ColumnValueGetter;
import cn.srd.library.java.tool.lang.functional.If;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.update.UpdateChain;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

/**
 * @author wjm
 * @since 2023-12-05 23:25
 */
@AllArgsConstructor(access = AccessLevel.MODULE)
@SuppressWarnings(SuppressWarningConstant.UNUSED)
public class UpdateChainer<T extends PO> extends AbstractUpdateChainer<T> {

    @Getter(AccessLevel.PROTECTED) private final UpdateChain<T> nativeUpdateChainer;

    public static <T extends PO> UpdateChainer<T> of(BaseMapper<T> baseMapper) {
        return new UpdateChainer<>(UpdateChain.of(baseMapper));
    }

    public UpdateChainer<T> set(ColumnValueGetter<T> columnValueGetter, Object value) {
        return set(columnValueGetter, value, true);
    }

    public UpdateChainer<T> set(ColumnValueGetter<T> columnValueGetter, Object value, BooleanSupplier condition) {
        return set(columnValueGetter, value, condition.getAsBoolean());
    }

    public <U> UpdateChainer<T> set(ColumnValueGetter<T> columnValueGetter, U value, Predicate<U> condition) {
        return set(columnValueGetter, value, condition.test(value));
    }

    public UpdateChainer<T> set(ColumnValueGetter<T> columnValueGetter, Object value, boolean condition) {
        getNativeUpdateChainer().set(columnValueGetter, value, condition);
        return this;
    }

    public UpdateChainer<T> setIfNotNull(ColumnValueGetter<T> columnValueGetter, Object value) {
        return set(columnValueGetter, value, If::notNull);
    }

    public UpdateChainer<T> setIfNotZeroValue(ColumnValueGetter<T> columnValueGetter, Number value) {
        return set(columnValueGetter, value, If::notZeroValue);
    }

    public UpdateChainer<T> setIfNotEmpty(ColumnValueGetter<T> columnValueGetter, CharSequence value) {
        return set(columnValueGetter, value, If::notEmpty);
    }

    public UpdateChainer<T> setIfNotBlank(ColumnValueGetter<T> columnValueGetter, CharSequence value) {
        return set(columnValueGetter, value, If::notBlank);
    }

    public <U extends PO> UpdateChainer<T, QueryChainer<T>, QueryChain<T>> where(ColumnValueGetter<U> columnValueGetter) {
        return new QueryConditional<>(getNativeQueryChainer().where(columnValueGetter), this);
    }

    public <U extends PO> UpdateChainer<T, QueryChainer<T>, QueryChain<T>> and(ColumnValueGetter<U> columnValueGetter) {
        return new QueryConditional<>(getNativeQueryChainer().and(columnValueGetter), this);
    }

}
