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
public class JsonbQueryCaster<PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO, P extends PO> {

    private final SqlConnector sqlConnector;

    private final JsonbQueryChainer<PJ1, P> jsonbQueryChainer;

    private final QueryChain<PJ1> nativeQueryChain;

    private final ColumnNameGetter<PJ2> columnNameGetter;

    private ColumnNameGetter<PJ3> jsonKeyNameGetter;

    public JsonbQueryCaster(SqlConnector sqlConnector, JsonbQueryChainer<PJ1, P> jsonbQueryChainer, QueryChain<PJ1> nativeQueryChain, ColumnNameGetter<PJ2> columnNameGetter) {
        this.sqlConnector = sqlConnector;
        this.jsonbQueryChainer = jsonbQueryChainer;
        this.nativeQueryChain = nativeQueryChain;
        this.columnNameGetter = columnNameGetter;
    }

    public JsonbQueryCaster(SqlConnector sqlConnector, JsonbQueryChainer<PJ1, P> jsonbQueryChainer, QueryChain<PJ1> nativeQueryChain, ColumnNameGetter<PJ2> columnNameGetter, ColumnNameGetter<PJ3> jsonKeyNameGetter) {
        this(sqlConnector, jsonbQueryChainer, nativeQueryChain, columnNameGetter);
        this.jsonKeyNameGetter = jsonKeyNameGetter;
    }

    public QueryConditional<JsonbQueryChainer<PJ1, P>, QueryChain<PJ1>> castToBoolean() {
        return toQueryConditional(PostgresqlDataType.BOOLEAN);
    }

    public QueryConditional<JsonbQueryChainer<PJ1, P>, QueryChain<PJ1>> castToSmallint() {
        return toQueryConditional(PostgresqlDataType.SMALLINT);
    }

    public QueryConditional<JsonbQueryChainer<PJ1, P>, QueryChain<PJ1>> castToInteger() {
        return toQueryConditional(PostgresqlDataType.INTEGER);
    }

    public QueryConditional<JsonbQueryChainer<PJ1, P>, QueryChain<PJ1>> castToBigint() {
        return toQueryConditional(PostgresqlDataType.BIGINT);
    }

    public QueryConditional<JsonbQueryChainer<PJ1, P>, QueryChain<PJ1>> castToDecimal() {
        return toQueryConditional(PostgresqlDataType.DECIMAL);
    }

    public QueryConditional<JsonbQueryChainer<PJ1, P>, QueryChain<PJ1>> castToNumberic() {
        return toQueryConditional(PostgresqlDataType.NUMERIC);
    }

    public QueryConditional<JsonbQueryChainer<PJ1, P>, QueryChain<PJ1>> castToReal() {
        return toQueryConditional(PostgresqlDataType.REAL);
    }

    public QueryConditional<JsonbQueryChainer<PJ1, P>, QueryChain<PJ1>> castToChar() {
        return toQueryConditional(PostgresqlDataType.CHAR);
    }

    public QueryConditional<JsonbQueryChainer<PJ1, P>, QueryChain<PJ1>> castToVarchar() {
        return toQueryConditional(PostgresqlDataType.VARCHAR);
    }

    public QueryConditional<JsonbQueryChainer<PJ1, P>, QueryChain<PJ1>> castToText() {
        return toQueryConditional(PostgresqlDataType.TEXT);
    }

    public QueryConditional<JsonbQueryChainer<PJ1, P>, QueryChain<PJ1>> castToTimestamp() {
        return toQueryConditional(PostgresqlDataType.TIMESTAMP);
    }

    public QueryConditional<JsonbQueryChainer<PJ1, P>, QueryChain<PJ1>> castToJson() {
        return toQueryConditional(PostgresqlDataType.JSON);
    }

    public QueryConditional<JsonbQueryChainer<PJ1, P>, QueryChain<PJ1>> castToJsonb() {
        return toQueryConditional(PostgresqlDataType.JSONB);
    }

    private QueryConditional<JsonbQueryChainer<PJ1, P>, QueryChain<PJ1>> toQueryConditional(PostgresqlDataType dataType) {
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

    private String getJsonKeyConditionSql(PostgresqlDataType dataType, ColumnNameGetter<PJ2> columnNameGetter) {
        return Strings.format("{}::{}", getJsonQueryColumnName(columnNameGetter), dataType.getValue());
    }

    private String getJsonKeyConditionSql(PostgresqlDataType dataType, ColumnNameGetter<PJ2> columnNameGetter, ColumnNameGetter<PJ3> jsonKeyNameGetter) {
        return Strings.format("({} ->> '{}')::{}", getJsonQueryColumnName(columnNameGetter), MybatisFlexs.getFieldName(jsonKeyNameGetter), dataType.getValue());
    }

    private String getJsonQueryColumnName(ColumnNameGetter<PJ2> columnNameGetter) {
        String jsonQueryFieldName = MybatisFlexs.getFieldName(columnNameGetter);
        String jsonQueryColumnName = ColumnJsonbMappingAliasCache.get(columnNameGetter, jsonQueryFieldName);
        if (Nil.isNull(jsonQueryColumnName)) {
            jsonQueryColumnName = jsonQueryFieldName;
        }
        return jsonQueryColumnName;
    }

}