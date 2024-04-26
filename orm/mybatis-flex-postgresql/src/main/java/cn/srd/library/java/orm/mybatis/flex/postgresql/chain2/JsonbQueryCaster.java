// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.chain2;

import cn.srd.library.java.contract.constant.database.PostgresqlDataType;
import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.contract.model.base.POJO;
import cn.srd.library.java.orm.mybatis.flex.base.chain.QueryChain;
import cn.srd.library.java.orm.mybatis.flex.base.chain.QueryConditional;
import cn.srd.library.java.tool.lang.text.Strings;
import com.mybatisflex.core.constant.SqlConnector;
import com.mybatisflex.core.query.RawQueryCondition;

/**
 * @author wjm
 * @since 2024-04-25 15:02
 */
public class JsonbQueryCaster<PJ extends POJO, P extends PO> {

    private final String sql;

    private final SqlConnector sqlConnector;

    private final QueryChain<PJ> nativeQueryChain;

    private final JsonbQueryChainer<PJ, P> jsonbQueryChainer;

    public JsonbQueryCaster(String sql, SqlConnector sqlConnector, QueryChain<PJ> nativeQueryChain, JsonbQueryChainer<PJ, P> jsonbQueryChainer) {
        this.sql = sql;
        this.sqlConnector = sqlConnector;
        this.nativeQueryChain = nativeQueryChain;
        this.jsonbQueryChainer = jsonbQueryChainer;
    }

    public QueryConditional<JsonbQueryChainer<PJ, P>, QueryChain<PJ>> castToBoolean() {
        return toQueryConditional(PostgresqlDataType.BOOLEAN);
    }

    public QueryConditional<JsonbQueryChainer<PJ, P>, QueryChain<PJ>> castToSmallint() {
        return toQueryConditional(PostgresqlDataType.SMALLINT);
    }

    public QueryConditional<JsonbQueryChainer<PJ, P>, QueryChain<PJ>> castToInteger() {
        return toQueryConditional(PostgresqlDataType.INTEGER);
    }

    public QueryConditional<JsonbQueryChainer<PJ, P>, QueryChain<PJ>> castToBigint() {
        return toQueryConditional(PostgresqlDataType.BIGINT);
    }

    public QueryConditional<JsonbQueryChainer<PJ, P>, QueryChain<PJ>> castToDecimal() {
        return toQueryConditional(PostgresqlDataType.DECIMAL);
    }

    public QueryConditional<JsonbQueryChainer<PJ, P>, QueryChain<PJ>> castToNumberic() {
        return toQueryConditional(PostgresqlDataType.NUMERIC);
    }

    public QueryConditional<JsonbQueryChainer<PJ, P>, QueryChain<PJ>> castToReal() {
        return toQueryConditional(PostgresqlDataType.REAL);
    }

    public QueryConditional<JsonbQueryChainer<PJ, P>, QueryChain<PJ>> castToChar() {
        return toQueryConditional(PostgresqlDataType.CHAR);
    }

    public QueryConditional<JsonbQueryChainer<PJ, P>, QueryChain<PJ>> castToVarchar() {
        return toQueryConditional(PostgresqlDataType.VARCHAR);
    }

    public QueryConditional<JsonbQueryChainer<PJ, P>, QueryChain<PJ>> castToText() {
        return toQueryConditional(PostgresqlDataType.TEXT);
    }

    public QueryConditional<JsonbQueryChainer<PJ, P>, QueryChain<PJ>> castToTimestamp() {
        return toQueryConditional(PostgresqlDataType.TIMESTAMP);
    }

    public QueryConditional<JsonbQueryChainer<PJ, P>, QueryChain<PJ>> castToJson() {
        return toQueryConditional(PostgresqlDataType.JSON);
    }

    public QueryConditional<JsonbQueryChainer<PJ, P>, QueryChain<PJ>> castToJsonb() {
        return toQueryConditional(PostgresqlDataType.JSONB);
    }

    private QueryConditional<JsonbQueryChainer<PJ, P>, QueryChain<PJ>> toQueryConditional(PostgresqlDataType dataType) {
        if (SqlConnector.AND == this.sqlConnector) {
            return new QueryConditional<>(this.jsonbQueryChainer, this.nativeQueryChain.and(new RawQueryCondition(castSQL(dataType))));
        }
        if (SqlConnector.OR == this.sqlConnector) {
            return new QueryConditional<>(this.jsonbQueryChainer, this.nativeQueryChain.or(new RawQueryCondition(castSQL(dataType))));
        }
        throw new LibraryJavaInternalException(Strings.format("{}cast json key to specified type failed because of the unsupported sql connector:[{}]", ModuleView.ORM_MYBATIS_SYSTEM, dataType));
    }

    private String castSQL(PostgresqlDataType dataType) {
        return Strings.format("{}::{}", this.sql, dataType.getValue());
    }

}