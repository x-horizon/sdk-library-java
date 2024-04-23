// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.chain;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.contract.model.base.POJO;
import cn.srd.library.java.orm.mybatis.flex.base.chain.QueryChain;
import cn.srd.library.java.orm.mybatis.flex.base.chain.QueryConditional;
import cn.srd.library.java.orm.mybatis.flex.base.tool.ColumnNameGetter;
import cn.srd.library.java.orm.mybatis.flex.base.tool.MybatisFlexs;
import cn.srd.library.java.orm.mybatis.flex.postgresql.cache.ColumnJsonbMappingAliasCache;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.text.Strings;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.query.RawQueryCondition;

/**
 * @param <P> the entity extends {@link PO}
 * @author wjm
 * @since 2024-04-19 00:05
 */
@CanIgnoreReturnValue
public class QueryChainer<P extends PO> extends cn.srd.library.java.orm.mybatis.flex.base.chain.QueryChainer<P> {

    protected QueryChainer(BaseMapper<P> nativeBaseMapper, QueryChain<P> nativeQueryChainer) {
        super(nativeBaseMapper, nativeQueryChainer);
    }

    public static <P extends PO> QueryChainer<P> of(BaseMapper<P> baseMapper) {
        return new QueryChainer<>(baseMapper, QueryChain.of(baseMapper));
    }

    @Override
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <U extends PO> QueryChainer<P> select(ColumnNameGetter<U>... columnNameGetters) {
        getNativeQueryChain().select(columnNameGetters);
        return this;
    }

    // public final <U extends PO> QueryChainer<P> innerJoinJsonbListView(ColumnNameGetter<U> columnNameGetter) {
    //     String columnName = MybatisFlexs.getColumnName(columnNameGetter);
    //     getNativeQueryChain()
    //             .innerJoin(new RawQueryTable(Strings.format("JSONB_ARRAY_ELEMENTS({})", columnName)))
    //             .as(Strings.format("{}_{}", columnName, SnowflakeIds.get()))
    //             .on("true");
    //     return this;
    // }

    @Override
    public <U extends POJO> QueryConditional<P, QueryChainer<P>, QueryChain<P>> where(ColumnNameGetter<U> columnNameGetter) {
        return new QueryConditional<>(getNativeQueryChain().where(getQueryColumn(columnNameGetter)), this);
    }

    public <U, R extends POJO> QueryConditional<P, QueryChainer<P>, QueryChain<P>> whereJsonQuery(ColumnNameGetter<U> columnNameGetter, ColumnNameGetter<R> jsonKeyNameGetter) {
        return new QueryConditional<>(getNativeQueryChain().where(new RawQueryCondition(buildJsonKeyToConditionSql(columnNameGetter, jsonKeyNameGetter))), this);
    }

    @Override
    public <U extends POJO> QueryConditional<P, QueryChainer<P>, QueryChain<P>> and(ColumnNameGetter<U> columnNameGetter) {
        return new QueryConditional<>(getNativeQueryChain().and(getQueryColumn(columnNameGetter)), this);
    }

    public <U, R extends POJO> QueryConditional<P, QueryChainer<P>, QueryChain<P>> andJsonQuery(ColumnNameGetter<U> columnNameGetter, ColumnNameGetter<R> jsonKeyNameGetter) {
        return new QueryConditional<>(getNativeQueryChain().and(new RawQueryCondition(buildJsonKeyToConditionSql(columnNameGetter, jsonKeyNameGetter))), this);
    }

    @Override
    public <U extends POJO> QueryConditional<P, QueryChainer<P>, QueryChain<P>> or(ColumnNameGetter<U> columnNameGetter) {
        return new QueryConditional<>(getNativeQueryChain().or(getQueryColumn(columnNameGetter)), this);
    }

    public <U, R extends POJO> QueryConditional<P, QueryChainer<P>, QueryChain<P>> orJsonQuery(ColumnNameGetter<U> columnNameGetter, ColumnNameGetter<R> jsonKeyNameGetter) {
        return new QueryConditional<>(getNativeQueryChain().or(new RawQueryCondition(buildJsonKeyToConditionSql(columnNameGetter, jsonKeyNameGetter))), this);
    }

    private <U extends POJO> QueryColumn getQueryColumn(ColumnNameGetter<U> columnNameGetter) {
        QueryColumn queryColumn = MybatisFlexs.getQueryColumn(columnNameGetter);
        if (Nil.isNull(queryColumn)) {
            String jsonQueryFieldName = MybatisFlexs.getFieldName(columnNameGetter);
            String jsonQueryColumnName = ColumnJsonbMappingAliasCache.get(jsonQueryFieldName);
            Assert.of().setMessage("{}could not generate query column, please check!", ModuleView.ORM_MYBATIS_SYSTEM)
                    .setThrowable(LibraryJavaInternalException.class)
                    .throwsIfNull(jsonQueryColumnName);
            queryColumn = new QueryColumn();
            queryColumn.setName(jsonQueryColumnName);
            return queryColumn;
        }
        return queryColumn;
    }

    private <U, R extends POJO> String buildJsonKeyToConditionSql(ColumnNameGetter<U> columnNameGetter, ColumnNameGetter<R> jsonKeyNameGetter) {
        String jsonQueryFieldName = MybatisFlexs.getFieldName(columnNameGetter);
        String jsonQueryColumnName = ColumnJsonbMappingAliasCache.get(jsonQueryFieldName);
        Assert.of().setMessage("{}could not find the json query column name by [{}], please check!", ModuleView.ORM_MYBATIS_SYSTEM, jsonQueryFieldName)
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfNull(jsonQueryColumnName);
        String jsonKeyName = MybatisFlexs.getFieldName(jsonKeyNameGetter);
        return Strings.format("\"{}\" ->> '{}'::VARCHAR", jsonQueryColumnName, jsonKeyName);
    }

}