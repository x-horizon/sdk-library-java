// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.chain;

import cn.srd.library.java.contract.constant.database.PostgresqlDataType;
import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.orm.contract.model.base.POJO;
import cn.srd.library.java.orm.mybatis.flex.base.chain.BaseQueryChainer;
import cn.srd.library.java.orm.mybatis.flex.base.chain.QueryChain;
import cn.srd.library.java.orm.mybatis.flex.base.chain.QueryConditional;
import cn.srd.library.java.tool.lang.text.Strings;
import com.mybatisflex.core.constant.SqlConnector;
import com.mybatisflex.core.query.RawQueryCondition;

/**
 * @author wjm
 * @since 2024-04-25 15:02
 */
public abstract class QueryCaster<PJ extends POJO> {

    protected final String sql;

    protected final SqlConnector sqlConnector;

    protected final QueryChain<PJ> nativeQueryChain;

    protected abstract BaseQueryChainer<PJ> getQueryChainer();

    protected QueryCaster(String sql, SqlConnector sqlConnector, QueryChain<PJ> nativeQueryChain) {
        this.sql = sql;
        this.sqlConnector = sqlConnector;
        this.nativeQueryChain = nativeQueryChain;
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public QueryConditional<? extends BaseQueryChainer<PJ>, QueryChain<PJ>> castToBoolean() {
        return toQueryConditional(PostgresqlDataType.BOOLEAN);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public QueryConditional<? extends BaseQueryChainer<PJ>, QueryChain<PJ>> castToSmallint() {
        return toQueryConditional(PostgresqlDataType.SMALLINT);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public QueryConditional<? extends BaseQueryChainer<PJ>, QueryChain<PJ>> castToInteger() {
        return toQueryConditional(PostgresqlDataType.INTEGER);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public QueryConditional<? extends BaseQueryChainer<PJ>, QueryChain<PJ>> castToBigint() {
        return toQueryConditional(PostgresqlDataType.BIGINT);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public QueryConditional<? extends BaseQueryChainer<PJ>, QueryChain<PJ>> castToDecimal() {
        return toQueryConditional(PostgresqlDataType.DECIMAL);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public QueryConditional<? extends BaseQueryChainer<PJ>, QueryChain<PJ>> castToNumberic() {
        return toQueryConditional(PostgresqlDataType.NUMERIC);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public QueryConditional<? extends BaseQueryChainer<PJ>, QueryChain<PJ>> castToReal() {
        return toQueryConditional(PostgresqlDataType.REAL);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public QueryConditional<? extends BaseQueryChainer<PJ>, QueryChain<PJ>> castToChar() {
        return toQueryConditional(PostgresqlDataType.CHAR);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public QueryConditional<? extends BaseQueryChainer<PJ>, QueryChain<PJ>> castToVarchar() {
        return toQueryConditional(PostgresqlDataType.VARCHAR);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public QueryConditional<? extends BaseQueryChainer<PJ>, QueryChain<PJ>> castToText() {
        return toQueryConditional(PostgresqlDataType.TEXT);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public QueryConditional<? extends BaseQueryChainer<PJ>, QueryChain<PJ>> castToTimestamp() {
        return toQueryConditional(PostgresqlDataType.TIMESTAMP);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public QueryConditional<? extends BaseQueryChainer<PJ>, QueryChain<PJ>> castToJson() {
        return toQueryConditional(PostgresqlDataType.JSON);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public QueryConditional<? extends BaseQueryChainer<PJ>, QueryChain<PJ>> castToJsonb() {
        return toQueryConditional(PostgresqlDataType.JSONB);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    private QueryConditional<? extends BaseQueryChainer<PJ>, QueryChain<PJ>> toQueryConditional(PostgresqlDataType dataType) {
        if (SqlConnector.AND == this.sqlConnector) {
            return new QueryConditional<>(getQueryChainer(), this.nativeQueryChain.and(new RawQueryCondition(castSQL(dataType))));
        }
        if (SqlConnector.OR == this.sqlConnector) {
            return new QueryConditional<>(getQueryChainer(), this.nativeQueryChain.or(new RawQueryCondition(castSQL(dataType))));
        }
        throw new LibraryJavaInternalException(Strings.format("{}cast json key to specified type failed because of the unsupported sql connector:[{}]", ModuleView.ORM_MYBATIS_SYSTEM, dataType));
    }

    private String castSQL(PostgresqlDataType dataType) {
        return Strings.format("{}::{}", this.sql, dataType.getValue());
    }

}