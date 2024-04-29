// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.chain2;

import cn.srd.library.java.contract.constant.database.PostgresqlFunctionType;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.model.base.POJO;
import cn.srd.library.java.orm.mybatis.flex.base.chain.BaseQueryChainer;
import cn.srd.library.java.orm.mybatis.flex.base.chain.QueryChain;
import cn.srd.library.java.orm.mybatis.flex.base.tool.ColumnNameGetter;
import cn.srd.library.java.orm.mybatis.flex.base.tool.MybatisFlexs;
import cn.srd.library.java.tool.lang.text.Strings;
import com.mybatisflex.core.constant.SqlConnector;
import com.mybatisflex.core.query.RawQueryCondition;
import com.mybatisflex.core.query.RawQueryTable;
import lombok.AccessLevel;
import lombok.Getter;

import static com.mybatisflex.core.query.QueryMethods.selectOne;

/**
 * the postgresql jsonb function.
 *
 * @author wjm
 * @since 2024-04-18 20:34
 */
@Getter(AccessLevel.PROTECTED)
public class JsonbQueryFunctionChainer<PJ extends POJO> extends BaseQueryChainer<PJ> {

    private final QueryChain<PJ> nativeQueryChain;

    private final String fieldName;

    private final String classOfFieldName;

    private final String sqlAppender;

    private JsonbQueryFunctionChainer(String fieldName, String classOfFieldName, String sqlAppender) {
        this.nativeQueryChain = new QueryChain<>(null);
        this.fieldName = fieldName;
        this.classOfFieldName = classOfFieldName;
        this.sqlAppender = sqlAppender;
    }

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    public static <PJ1 extends POJO> JsonbQueryFunctionChainer<PJ1> jsonbArrayElements(ColumnNameGetter<PJ1> columnNameGetter) {
        return new JsonbQueryFunctionChainer<>(
                MybatisFlexs.getFieldName(columnNameGetter),
                MybatisFlexs.getClassName(columnNameGetter),
                STR."\{PostgresqlFunctionType.JSONB_ARRAY_ELEMENTS.getValue()}({}\{MybatisFlexs.getColumnName(columnNameGetter)})"
        );
    }

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    public static <PJ1 extends POJO> JsonbQueryFunctionChainer<PJ1> jsonbArrayElements(JsonbQueryFunctionChainer<PJ1> function) {
        return new JsonbQueryFunctionChainer<>(
                function.getFieldName(),
                function.getClassOfFieldName(),
                STR."\{PostgresqlFunctionType.JSONB_ARRAY_ELEMENTS.getValue()}(\{function.getSqlAppender()})"
        );
    }

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    public static <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO> JsonbQueryFunctionChainer<PJ1> jsonbExtractPath(ColumnNameGetter<PJ2> columnNameGetter, ColumnNameGetter<PJ3> jsonKeyGetter) {
        String theLastJsonKeyFieldName = MybatisFlexs.getFieldName(jsonKeyGetter);
        return new JsonbQueryFunctionChainer<>(
                theLastJsonKeyFieldName,
                MybatisFlexs.getClassName(jsonKeyGetter),
                STR."\{PostgresqlFunctionType.JSONB_EXTRACT_PATH.getValue()}({}\{MybatisFlexs.getColumnName(columnNameGetter)}, '\{theLastJsonKeyFieldName}')"
        );
    }

    public JsonbQueryFunctionCaster<PJ> where(ColumnNameGetter<PJ> columnNameGetter) {
        return and(columnNameGetter);
    }

    public JsonbQueryFunctionCaster<PJ> and(ColumnNameGetter<PJ> columnNameGetter) {
        String alias = Strings.format("{}_{}", Strings.lowerFirst(this.classOfFieldName), this.fieldName);
        return new JsonbQueryFunctionCaster<>(
                selectOne()
                        .from(new RawQueryTable(Strings.format(this.sqlAppender, Strings.format("\"{}\".", "student"))))
                        .as(alias)
                        .where(new RawQueryCondition(Strings.joinWithDoubleQuoteAndComma(alias))).toSQL(),
                SqlConnector.AND,
                this.nativeQueryChain,
                this
        );
    }

    // public JsonbQueryFunctionCaster<PJ> or(ColumnNameGetter<PJ> columnNameGetter) {
    //     return new QueryCaster<>(connectJsonbDirectQuerySQL(columnNameGetter, jsonKeyGetter1, jsonKeyGetter2), SqlConnector.OR, this.nativeQueryChain, this);
    // }

}