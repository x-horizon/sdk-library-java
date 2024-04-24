// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.chain;

import cn.srd.library.java.contract.component.database.postgresql.PostgresqlFunction;
import cn.srd.library.java.contract.constant.booleans.BooleanConstant;
import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.constant.text.SymbolConstant;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.contract.model.base.POJO;
import cn.srd.library.java.orm.mybatis.flex.base.tool.ColumnNameGetter;
import cn.srd.library.java.orm.mybatis.flex.base.tool.MybatisFlexs;
import cn.srd.library.java.orm.mybatis.flex.postgresql.cache.ColumnJsonbMappingAliasCache;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.text.Strings;
import com.mybatisflex.core.query.RawQueryTable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author wjm
 * @since 2024-04-23 19:48
 */
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class JsonbQueryChainer<P extends POJO, U extends PO> {

    @Getter(AccessLevel.PRIVATE) private final QueryChainer<U> queryChainer;

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private String jsonbFunctionSQL;

    public static <P extends POJO, U extends PO> JsonbQueryChainer<P, U> of(QueryChainer<U> queryChainer) {
        return new JsonbQueryChainer<>(queryChainer, SymbolConstant.EMPTY);
    }

    public <C, V extends POJO> JsonbQueryChainer<P, U> functionObjectExtract(ColumnNameGetter<C> columnNameGetter, ColumnNameGetter<V> jsonKeyNameGetter) {
        return functionObjectExtract(columnNameGetter, List.of(MybatisFlexs.getFieldName(jsonKeyNameGetter)));
    }

    public <C, V1, V2 extends POJO> JsonbQueryChainer<P, U> functionObjectExtract(ColumnNameGetter<C> columnNameGetter, ColumnNameGetter<V1> jsonKeyNameGetter1, ColumnNameGetter<V2> jsonKeyNameGetter2) {
        return functionObjectExtract(columnNameGetter, List.of(
                MybatisFlexs.getFieldName(jsonKeyNameGetter1),
                MybatisFlexs.getFieldName(jsonKeyNameGetter2)
        ));
    }

    public <C, V1, V2, V3 extends POJO> JsonbQueryChainer<P, U> functionObjectExtract(ColumnNameGetter<C> columnNameGetter, ColumnNameGetter<V1> jsonKeyNameGetter1, ColumnNameGetter<V2> jsonKeyNameGetter2, ColumnNameGetter<V3> jsonKeyNameGetter3) {
        return functionObjectExtract(columnNameGetter, List.of(
                MybatisFlexs.getFieldName(jsonKeyNameGetter1),
                MybatisFlexs.getFieldName(jsonKeyNameGetter2),
                MybatisFlexs.getFieldName(jsonKeyNameGetter3)
        ));
    }

    public <C, V1, V2, V3, V4 extends POJO> JsonbQueryChainer<P, U> functionObjectExtract(ColumnNameGetter<C> columnNameGetter, ColumnNameGetter<V1> jsonKeyNameGetter1, ColumnNameGetter<V2> jsonKeyNameGetter2, ColumnNameGetter<V3> jsonKeyNameGetter3, ColumnNameGetter<V4> jsonKeyNameGetter4) {
        return functionObjectExtract(columnNameGetter, List.of(
                MybatisFlexs.getFieldName(jsonKeyNameGetter1),
                MybatisFlexs.getFieldName(jsonKeyNameGetter2),
                MybatisFlexs.getFieldName(jsonKeyNameGetter3),
                MybatisFlexs.getFieldName(jsonKeyNameGetter4)
        ));
    }

    public <C, V1, V2, V3, V4, V5 extends POJO> JsonbQueryChainer<P, U> functionObjectExtract(ColumnNameGetter<C> columnNameGetter, ColumnNameGetter<V1> jsonKeyNameGetter1, ColumnNameGetter<V2> jsonKeyNameGetter2, ColumnNameGetter<V3> jsonKeyNameGetter3, ColumnNameGetter<V4> jsonKeyNameGetter4, ColumnNameGetter<V5> jsonKeyNameGetter5) {
        return functionObjectExtract(columnNameGetter, List.of(
                MybatisFlexs.getFieldName(jsonKeyNameGetter1),
                MybatisFlexs.getFieldName(jsonKeyNameGetter2),
                MybatisFlexs.getFieldName(jsonKeyNameGetter3),
                MybatisFlexs.getFieldName(jsonKeyNameGetter4),
                MybatisFlexs.getFieldName(jsonKeyNameGetter5)
        ));
    }

    public <C, V1, V2, V3, V4, V5, V6 extends POJO> JsonbQueryChainer<P, U> functionObjectExtract(ColumnNameGetter<C> columnNameGetter, ColumnNameGetter<V1> jsonKeyNameGetter1, ColumnNameGetter<V2> jsonKeyNameGetter2, ColumnNameGetter<V3> jsonKeyNameGetter3, ColumnNameGetter<V4> jsonKeyNameGetter4, ColumnNameGetter<V5> jsonKeyNameGetter5, ColumnNameGetter<V6> jsonKeyNameGetter6) {
        return functionObjectExtract(columnNameGetter, List.of(
                MybatisFlexs.getFieldName(jsonKeyNameGetter1),
                MybatisFlexs.getFieldName(jsonKeyNameGetter2),
                MybatisFlexs.getFieldName(jsonKeyNameGetter3),
                MybatisFlexs.getFieldName(jsonKeyNameGetter4),
                MybatisFlexs.getFieldName(jsonKeyNameGetter5),
                MybatisFlexs.getFieldName(jsonKeyNameGetter6)
        ));
    }

    public <C, V1, V2, V3, V4, V5, V6, V7 extends POJO> JsonbQueryChainer<P, U> functionObjectExtract(ColumnNameGetter<C> columnNameGetter, ColumnNameGetter<V1> jsonKeyNameGetter1, ColumnNameGetter<V2> jsonKeyNameGetter2, ColumnNameGetter<V3> jsonKeyNameGetter3, ColumnNameGetter<V4> jsonKeyNameGetter4, ColumnNameGetter<V5> jsonKeyNameGetter5, ColumnNameGetter<V6> jsonKeyNameGetter6, ColumnNameGetter<V7> jsonKeyNameGetter7) {
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

    public <C, V1, V2, V3, V4, V5, V6, V7, V8 extends POJO> JsonbQueryChainer<P, U> functionObjectExtract(ColumnNameGetter<C> columnNameGetter, ColumnNameGetter<V1> jsonKeyNameGetter1, ColumnNameGetter<V2> jsonKeyNameGetter2, ColumnNameGetter<V3> jsonKeyNameGetter3, ColumnNameGetter<V4> jsonKeyNameGetter4, ColumnNameGetter<V5> jsonKeyNameGetter5, ColumnNameGetter<V6> jsonKeyNameGetter6, ColumnNameGetter<V7> jsonKeyNameGetter7, ColumnNameGetter<V8> jsonKeyNameGetter8) {
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

    public <C, V1, V2, V3, V4, V5, V6, V7, V8, V9 extends POJO> JsonbQueryChainer<P, U> functionObjectExtract(ColumnNameGetter<C> columnNameGetter, ColumnNameGetter<V1> jsonKeyNameGetter1, ColumnNameGetter<V2> jsonKeyNameGetter2, ColumnNameGetter<V3> jsonKeyNameGetter3, ColumnNameGetter<V4> jsonKeyNameGetter4, ColumnNameGetter<V5> jsonKeyNameGetter5, ColumnNameGetter<V6> jsonKeyNameGetter6, ColumnNameGetter<V7> jsonKeyNameGetter7, ColumnNameGetter<V8> jsonKeyNameGetter8, ColumnNameGetter<V9> jsonKeyNameGetter9) {
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

    private <C> JsonbQueryChainer<P, U> functionObjectExtract(ColumnNameGetter<C> columnNameGetter, List<String> jsonKeyNames) {
        String jsonColumnName = MybatisFlexs.getFieldName(columnNameGetter);
        String jsonViewAlias = ColumnJsonbMappingAliasCache.get(jsonColumnName);
        if (Nil.isNotNull(jsonViewAlias)) {
            jsonColumnName = jsonViewAlias;
        }
        String theLastJsonKeyName = Collections.getLast(jsonKeyNames).orElseThrow();
        ColumnJsonbMappingAliasCache.computeToCache(theLastJsonKeyName);
        setJsonbFunctionSQL(Strings.format(PostgresqlFunction.JSONB_OBJECT_EXTRACT_APPENDER, jsonColumnName, Strings.joinWithSingleQuoteAndComma(jsonKeyNames)));
        return this;
    }

    public JsonbQueryChainer<P, U> functionArrayUnnest() {
        ColumnJsonbMappingAliasCache.computeToCache(getJsonbFunctionSQL());
        setJsonbFunctionSQL(Strings.format(PostgresqlFunction.JSONB_ARRAY_UNNEST_APPENDER, getJsonbFunctionSQL()));
        return this;
    }

    public <C extends POJO> JsonbQueryChainer<P, U> functionArrayUnnest(ColumnNameGetter<C> columnNameGetter) {
        String jsonColumnName = MybatisFlexs.getFieldName(columnNameGetter);
        ColumnJsonbMappingAliasCache.computeToCache(jsonColumnName);
        setJsonbFunctionSQL(Strings.format(PostgresqlFunction.JSONB_ARRAY_UNNEST_APPENDER, jsonColumnName));
        return this;
    }

    @SuppressWarnings(SuppressWarningConstant.DEPRECATED)
    public <V extends POJO> JsonbQueryChainer<P, U> innerJoin(ColumnNameGetter<V> theLastJsonKeyNameGetter) {
        String jsonKeyName = MybatisFlexs.getFieldName(theLastJsonKeyNameGetter);
        String jsonViewAlias = ColumnJsonbMappingAliasCache.get(jsonKeyName);
        Assert.of().setMessage("{}could not find the json query alias name by [{}], please check!", ModuleView.ORM_MYBATIS_SYSTEM, jsonKeyName)
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfNull(jsonViewAlias);
        getQueryChainer().getNativeQueryChain()
                .innerJoin(new RawQueryTable(getJsonbFunctionSQL()))
                .as(Strings.removeHeadTailDoubleQuote(jsonViewAlias))
                .on(BooleanConstant.TRUE_STRING_LOWER_CASE);
        return this;
    }

    public JsonbQueryChainer<P, U> next() {
        return JsonbQueryChainer.of(getQueryChainer());
    }

    public QueryChainer<U> switchToNormalQuery() {
        return getQueryChainer();
    }

}