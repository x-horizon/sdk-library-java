// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.chain;

import cn.srd.library.java.contract.constant.booleans.BooleanConstant;
import cn.srd.library.java.contract.constant.database.PostgresqlFunction;
import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.contract.model.base.POJO;
import cn.srd.library.java.orm.mybatis.flex.base.chain.BaseQueryChainer;
import cn.srd.library.java.orm.mybatis.flex.base.chain.QueryChain;
import cn.srd.library.java.orm.mybatis.flex.base.tool.ColumnNameGetter;
import cn.srd.library.java.orm.mybatis.flex.base.tool.MybatisFlexs;
import cn.srd.library.java.orm.mybatis.flex.postgresql.cache.ColumnJsonbMappingAliasCache;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.reflect.Reflects;
import cn.srd.library.java.tool.lang.text.Strings;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.mybatisflex.core.constant.SqlConnector;
import com.mybatisflex.core.query.RawQueryTable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author wjm
 * @since 2024-04-23 19:48
 */
@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PRIVATE)
@CanIgnoreReturnValue
public class JsonbQueryChainer<P extends POJO, U extends PO> extends BaseQueryChainer<P> {

    private final NormalQueryChainer<U, P> normalQueryChainer;

    private final QueryChain<P> nativeQueryChain;

    private final Class<U> poClass;

    private String jsonbFunctionSQL;

    private String theLastJsonKeyName;

    private ColumnNameGetter<P> theLastJsonKeyNameGetter;

    public JsonbQueryChainer(NormalQueryChainer<U, P> normalQueryChainer, Class<U> poClass) {
        this.normalQueryChainer = normalQueryChainer;
        this.nativeQueryChain = Reflects.getFieldValue(normalQueryChainer, "nativeQueryChain");
        this.poClass = poClass;
    }

    public <C extends POJO, V extends POJO> JsonbQueryCaster<P, C, V, U> where(ColumnNameGetter<C> columnNameGetter) {
        return and(columnNameGetter);
    }

    public <C extends POJO, V extends POJO> JsonbQueryCaster<P, C, V, U> where(ColumnNameGetter<C> columnNameGetter, ColumnNameGetter<V> jsonKeyNameGetter) {
        return and(columnNameGetter, jsonKeyNameGetter);
    }

    public <C extends POJO, V extends POJO> JsonbQueryCaster<P, C, V, U> and(ColumnNameGetter<C> columnNameGetter) {
        return new JsonbQueryCaster<>(SqlConnector.AND, this, getNativeQueryChain(), columnNameGetter);
    }

    public <C extends POJO, V extends POJO> JsonbQueryCaster<P, C, V, U> and(ColumnNameGetter<C> columnNameGetter, ColumnNameGetter<V> jsonKeyNameGetter) {
        return new JsonbQueryCaster<>(SqlConnector.AND, this, getNativeQueryChain(), columnNameGetter, jsonKeyNameGetter);
    }

    public <C extends POJO, V extends POJO> JsonbQueryCaster<P, C, V, U> or(ColumnNameGetter<C> columnNameGetter) {
        return new JsonbQueryCaster<>(SqlConnector.OR, this, getNativeQueryChain(), columnNameGetter);
    }

