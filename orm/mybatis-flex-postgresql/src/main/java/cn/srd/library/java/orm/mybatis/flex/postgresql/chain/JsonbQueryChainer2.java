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
public class JsonbQueryChainer2<PJ extends POJO, P extends PO> extends BaseQueryChainer<PJ> {

    private final NormalQueryChainer<P, PJ> normalQueryChainer;

    private final QueryChain<PJ> nativeQueryChain;

    private final Class<P> poClass;

    private String jsonbFunctionSQL;

    private String theLastJsonKeyName;

    private ColumnNameGetter<PJ> theLastJsonKeyNameGetter;

    public JsonbQueryChainer2(NormalQueryChainer<P, PJ> normalQueryChainer, Class<P> poClass) {
        this.normalQueryChainer = normalQueryChainer;
        this.nativeQueryChain = Reflects.getFieldValue(normalQueryChainer, "nativeQueryChain");
        this.poClass = poClass;
    }

    public <PJ1 extends POJO, PJ2 extends POJO> JsonbQueryCaster2<PJ, PJ1, PJ2, P> where(ColumnNameGetter<PJ1> columnNameGetter) {
        return and(columnNameGetter);
    }

    public <PJ1 extends POJO, PJ2 extends POJO> JsonbQueryCaster2<PJ, PJ1, PJ2, P> where(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyNameGetter) {
        return and(columnNameGetter, jsonKeyNameGetter);
    }

    public <PJ1 extends POJO, PJ2 extends POJO> JsonbQueryCaster2<PJ, PJ1, PJ2, P> and(ColumnNameGetter<PJ1> columnNameGetter) {
        return new JsonbQueryCaster2<>(SqlConnector.AND, this, getNativeQueryChain(), columnNameGetter);
    }

    public <PJ1 extends POJO, PJ2 extends POJO> JsonbQueryCaster2<PJ, PJ1, PJ2, P> and(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyNameGetter) {
        return new JsonbQueryCaster2<>(SqlConnector.AND, this, getNativeQueryChain(), columnNameGetter, jsonKeyNameGetter);
    }

    public <PJ1 extends POJO, PJ2 extends POJO> JsonbQueryCaster2<PJ, PJ1, PJ2, P> or(ColumnNameGetter<PJ1> columnNameGetter) {
        return new JsonbQueryCaster2<>(SqlConnector.OR, this, getNativeQueryChain(), columnNameGetter);
    }

