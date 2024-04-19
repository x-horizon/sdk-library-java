// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.chain;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.mybatis.flex.base.tool.ColumnNameGetter;
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

    public UpdateChainer<P> set(ColumnNameGetter<P> columnNameGetter, Object value) {
        return set(columnNameGetter, value, true);
    }

    public UpdateChainer<P> set(ColumnNameGetter<P> columnNameGetter, Object value, BooleanSupplier condition) {
        return set(columnNameGetter, value, condition.getAsBoolean());
    }

    public <U> UpdateChainer<P> set(ColumnNameGetter<P> columnNameGetter, U value, Predicate<U> condition) {
        return set(columnNameGetter, value, condition.test(value));
    }

    public UpdateChainer<P> set(ColumnNameGetter<P> columnNameGetter, Object value, boolean condition) {
        getNativeUpdateChainer().set(columnNameGetter, value, condition);
        return this;
    }

    public UpdateChainer<P> setIfNotNull(ColumnNameGetter<P> columnNameGetter, Object value) {
        return set(columnNameGetter, value, If::notNull);
    }

    public UpdateChainer<P> setIfNotZeroValue(ColumnNameGetter<P> columnNameGetter, Number value) {
        return set(columnNameGetter, value, If::notZeroValue);
    }

    public UpdateChainer<P> setIfNotEmpty(ColumnNameGetter<P> columnNameGetter, CharSequence value) {
        return set(columnNameGetter, value, If::notEmpty);
    }

    public UpdateChainer<P> setIfNotBlank(ColumnNameGetter<P> columnNameGetter, CharSequence value) {
        return set(columnNameGetter, value, If::notBlank);
    }

    public QueryConditional<P, UpdateChainer<P>, UpdateChain<P>> where(ColumnNameGetter<P> columnNameGetter) {
        return new QueryConditional<>(getNativeUpdateChainer().where(columnNameGetter), this);
    }

    public QueryConditional<P, UpdateChainer<P>, UpdateChain<P>> and(ColumnNameGetter<P> columnNameGetter) {
        return new QueryConditional<>(getNativeUpdateChainer().and(columnNameGetter), this);
    }

    public QueryConditional<P, UpdateChainer<P>, UpdateChain<P>> or(ColumnNameGetter<P> columnNameGetter) {
        return new QueryConditional<>(getNativeUpdateChainer().or(columnNameGetter), this);
    }

    public void update() {
        getNativeUpdateChainer().update();
    }

    public String toSQL() {
        return getNativeUpdateChainer().toSQL();
    }

}