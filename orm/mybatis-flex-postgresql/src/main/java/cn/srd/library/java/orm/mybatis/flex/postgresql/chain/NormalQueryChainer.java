// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.chain;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.contract.model.base.POJO;
import cn.srd.library.java.orm.mybatis.flex.base.chain.QueryChain;
import cn.srd.library.java.orm.mybatis.flex.base.chain.QueryChainer;
import cn.srd.library.java.orm.mybatis.flex.base.chain.QueryConditional;
import cn.srd.library.java.orm.mybatis.flex.base.chain.QueryJoiner;
import cn.srd.library.java.orm.mybatis.flex.base.tool.ColumnNameGetter;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.function.BooleanSupplier;

/**
 * @param <P> the entity extends {@link PO}
 * @author wjm
 * @since 2024-04-19 00:05
 */
@Getter(AccessLevel.PRIVATE)
@CanIgnoreReturnValue
public class NormalQueryChainer<P extends PO, C extends POJO> extends QueryChainer<P> {

    private final JsonbQueryChainer<C, P> jsonbQueryChainer;

    private final Class<P> poClass;

    public NormalQueryChainer(QueryChain<P> nativeQueryChainer, Class<P> poClass) {
        super(nativeQueryChainer);
        this.poClass = poClass;
        this.jsonbQueryChainer = new JsonbQueryChainer<>(this, poClass);
    }

    @Override
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <U extends PO> NormalQueryChainer<P, C> select(ColumnNameGetter<U>... columnNameGetters) {
        getNativeQueryChain().select(columnNameGetters);
        return this;
    }

    @Override
    public <U extends PO> QueryJoiner<P, NormalQueryChainer<P, C>> innerJoin(Class<U> entityClass) {
        return innerJoin(entityClass, true);
    }

    @Override
    public <U extends PO> QueryJoiner<P, NormalQueryChainer<P, C>> innerJoin(Class<U> entityClass, BooleanSupplier condition) {
        return innerJoin(entityClass, condition.getAsBoolean());
    }

    @Override
    public <U extends PO> QueryJoiner<P, NormalQueryChainer<P, C>> innerJoin(Class<U> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChain().innerJoin(entityClass, condition), this);
    }

    @Override
    public <U extends PO> QueryJoiner<P, NormalQueryChainer<P, C>> leftJoin(Class<U> entityClass) {
        return leftJoin(entityClass, true);
    }

    @Override
    public <U extends PO> QueryJoiner<P, NormalQueryChainer<P, C>> leftJoin(Class<U> entityClass, BooleanSupplier condition) {
        return leftJoin(entityClass, condition.getAsBoolean());
    }

    @Override
    public <U extends PO> QueryJoiner<P, NormalQueryChainer<P, C>> leftJoin(Class<U> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChain().leftJoin(entityClass, condition), this);
    }

    @Override
    public <U extends PO> QueryJoiner<P, NormalQueryChainer<P, C>> rightJoin(Class<U> entityClass) {
        return rightJoin(entityClass, true);
    }

    @Override
    public <U extends PO> QueryJoiner<P, NormalQueryChainer<P, C>> rightJoin(Class<U> entityClass, BooleanSupplier condition) {
        return rightJoin(entityClass, condition.getAsBoolean());
    }

    @Override
    public <U extends PO> QueryJoiner<P, NormalQueryChainer<P, C>> rightJoin(Class<U> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChain().rightJoin(entityClass, condition), this);
    }

    @Override
    public <U extends PO> QueryJoiner<P, NormalQueryChainer<P, C>> fullJoin(Class<U> entityClass) {
        return fullJoin(entityClass, true);
    }

    @Override
    public <U extends PO> QueryJoiner<P, NormalQueryChainer<P, C>> fullJoin(Class<U> entityClass, BooleanSupplier condition) {
        return fullJoin(entityClass, condition.getAsBoolean());
    }

    @Override
    public <U extends PO> QueryJoiner<P, NormalQueryChainer<P, C>> fullJoin(Class<U> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChain().fullJoin(entityClass, condition), this);
    }

    @Override
    public <U extends POJO> QueryConditional<NormalQueryChainer<P, C>, QueryChain<P>> where(ColumnNameGetter<U> columnNameGetter) {
        return new QueryConditional<>(getNativeQueryChain().where(columnNameGetter), this);
    }

    @Override
    public <U extends POJO> QueryConditional<NormalQueryChainer<P, C>, QueryChain<P>> and(ColumnNameGetter<U> columnNameGetter) {
        return new QueryConditional<>(getNativeQueryChain().and(columnNameGetter), this);
    }

    @Override
    public <U extends POJO> QueryConditional<NormalQueryChainer<P, C>, QueryChain<P>> or(ColumnNameGetter<U> columnNameGetter) {
        return new QueryConditional<>(getNativeQueryChain().or(columnNameGetter), this);
    }

    // @Override
    // public <U extends POJO> QueryConditional<NormalQueryChainer<P, C>, QueryChain<P>> where(ColumnNameGetter<U> columnNameGetter) {
    //     return new QueryConditional<>(getNativeQueryChain().where(getQueryColumn(columnNameGetter)), this);
    // }
    //
    // @Override
    // public <U extends POJO> QueryConditional<NormalQueryChainer<P, C>, QueryChain<P>> and(ColumnNameGetter<U> columnNameGetter) {
    //     return new QueryConditional<>(getNativeQueryChain().and(getQueryColumn(columnNameGetter)), this);
    // }
    //
    // @Override
    // public <U extends POJO> QueryConditional<NormalQueryChainer<P, C>, QueryChain<P>> or(ColumnNameGetter<U> columnNameGetter) {
    //     return new QueryConditional<>(getNativeQueryChain().or(getQueryColumn(columnNameGetter)), this);
    // }

    public JsonbQueryChainer<C, P> switchToJsonbQuery() {
        return getJsonbQueryChainer();
    }

    // private <U extends POJO> QueryColumn getQueryColumn(ColumnNameGetter<U> columnNameGetter) {
    //     QueryColumn queryColumn = MybatisFlexs.getQueryColumn(columnNameGetter);
    //     if (Nil.isNull(queryColumn)) {
    //         String jsonQueryFieldName = MybatisFlexs.getFieldName(columnNameGetter);
    //         String jsonQueryColumnName = ColumnJsonbMappingAliasCache.get(jsonQueryFieldName);
    //         Assert.of().setMessage("{}could not generate query column, please check!", ModuleView.ORM_MYBATIS_SYSTEM)
    //                 .setThrowable(LibraryJavaInternalException.class)
    //                 .throwsIfNull(jsonQueryColumnName);
    //         queryColumn = new QueryColumn();
    //         queryColumn.setName(jsonQueryColumnName);
    //         return queryColumn;
    //     }
    //     return queryColumn;
    // }

}