    public <C extends POJO, V extends POJO> JsonbQueryCaster<P, C, V, U> or(ColumnNameGetter<C> columnNameGetter, ColumnNameGetter<V> jsonKeyNameGetter) {
        return new JsonbQueryCaster<>(SqlConnector.OR, this, getNativeQueryChain(), columnNameGetter, jsonKeyNameGetter);
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <C, V extends POJO> JsonbQueryChainer<P, U> functionObjectExtract(ColumnNameGetter<C> columnNameGetter, ColumnNameGetter<V> jsonKeyNameGetter) {
        setTheLastJsonKeyNameGetter((ColumnNameGetter<P>) jsonKeyNameGetter);
        ColumnJsonbMappingAliasCache.computeToCache(jsonKeyNameGetter);
        return functionObjectExtract(columnNameGetter, List.of(MybatisFlexs.getFieldName(jsonKeyNameGetter)));
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <C, V1, V2 extends POJO> JsonbQueryChainer<P, U> functionObjectExtract(ColumnNameGetter<C> columnNameGetter, ColumnNameGetter<V1> jsonKeyNameGetter1, ColumnNameGetter<V2> jsonKeyNameGetter2) {
        setTheLastJsonKeyNameGetter((ColumnNameGetter<P>) jsonKeyNameGetter2);
        ColumnJsonbMappingAliasCache.computeToCache(jsonKeyNameGetter2);
        return functionObjectExtract(columnNameGetter, List.of(
                MybatisFlexs.getFieldName(jsonKeyNameGetter1),
                MybatisFlexs.getFieldName(jsonKeyNameGetter2)
        ));
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <C, V1, V2, V3 extends POJO> JsonbQueryChainer<P, U> functionObjectExtract(ColumnNameGetter<C> columnNameGetter, ColumnNameGetter<V1> jsonKeyNameGetter1, ColumnNameGetter<V2> jsonKeyNameGetter2, ColumnNameGetter<V3> jsonKeyNameGetter3) {
        setTheLastJsonKeyNameGetter((ColumnNameGetter<P>) jsonKeyNameGetter3);
        ColumnJsonbMappingAliasCache.computeToCache(jsonKeyNameGetter3);
        return functionObjectExtract(columnNameGetter, List.of(
                MybatisFlexs.getFieldName(jsonKeyNameGetter1),
                MybatisFlexs.getFieldName(jsonKeyNameGetter2),
                MybatisFlexs.getFieldName(jsonKeyNameGetter3)
        ));
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <C, V1, V2, V3, V4 extends POJO> JsonbQueryChainer<P, U> functionObjectExtract(ColumnNameGetter<C> columnNameGetter, ColumnNameGetter<V1> jsonKeyNameGetter1, ColumnNameGetter<V2> jsonKeyNameGetter2, ColumnNameGetter<V3> jsonKeyNameGetter3, ColumnNameGetter<V4> jsonKeyNameGetter4) {
        setTheLastJsonKeyNameGetter((ColumnNameGetter<P>) jsonKeyNameGetter4);
        ColumnJsonbMappingAliasCache.computeToCache(jsonKeyNameGetter4);
        return functionObjectExtract(columnNameGetter, List.of(
                MybatisFlexs.getFieldName(jsonKeyNameGetter1),
                MybatisFlexs.getFieldName(jsonKeyNameGetter2),
                MybatisFlexs.getFieldName(jsonKeyNameGetter3),
                MybatisFlexs.getFieldName(jsonKeyNameGetter4)
        ));
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <C, V1, V2, V3, V4, V5 extends POJO> JsonbQueryChainer<P, U> functionObjectExtract(ColumnNameGetter<C> columnNameGetter, ColumnNameGetter<V1> jsonKeyNameGetter1, ColumnNameGetter<V2> jsonKeyNameGetter2, ColumnNameGetter<V3> jsonKeyNameGetter3, ColumnNameGetter<V4> jsonKeyNameGetter4, ColumnNameGetter<V5> jsonKeyNameGetter5) {
        setTheLastJsonKeyNameGetter((ColumnNameGetter<P>) jsonKeyNameGetter5);
        ColumnJsonbMappingAliasCache.computeToCache(jsonKeyNameGetter5);
        return functionObjectExtract(columnNameGetter, List.of(
                MybatisFlexs.getFieldName(jsonKeyNameGetter1),
                MybatisFlexs.getFieldName(jsonKeyNameGetter2),
                MybatisFlexs.getFieldName(jsonKeyNameGetter3),
                MybatisFlexs.getFieldName(jsonKeyNameGetter4),
                MybatisFlexs.getFieldName(jsonKeyNameGetter5)
        ));
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <C, V1, V2, V3, V4, V5, V6 extends POJO> JsonbQueryChainer<P, U> functionObjectExtract(ColumnNameGetter<C> columnNameGetter, ColumnNameGetter<V1> jsonKeyNameGetter1, ColumnNameGetter<V2> jsonKeyNameGetter2, ColumnNameGetter<V3> jsonKeyNameGetter3, ColumnNameGetter<V4> jsonKeyNameGetter4, ColumnNameGetter<V5> jsonKeyNameGetter5, ColumnNameGetter<V6> jsonKeyNameGetter6) {
        setTheLastJsonKeyNameGetter((ColumnNameGetter<P>) jsonKeyNameGetter6);
        ColumnJsonbMappingAliasCache.computeToCache(jsonKeyNameGetter6);
        return functionObjectExtract(columnNameGetter, List.of(
                MybatisFlexs.getFieldName(jsonKeyNameGetter1),
                MybatisFlexs.getFieldName(jsonKeyNameGetter2),
                MybatisFlexs.getFieldName(jsonKeyNameGetter3),
                MybatisFlexs.getFieldName(jsonKeyNameGetter4),
                MybatisFlexs.getFieldName(jsonKeyNameGetter5),
                MybatisFlexs.getFieldName(jsonKeyNameGetter6)
        ));
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <C, V1, V2, V3, V4, V5, V6, V7 extends POJO> JsonbQueryChainer<P, U> functionObjectExtract(ColumnNameGetter<C> columnNameGetter, ColumnNameGetter<V1> jsonKeyNameGetter1, ColumnNameGetter<V2> jsonKeyNameGetter2, ColumnNameGetter<V3> jsonKeyNameGetter3, ColumnNameGetter<V4> jsonKeyNameGetter4, ColumnNameGetter<V5> jsonKeyNameGetter5, ColumnNameGetter<V6> jsonKeyNameGetter6, ColumnNameGetter<V7> jsonKeyNameGetter7) {
        setTheLastJsonKeyNameGetter((ColumnNameGetter<P>) jsonKeyNameGetter7);
        ColumnJsonbMappingAliasCache.computeToCache(jsonKeyNameGetter7);
        return functionObjectExtract(columnNameGetter, List.of(
                MybatisFlexs.getFieldName(jsonKeyNameGetter1),
                MybatisFlexs.getFieldName(jsonKeyNameGetter2),
                MybatisFlexs.getFieldName(jsonKeyNameGetter3),
                MybatisFlexs.getFieldName(jsonKeyNameGetter4),
                MybatisFlexs.getFieldName(jsonKeyNameGetter5),
                MybatisFlexs.getFieldName(jsonKeyNameGetter6),
                MybatisFlexs.getFieldName(jsonKeyNameGetter7)
        ));
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <C, V1, V2, V3, V4, V5, V6, V7, V8 extends POJO> JsonbQueryChainer<P, U> functionObjectExtract(ColumnNameGetter<C> columnNameGetter, ColumnNameGetter<V1> jsonKeyNameGetter1, ColumnNameGetter<V2> jsonKeyNameGetter2, ColumnNameGetter<V3> jsonKeyNameGetter3, ColumnNameGetter<V4> jsonKeyNameGetter4, ColumnNameGetter<V5> jsonKeyNameGetter5, ColumnNameGetter<V6> jsonKeyNameGetter6, ColumnNameGetter<V7> jsonKeyNameGetter7, ColumnNameGetter<V8> jsonKeyNameGetter8) {
        setTheLastJsonKeyNameGetter((ColumnNameGetter<P>) jsonKeyNameGetter8);
        ColumnJsonbMappingAliasCache.computeToCache(jsonKeyNameGetter8);
        return functionObjectExtract(columnNameGetter, List.of(
                MybatisFlexs.getFieldName(jsonKeyNameGetter1),
                MybatisFlexs.getFieldName(jsonKeyNameGetter2),
                MybatisFlexs.getFieldName(jsonKeyNameGetter3),
                MybatisFlexs.getFieldName(jsonKeyNameGetter4),
                MybatisFlexs.getFieldName(jsonKeyNameGetter5),
                MybatisFlexs.getFieldName(jsonKeyNameGetter6),
                MybatisFlexs.getFieldName(jsonKeyNameGetter7),
                MybatisFlexs.getFieldName(jsonKeyNameGetter8)
        ));
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <C, V1, V2, V3, V4, V5, V6, V7, V8, V9 extends POJO> JsonbQueryChainer<P, U> functionObjectExtract(ColumnNameGetter<C> columnNameGetter, ColumnNameGetter<V1> jsonKeyNameGetter1, ColumnNameGetter<V2> jsonKeyNameGetter2, ColumnNameGetter<V3> jsonKeyNameGetter3, ColumnNameGetter<V4> jsonKeyNameGetter4, ColumnNameGetter<V5> jsonKeyNameGetter5, ColumnNameGetter<V6> jsonKeyNameGetter6, ColumnNameGetter<V7> jsonKeyNameGetter7, ColumnNameGetter<V8> jsonKeyNameGetter8, ColumnNameGetter<V9> jsonKeyNameGetter9) {
        setTheLastJsonKeyNameGetter((ColumnNameGetter<P>) jsonKeyNameGetter9);
        ColumnJsonbMappingAliasCache.computeToCache(jsonKeyNameGetter9);
        return functionObjectExtract(columnNameGetter, List.of(
                MybatisFlexs.getFieldName(jsonKeyNameGetter1),
                MybatisFlexs.getFieldName(jsonKeyNameGetter2),
                MybatisFlexs.getFieldName(jsonKeyNameGetter3),
                MybatisFlexs.getFieldName(jsonKeyNameGetter4),
                MybatisFlexs.getFieldName(jsonKeyNameGetter5),
                MybatisFlexs.getFieldName(jsonKeyNameGetter6),
                MybatisFlexs.getFieldName(jsonKeyNameGetter7),
                MybatisFlexs.getFieldName(jsonKeyNameGetter8),
                MybatisFlexs.getFieldName(jsonKeyNameGetter9)
        ));
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    private <C> JsonbQueryChainer<P, U> functionObjectExtract(ColumnNameGetter<C> columnNameGetter, List<String> jsonKeyNames) {
        String jsonColumnName = MybatisFlexs.getFieldName(columnNameGetter);
        String jsonViewAlias = ColumnJsonbMappingAliasCache.get((ColumnNameGetter<P>) columnNameGetter, jsonColumnName);
        if (Nil.isNotNull(jsonViewAlias)) {
            jsonColumnName = jsonViewAlias;
        }
        setTheLastJsonKeyName(Collections.getLast(jsonKeyNames).orElseThrow());
        setJsonbFunctionSQL(Strings.format(PostgresqlFunction.JSONB_OBJECT_EXTRACT_APPENDER, jsonColumnName, Strings.joinWithSingleQuoteAndComma(jsonKeyNames)));
        return this;
    }

    public JsonbQueryChainer<P, U> functionArrayUnnest() {
        ColumnJsonbMappingAliasCache.computeToCache(getJsonbFunctionSQL(), getPoClass().getSimpleName());
        setJsonbFunctionSQL(Strings.format(PostgresqlFunction.JSONB_ARRAY_UNNEST_APPENDER, getJsonbFunctionSQL()));
        return this;
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <C extends POJO> JsonbQueryChainer<P, U> functionArrayUnnest(ColumnNameGetter<C> columnNameGetter) {
        ColumnJsonbMappingAliasCache.computeToUnderlineCaseCache(columnNameGetter);
        String jsonColumnName = Strings.underlineCase(MybatisFlexs.getFieldName(columnNameGetter));
        setTheLastJsonKeyName(jsonColumnName);
        setTheLastJsonKeyNameGetter((ColumnNameGetter<P>) columnNameGetter);
        setJsonbFunctionSQL(Strings.format(PostgresqlFunction.JSONB_ARRAY_UNNEST_APPENDER, jsonColumnName));
        return this;
    }

    @SuppressWarnings(SuppressWarningConstant.DEPRECATED)
    public JsonbQueryChainer<P, U> innerJoin() {
        String jsonViewAlias = ColumnJsonbMappingAliasCache.get(getTheLastJsonKeyNameGetter(), getTheLastJsonKeyName());
        Assert.of().setMessage("{}could not find the json query alias name by [{}], please check!", ModuleView.ORM_MYBATIS_SYSTEM, getTheLastJsonKeyName())
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfNull(jsonViewAlias);
        getNativeQueryChain()
                .innerJoin(new RawQueryTable(getJsonbFunctionSQL()))
                .as(Strings.removeHeadTailDoubleQuote(jsonViewAlias))
                .on(BooleanConstant.TRUE_STRING_LOWER_CASE);
        return new JsonbQueryChainer<>(getNormalQueryChainer(), getPoClass());
    }

    public NormalQueryChainer<U, P> switchToNormalQuery() {
        return getNormalQueryChainer();
    }

}