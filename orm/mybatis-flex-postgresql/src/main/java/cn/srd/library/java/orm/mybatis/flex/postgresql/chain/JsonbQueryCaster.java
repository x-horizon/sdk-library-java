// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.chain;

import cn.srd.library.java.contract.constant.database.PostgresqlDataType;
import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.contract.model.base.POJO;
import cn.srd.library.java.orm.mybatis.flex.base.chain.QueryChain;
import cn.srd.library.java.orm.mybatis.flex.base.chain.QueryConditional;
import cn.srd.library.java.orm.mybatis.flex.base.tool.ColumnNameGetter;
import cn.srd.library.java.orm.mybatis.flex.base.tool.MybatisFlexs;
import cn.srd.library.java.orm.mybatis.flex.postgresql.cache.ColumnJsonbMappingAliasCache;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.text.Strings;
import com.mybatisflex.core.constant.SqlConnector;
import com.mybatisflex.core.query.RawQueryCondition;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author wjm
 * @since 2024-04-25 15:02
 */
@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
// @AllArgsConstructor
public class JsonbQueryCaster<P extends POJO, C extends POJO, V extends POJO, U extends PO> {

    private final SqlConnector sqlConnector;

    private final JsonbQueryChainer<P, U> jsonbQueryChainer;

    private final QueryChain<P> nativeQueryChain;

    private final ColumnNameGetter<C> columnNameGetter;

    private ColumnNameGetter<V> jsonKeyNameGetter;

    public JsonbQueryCaster(SqlConnector sqlConnector, JsonbQueryChainer<P, U> jsonbQueryChainer, QueryChain<P> nativeQueryChain, ColumnNameGetter<C> columnNameGetter) {
        this.sqlConnector = sqlConnector;
        this.jsonbQueryChainer = jsonbQueryChainer;
        this.nativeQueryChain = nativeQueryChain;
        this.columnNameGetter = columnNameGetter;
    }

    public JsonbQueryCaster(SqlConnector sqlConnector, JsonbQueryChainer<P, U> jsonbQueryChainer, QueryChain<P> nativeQueryChain, ColumnNameGetter<C> columnNameGetter, ColumnNameGetter<V> jsonKeyNameGetter) {
        this(sqlConnector, jsonbQueryChainer, nativeQueryChain, columnNameGetter);
        this.jsonKeyNameGetter = jsonKeyNameGetter;
    }

    public QueryConditional<JsonbQueryChainer<P, U>, QueryChain<P>> castToBoolean() {
        return toQueryConditional(PostgresqlDataType.BOOLEAN);
    }

    public QueryConditional<JsonbQueryChainer<P, U>, QueryChain<P>> castToSmallint() {
        return toQueryConditional(PostgresqlDataType.SMALLINT);
    }

    public QueryConditional<JsonbQueryChainer<P, U>, QueryChain<P>> castToInteger() {
        return toQueryConditional(PostgresqlDataType.INTEGER);
    }

    public QueryConditional<JsonbQueryChainer<P, U>, QueryChain<P>> castToBigint() {
        return toQueryConditional(PostgresqlDataType.BIGINT);
    }

    public QueryConditional<JsonbQueryChainer<P, U>, QueryChain<P>> castToDecimal() {
        return toQueryConditional(PostgresqlDataType.DECIMAL);
    }

    public QueryConditional<JsonbQueryChainer<P, U>, QueryChain<P>> castToNumberic() {
        return toQueryConditional(PostgresqlDataType.NUMERIC);
    }

    public QueryConditional<JsonbQueryChainer<P, U>, QueryChain<P>> castToReal() {
        return toQueryConditional(PostgresqlDataType.REAL);
    }

    public QueryConditional<JsonbQueryChainer<P, U>, QueryChain<P>> castToChar() {
        return toQueryConditional(PostgresqlDataType.CHAR);
    }

    public QueryConditional<JsonbQueryChainer<P, U>, QueryChain<P>> castToVarchar() {
        return toQueryConditional(PostgresqlDataType.VARCHAR);
    }

    public QueryConditional<JsonbQueryChainer<P, U>, QueryChain<P>> castToText() {
        return toQueryConditional(PostgresqlDataType.TEXT);
    }

    public QueryConditional<JsonbQueryChainer<P, U>, QueryChain<P>> castToTimestamp() {
        return toQueryConditional(PostgresqlDataType.TIMESTAMP);
    }

    public QueryConditional<JsonbQueryChainer<P, U>, QueryChain<P>> castToJson() {
        return toQueryConditional(PostgresqlDataType.JSON);
    }

    public QueryConditional<JsonbQueryChainer<P, U>, QueryChain<P>> castToJsonb() {
        return toQueryConditional(PostgresqlDataType.JSONB);
    }

    private QueryConditional<JsonbQueryChainer<P, U>, QueryChain<P>> toQueryConditional(PostgresqlDataType dataType) {
        if (SqlConnector.AND == getSqlConnector()) {
            if (Nil.isNull(jsonKeyNameGetter)) {
                return new QueryConditional<>(getNativeQueryChain().and(new RawQueryCondition(getJsonKeyConditionSql(dataType, getColumnNameGetter()))), getJsonbQueryChainer());
            }
            return new QueryConditional<>(getNativeQueryChain().and(new RawQueryCondition(getJsonKeyConditionSql(dataType, getColumnNameGetter(), getJsonKeyNameGetter()))), getJsonbQueryChainer());
        }
        if (SqlConnector.OR == getSqlConnector()) {
            if (Nil.isNull(jsonKeyNameGetter)) {
                return new QueryConditional<>(getNativeQueryChain().or(new RawQueryCondition(getJsonKeyConditionSql(dataType, getColumnNameGetter()))), getJsonbQueryChainer());
            }
            return new QueryConditional<>(getNativeQueryChain().or(new RawQueryCondition(getJsonKeyConditionSql(dataType, getColumnNameGetter(), getJsonKeyNameGetter()))), getJsonbQueryChainer());
        }
        throw new LibraryJavaInternalException(Strings.format("{}cast json key to specified type failed because of the unsupported sql connector:[{}]", ModuleView.ORM_MYBATIS_SYSTEM, dataType));
    }

    private String getJsonKeyConditionSql(PostgresqlDataType dataType, ColumnNameGetter<C> columnNameGetter) {
        return Strings.format("{}::{}", getJsonQueryColumnName(columnNameGetter), dataType.getValue());
    }

    private String getJsonKeyConditionSql(PostgresqlDataType dataType, ColumnNameGetter<C> columnNameGetter, ColumnNameGetter<V> jsonKeyNameGetter) {
        return Strings.format("({} ->> '{}')::{}", getJsonQueryColumnName(columnNameGetter), MybatisFlexs.getFieldName(jsonKeyNameGetter), dataType.getValue());
    }

    private  String getJsonQueryColumnName(ColumnNameGetter<C> columnNameGetter) {
        String jsonQueryFieldName = MybatisFlexs.getFieldName(columnNameGetter);
        String jsonQueryColumnName = ColumnJsonbMappingAliasCache.get(columnNameGetter, jsonQueryFieldName);
        if (Nil.isNull(jsonQueryColumnName)) {
            jsonQueryColumnName = jsonQueryFieldName;
        }
        return jsonQueryColumnName;
    }

}