    public <PJ1 extends POJO, PJ2 extends POJO> JsonbQueryCaster2<PJ, PJ1, PJ2, P> or(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyNameGetter) {
        return new JsonbQueryCaster2<>(SqlConnector.OR, this, getNativeQueryChain(), columnNameGetter, jsonKeyNameGetter);
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <PJ1, PJ2 extends POJO> JsonbQueryChainer2<PJ, P> functionObjectExtract(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyNameGetter) {
        setTheLastJsonKeyNameGetter((ColumnNameGetter<PJ>) jsonKeyNameGetter);
        ColumnJsonbMappingAliasCache.computeToCache(jsonKeyNameGetter);
        return functionObjectExtract(columnNameGetter, List.of(MybatisFlexs.getFieldName(jsonKeyNameGetter)));
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <PJ1, PJ2, PJ3 extends POJO> JsonbQueryChainer2<PJ, P> functionObjectExtract(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyNameGetter1, ColumnNameGetter<PJ3> jsonKeyNameGetter2) {
        setTheLastJsonKeyNameGetter((ColumnNameGetter<PJ>) jsonKeyNameGetter2);
        ColumnJsonbMappingAliasCache.computeToCache(jsonKeyNameGetter2);
        return functionObjectExtract(columnNameGetter, List.of(
                MybatisFlexs.getFieldName(jsonKeyNameGetter1),
                MybatisFlexs.getFieldName(jsonKeyNameGetter2)
        ));
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <PJ1, PJ2, PJ3, PJ4 extends POJO> JsonbQueryChainer2<PJ, P> functionObjectExtract(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyNameGetter1, ColumnNameGetter<PJ3> jsonKeyNameGetter2, ColumnNameGetter<PJ4> jsonKeyNameGetter3) {
        setTheLastJsonKeyNameGetter((ColumnNameGetter<PJ>) jsonKeyNameGetter3);
        ColumnJsonbMappingAliasCache.computeToCache(jsonKeyNameGetter3);
        return functionObjectExtract(columnNameGetter, List.of(
                MybatisFlexs.getFieldName(jsonKeyNameGetter1),
                MybatisFlexs.getFieldName(jsonKeyNameGetter2),
                MybatisFlexs.getFieldName(jsonKeyNameGetter3)
        ));
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <PJ1, PJ2, PJ3, PJ4, PJ5 extends POJO> JsonbQueryChainer2<PJ, P> functionObjectExtract(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyNameGetter1, ColumnNameGetter<PJ3> jsonKeyNameGetter2, ColumnNameGetter<PJ4> jsonKeyNameGetter3, ColumnNameGetter<PJ5> jsonKeyNameGetter4) {
        setTheLastJsonKeyNameGetter((ColumnNameGetter<PJ>) jsonKeyNameGetter4);
        ColumnJsonbMappingAliasCache.computeToCache(jsonKeyNameGetter4);
        return functionObjectExtract(columnNameGetter, List.of(
                MybatisFlexs.getFieldName(jsonKeyNameGetter1),
                MybatisFlexs.getFieldName(jsonKeyNameGetter2),
                MybatisFlexs.getFieldName(jsonKeyNameGetter3),
                MybatisFlexs.getFieldName(jsonKeyNameGetter4)
        ));
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <PJ1, PJ2, PJ3, PJ4, PJ5, PJ6 extends POJO> JsonbQueryChainer2<PJ, P> functionObjectExtract(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyNameGetter1, ColumnNameGetter<PJ3> jsonKeyNameGetter2, ColumnNameGetter<PJ4> jsonKeyNameGetter3, ColumnNameGetter<PJ5> jsonKeyNameGetter4, ColumnNameGetter<PJ6> jsonKeyNameGetter5) {
        setTheLastJsonKeyNameGetter((ColumnNameGetter<PJ>) jsonKeyNameGetter5);
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
    public <PJ1, PJ2, PJ3, PJ4, PJ5, PJ6, PJ7 extends POJO> JsonbQueryChainer2<PJ, P> functionObjectExtract(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyNameGetter1, ColumnNameGetter<PJ3> jsonKeyNameGetter2, ColumnNameGetter<PJ4> jsonKeyNameGetter3, ColumnNameGetter<PJ5> jsonKeyNameGetter4, ColumnNameGetter<PJ6> jsonKeyNameGetter5, ColumnNameGetter<PJ7> jsonKeyNameGetter6) {
        setTheLastJsonKeyNameGetter((ColumnNameGetter<PJ>) jsonKeyNameGetter6);
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
    public <PJ1, PJ2, PJ3, PJ4, PJ5, PJ6, PJ7, PJ8 extends POJO> JsonbQueryChainer2<PJ, P> functionObjectExtract(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyNameGetter1, ColumnNameGetter<PJ3> jsonKeyNameGetter2, ColumnNameGetter<PJ4> jsonKeyNameGetter3, ColumnNameGetter<PJ5> jsonKeyNameGetter4, ColumnNameGetter<PJ6> jsonKeyNameGetter5, ColumnNameGetter<PJ7> jsonKeyNameGetter6, ColumnNameGetter<PJ8> jsonKeyNameGetter7) {
        setTheLastJsonKeyNameGetter((ColumnNameGetter<PJ>) jsonKeyNameGetter7);
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
    public <PJ1, PJ2, PJ3, PJ4, PJ5, PJ6, PJ7, PJ8, PJ9 extends POJO> JsonbQueryChainer2<PJ, P> functionObjectExtract(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyNameGetter1, ColumnNameGetter<PJ3> jsonKeyNameGetter2, ColumnNameGetter<PJ4> jsonKeyNameGetter3, ColumnNameGetter<PJ5> jsonKeyNameGetter4, ColumnNameGetter<PJ6> jsonKeyNameGetter5, ColumnNameGetter<PJ7> jsonKeyNameGetter6, ColumnNameGetter<PJ8> jsonKeyNameGetter7, ColumnNameGetter<PJ9> jsonKeyNameGetter8) {
        setTheLastJsonKeyNameGetter((ColumnNameGetter<PJ>) jsonKeyNameGetter8);
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
    public <PJ1, PJ2, PJ3, PJ4, PJ5, PJ6, PJ7, PJ8, PJ9, PJ10 extends POJO> JsonbQueryChainer2<PJ, P> functionObjectExtract(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyNameGetter1, ColumnNameGetter<PJ3> jsonKeyNameGetter2, ColumnNameGetter<PJ4> jsonKeyNameGetter3, ColumnNameGetter<PJ5> jsonKeyNameGetter4, ColumnNameGetter<PJ6> jsonKeyNameGetter5, ColumnNameGetter<PJ7> jsonKeyNameGetter6, ColumnNameGetter<PJ8> jsonKeyNameGetter7, ColumnNameGetter<PJ9> jsonKeyNameGetter8, ColumnNameGetter<PJ10> jsonKeyNameGetter9) {
        setTheLastJsonKeyNameGetter((ColumnNameGetter<PJ>) jsonKeyNameGetter9);
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
    private <C> JsonbQueryChainer2<PJ, P> functionObjectExtract(ColumnNameGetter<C> columnNameGetter, List<String> jsonKeyNames) {
        String jsonColumnName = MybatisFlexs.getFieldName(columnNameGetter);
        String jsonViewAlias = ColumnJsonbMappingAliasCache.get((ColumnNameGetter<PJ>) columnNameGetter, jsonColumnName);
        if (Nil.isNotNull(jsonViewAlias)) {
            jsonColumnName = jsonViewAlias;
        }
        setTheLastJsonKeyName(Collections.getLast(jsonKeyNames).orElseThrow());
        setJsonbFunctionSQL(Strings.format(PostgresqlFunction.JSONB_OBJECT_EXTRACT_APPENDER, jsonColumnName, Strings.joinWithSingleQuoteAndComma(jsonKeyNames)));
        return this;
    }

    public JsonbQueryChainer2<PJ, P> functionArrayUnnest() {
        ColumnJsonbMappingAliasCache.computeToCache(getJsonbFunctionSQL(), getPoClass().getSimpleName());
        setJsonbFunctionSQL(Strings.format(PostgresqlFunction.JSONB_ARRAY_UNNEST_APPENDER, getJsonbFunctionSQL()));
        return this;
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <PJ1 extends POJO> JsonbQueryChainer2<PJ, P> functionArrayUnnest(ColumnNameGetter<PJ1> columnNameGetter) {
        ColumnJsonbMappingAliasCache.computeToUnderlineCaseCache(columnNameGetter);
        String jsonColumnName = Strings.underlineCase(MybatisFlexs.getFieldName(columnNameGetter));
        setTheLastJsonKeyName(jsonColumnName);
        setTheLastJsonKeyNameGetter((ColumnNameGetter<PJ>) columnNameGetter);
        setJsonbFunctionSQL(Strings.format(PostgresqlFunction.JSONB_ARRAY_UNNEST_APPENDER, jsonColumnName));
        return this;
    }

    @SuppressWarnings(SuppressWarningConstant.DEPRECATED)
    public JsonbQueryChainer2<PJ, P> innerJoin() {
        String jsonViewAlias = ColumnJsonbMappingAliasCache.get(getTheLastJsonKeyNameGetter(), getTheLastJsonKeyName());
        Assert.of().setMessage("{}could not find the json query alias name by [{}], please check!", ModuleView.ORM_MYBATIS_SYSTEM, getTheLastJsonKeyName())
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfNull(jsonViewAlias);
        getNativeQueryChain()
                .innerJoin(new RawQueryTable(getJsonbFunctionSQL()))
                .as(Strings.removeHeadTailDoubleQuote(jsonViewAlias))
                .on(BooleanConstant.TRUE_STRING_LOWER_CASE);
        return new JsonbQueryChainer2<>(getNormalQueryChainer(), getPoClass());
    }

    public NormalQueryChainer<P, PJ> switchToNormalQuery() {
        return getNormalQueryChainer();
    }

}