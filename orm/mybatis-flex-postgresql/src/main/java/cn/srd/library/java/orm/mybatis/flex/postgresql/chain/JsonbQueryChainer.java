// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.chain;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.constant.text.SymbolConstant;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.contract.model.base.POJO;
import cn.srd.library.java.orm.mybatis.flex.base.tool.ColumnNameGetter;
import cn.srd.library.java.orm.mybatis.flex.base.tool.MybatisFlexs;
import cn.srd.library.java.orm.mybatis.flex.postgresql.cache.ColumnJsonbMappingAliasCache;
import cn.srd.library.java.tool.id.snowflake.SnowflakeIds;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.text.Strings;
import com.mybatisflex.core.query.RawQueryTable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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

    public <C, V extends POJO> JsonbQueryChainer<P, U> addObjectExtractFunction(ColumnNameGetter<C> columnNameGetter, ColumnNameGetter<V> jsonKeyNameGetter) {
        String jsonColumnName = MybatisFlexs.getFieldName(columnNameGetter);
        String jsonViewAlias = ColumnJsonbMappingAliasCache.get(jsonColumnName);
        if (Nil.isNotNull(jsonViewAlias)) {
            jsonColumnName = jsonViewAlias;
        }
        String jsonKeyName = MybatisFlexs.getFieldName(jsonKeyNameGetter);
        ColumnJsonbMappingAliasCache.set(jsonKeyName, Strings.format("{}_{}", jsonKeyName, SnowflakeIds.get()));
        setJsonbFunctionSQL(Strings.format("JSONB_EXTRACT_PATH({}, {})", jsonColumnName, Strings.joinWithSingleQuoteAndComma(jsonKeyName)));
        return this;
    }

    public JsonbQueryChainer<P, U> addArrayUnnestFunction() {
        setJsonbFunctionSQL(Strings.format("JSONB_ARRAY_ELEMENTS({})", getJsonbFunctionSQL()));
        return this;
    }

    public <C extends POJO> JsonbQueryChainer<P, U> addArrayUnnestFunction(ColumnNameGetter<C> columnNameGetter) {
        String jsonColumnName = MybatisFlexs.getFieldName(columnNameGetter);
        setJsonbFunctionSQL(Strings.format("JSONB_ARRAY_ELEMENTS({})", jsonColumnName));
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
                .as(jsonViewAlias)
                .on("true");
        return this;
    }

    public JsonbQueryChainer<P, U> next() {
        return JsonbQueryChainer.of(getQueryChainer());
    }

    public QueryChainer<U> switchToQuery() {
        return getQueryChainer();
    }

    // @SuppressWarnings(SuppressWarningConstant.DEPRECATED)
    // public JsonbQueryChainer<P, U> innerJoinJsonbListObjectView2(ColumnNameGetter<U> columnNameGetter, ColumnNameGetter<P> jsonKeyNameGetter, PostgresqlFunctionType... jsonFunctionTypes) {
    //     Assert.of().setMessage("{}postgresql function is not specified, please check!", ModuleView.ORM_MYBATIS_SYSTEM)
    //             .setThrowable(LibraryJavaInternalException.class)
    //             .throwsIfEmpty(jsonFunctionTypes);
    //
    //     String jsonColumnName = MybatisFlexs.getFieldName(columnNameGetter);
    //     String jsonKeyName = MybatisFlexs.getFieldName(jsonKeyNameGetter);
    //     String jsonViewAlias = JSON_QUERY_FIELD_NAME_MAPPING_JSON_QUERY_COLUMN_NAME_MAP.computeIfAbsent(jsonKeyName, ignore -> Strings.format("{}_{}", jsonKeyName, SnowflakeIds.get()));
    //     String jsonKeyActualName = Strings.format("'{}'", jsonKeyName);
    //
    //     String innerJoinJsonbSQL = SymbolConstant.EMPTY;
    //     String[] innerJoinJsonbSQLs = Collections.ofArray(String.class, jsonColumnName, jsonKeyActualName);
    //     for (PostgresqlFunctionType jsonFunctionType : jsonFunctionTypes) {
    //         innerJoinJsonbSQL = jsonFunctionType.getStrategy().toSQL(innerJoinJsonbSQLs);
    //         innerJoinJsonbSQLs = Collections.ofArray(String.class, innerJoinJsonbSQL);
    //     }
    //
    //     getNativeQueryChain()
    //             .innerJoin(new RawQueryTable(innerJoinJsonbSQL))
    //             .as(jsonViewAlias)
    //             .on("true");
    //
    //     return this;
    // }

}