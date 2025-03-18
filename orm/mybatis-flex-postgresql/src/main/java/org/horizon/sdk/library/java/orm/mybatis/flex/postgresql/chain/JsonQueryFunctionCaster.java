package org.horizon.sdk.library.java.orm.mybatis.flex.postgresql.chain;

import com.mybatisflex.core.constant.SqlConnector;
import lombok.AccessLevel;
import lombok.Getter;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.contract.model.base.POJO;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.chain.QueryChain;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.chain.QueryConditional;

/**
 * @author wjm
 * @since 2024-04-25 15:02
 */
@SuppressWarnings(SuppressWarningConstant.UNCHECKED)
public class JsonQueryFunctionCaster<PJ extends POJO> extends QueryCaster<PJ> {

    @Getter(AccessLevel.PROTECTED)
    private final JsonQueryFunctionChainer<PJ> queryChainer;

    public JsonQueryFunctionCaster(String sql, SqlConnector sqlConnector, QueryChain<PJ> nativeQueryChain, JsonQueryFunctionChainer<PJ> jsonQueryChainer) {
        super(sql, sqlConnector, nativeQueryChain);
        this.queryChainer = jsonQueryChainer;
    }

    @Override
    public QueryConditional<JsonQueryFunctionChainer<PJ>, QueryChain<PJ>> castToBoolean() {
        return (QueryConditional<JsonQueryFunctionChainer<PJ>, QueryChain<PJ>>) super.castToBoolean();
    }

    @Override
    public QueryConditional<JsonQueryFunctionChainer<PJ>, QueryChain<PJ>> castToSmallint() {
        return (QueryConditional<JsonQueryFunctionChainer<PJ>, QueryChain<PJ>>) super.castToSmallint();
    }

    @Override
    public QueryConditional<JsonQueryFunctionChainer<PJ>, QueryChain<PJ>> castToInteger() {
        return (QueryConditional<JsonQueryFunctionChainer<PJ>, QueryChain<PJ>>) super.castToInteger();
    }

    @Override
    public QueryConditional<JsonQueryFunctionChainer<PJ>, QueryChain<PJ>> castToBigint() {
        return (QueryConditional<JsonQueryFunctionChainer<PJ>, QueryChain<PJ>>) super.castToBigint();
    }

    @Override
    public QueryConditional<JsonQueryFunctionChainer<PJ>, QueryChain<PJ>> castToDecimal() {
        return (QueryConditional<JsonQueryFunctionChainer<PJ>, QueryChain<PJ>>) super.castToDecimal();
    }

    @Override
    public QueryConditional<JsonQueryFunctionChainer<PJ>, QueryChain<PJ>> castToNumberic() {
        return (QueryConditional<JsonQueryFunctionChainer<PJ>, QueryChain<PJ>>) super.castToNumberic();
    }

    @Override
    public QueryConditional<JsonQueryFunctionChainer<PJ>, QueryChain<PJ>> castToReal() {
        return (QueryConditional<JsonQueryFunctionChainer<PJ>, QueryChain<PJ>>) super.castToReal();
    }

    @Override
    public QueryConditional<JsonQueryFunctionChainer<PJ>, QueryChain<PJ>> castToChar() {
        return (QueryConditional<JsonQueryFunctionChainer<PJ>, QueryChain<PJ>>) super.castToChar();
    }

    @Override
    public QueryConditional<JsonQueryFunctionChainer<PJ>, QueryChain<PJ>> castToVarchar() {
        return (QueryConditional<JsonQueryFunctionChainer<PJ>, QueryChain<PJ>>) super.castToVarchar();
    }

    @Override
    public QueryConditional<JsonQueryFunctionChainer<PJ>, QueryChain<PJ>> castToText() {
        return (QueryConditional<JsonQueryFunctionChainer<PJ>, QueryChain<PJ>>) super.castToText();
    }

    @Override
    public QueryConditional<JsonQueryFunctionChainer<PJ>, QueryChain<PJ>> castToTimestamp() {
        return (QueryConditional<JsonQueryFunctionChainer<PJ>, QueryChain<PJ>>) super.castToTimestamp();
    }

    @Override
    public QueryConditional<JsonQueryFunctionChainer<PJ>, QueryChain<PJ>> castToJson() {
        return (QueryConditional<JsonQueryFunctionChainer<PJ>, QueryChain<PJ>>) super.castToJson();
    }

    @Override
    public QueryConditional<JsonQueryFunctionChainer<PJ>, QueryChain<PJ>> castToJsonb() {
        return (QueryConditional<JsonQueryFunctionChainer<PJ>, QueryChain<PJ>>) super.castToJsonb();
    }

}