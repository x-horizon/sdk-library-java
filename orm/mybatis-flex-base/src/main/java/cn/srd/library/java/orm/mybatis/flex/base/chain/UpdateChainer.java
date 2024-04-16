// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.chain;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.mybatis.flex.base.tool.ColumnValueGetter;
import cn.srd.library.java.tool.lang.functional.If;
import com.mybatisflex.core.BaseMapper;
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
public class UpdateChainer<P extends PO> extends BaseUpdateChainer<P> {

    @Getter(AccessLevel.PROTECTED) private final BaseMapper<P> nativeBaseMapper;

    @Getter(AccessLevel.PROTECTED) private final UpdateChain<P> nativeUpdateChainer;

    public static <P extends PO> UpdateChainer<P> of(BaseMapper<P> baseMapper) {
        return new UpdateChainer<>(baseMapper, UpdateChain.of(baseMapper));
    }

    public UpdateChainer<P> set(ColumnValueGetter<P> columnValueGetter, Object value) {
        return set(columnValueGetter, value, true);
    }

    public UpdateChainer<P> set(ColumnValueGetter<P> columnValueGetter, Object value, BooleanSupplier condition) {
        return set(columnValueGetter, value, condition.getAsBoolean());
    }

    public <U> UpdateChainer<P> set(ColumnValueGetter<P> columnValueGetter, U value, Predicate<U> condition) {
        return set(columnValueGetter, value, condition.test(value));
    }

    public UpdateChainer<P> set(ColumnValueGetter<P> columnValueGetter, Object value, boolean condition) {
        getNativeUpdateChainer().set(columnValueGetter, value, condition);
        return this;
    }

    public UpdateChainer<P> setIfNotNull(ColumnValueGetter<P> columnValueGetter, Object value) {
        return set(columnValueGetter, value, If::notNull);
    }

    public UpdateChainer<P> setIfNotZeroValue(ColumnValueGetter<P> columnValueGetter, Number value) {
        return set(columnValueGetter, value, If::notZeroValue);
    }

    public UpdateChainer<P> setIfNotEmpty(ColumnValueGetter<P> columnValueGetter, CharSequence value) {
        return set(columnValueGetter, value, If::notEmpty);
    }

    public UpdateChainer<P> setIfNotBlank(ColumnValueGetter<P> columnValueGetter, CharSequence value) {
        return set(columnValueGetter, value, If::notBlank);
    }

    public QueryConditional<P, UpdateChainer<P>, UpdateChain<P>> where(ColumnValueGetter<P> columnValueGetter) {
        return new QueryConditional<>(getNativeUpdateChainer().where(columnValueGetter), this);
    }

    public QueryConditional<P, UpdateChainer<P>, UpdateChain<P>> and(ColumnValueGetter<P> columnValueGetter) {
        return new QueryConditional<>(getNativeUpdateChainer().and(columnValueGetter), this);
    }

    public QueryConditional<P, UpdateChainer<P>, UpdateChain<P>> or(ColumnValueGetter<P> columnValueGetter) {
        return new QueryConditional<>(getNativeUpdateChainer().or(columnValueGetter), this);
    }

    public void update() {
        getNativeUpdateChainer().update();
    }

    public String toSQL() {
        return getNativeUpdateChainer().toSQL();
    }

}