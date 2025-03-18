package org.horizon.sdk.library.java.orm.mybatis.flex.postgresql.chain;

import com.mybatisflex.core.constant.SqlConnector;
import lombok.AccessLevel;
import lombok.Getter;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.contract.model.base.PO;
import org.horizon.sdk.library.java.contract.model.base.POJO;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.chain.QueryChain;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.chain.QueryConditional;

/**
 * @author wjm
 * @since 2024-04-25 15:02
 */
@SuppressWarnings(SuppressWarningConstant.UNCHECKED)
public class JsonQueryCaster<P extends PO, PJ extends POJO> extends QueryCaster<PJ> {

    @Getter(AccessLevel.PROTECTED)
    private final JsonQueryChainer<P, PJ> queryChainer;

    public JsonQueryCaster(String sql, SqlConnector sqlConnector, QueryChain<PJ> nativeQueryChain, JsonQueryChainer<P, PJ> jsonQueryChainer) {
        super(sql, sqlConnector, nativeQueryChain);
        this.queryChainer = jsonQueryChainer;
    }

    @Override
    public QueryConditional<JsonQueryChainer<P, PJ>, QueryChain<PJ>> castToBoolean() {
        return (QueryConditional<JsonQueryChainer<P, PJ>, QueryChain<PJ>>) super.castToBoolean();
    }

    @Override
    public QueryConditional<JsonQueryChainer<P, PJ>, QueryChain<PJ>> castToSmallint() {
        return (QueryConditional<JsonQueryChainer<P, PJ>, QueryChain<PJ>>) super.castToSmallint();
    }

    @Override
    public QueryConditional<JsonQueryChainer<P, PJ>, QueryChain<PJ>> castToInteger() {
        return (QueryConditional<JsonQueryChainer<P, PJ>, QueryChain<PJ>>) super.castToInteger();
    }

    @Override
    public QueryConditional<JsonQueryChainer<P, PJ>, QueryChain<PJ>> castToBigint() {
        return (QueryConditional<JsonQueryChainer<P, PJ>, QueryChain<PJ>>) super.castToBigint();
    }

    @Override
    public QueryConditional<JsonQueryChainer<P, PJ>, QueryChain<PJ>> castToDecimal() {
        return (QueryConditional<JsonQueryChainer<P, PJ>, QueryChain<PJ>>) super.castToDecimal();
    }

    @Override
    public QueryConditional<JsonQueryChainer<P, PJ>, QueryChain<PJ>> castToNumberic() {
        return (QueryConditional<JsonQueryChainer<P, PJ>, QueryChain<PJ>>) super.castToNumberic();
    }

    @Override
    public QueryConditional<JsonQueryChainer<P, PJ>, QueryChain<PJ>> castToReal() {
        return (QueryConditional<JsonQueryChainer<P, PJ>, QueryChain<PJ>>) super.castToReal();
    }

    @Override
    public QueryConditional<JsonQueryChainer<P, PJ>, QueryChain<PJ>> castToChar() {
        return (QueryConditional<JsonQueryChainer<P, PJ>, QueryChain<PJ>>) super.castToChar();
    }

    @Override
    public QueryConditional<JsonQueryChainer<P, PJ>, QueryChain<PJ>> castToVarchar() {
        return (QueryConditional<JsonQueryChainer<P, PJ>, QueryChain<PJ>>) super.castToVarchar();
    }

    @Override
    public QueryConditional<JsonQueryChainer<P, PJ>, QueryChain<PJ>> castToText() {
        return (QueryConditional<JsonQueryChainer<P, PJ>, QueryChain<PJ>>) super.castToText();
    }

    @Override
    public QueryConditional<JsonQueryChainer<P, PJ>, QueryChain<PJ>> castToTimestamp() {
        return (QueryConditional<JsonQueryChainer<P, PJ>, QueryChain<PJ>>) super.castToTimestamp();
    }

    @Override
    public QueryConditional<JsonQueryChainer<P, PJ>, QueryChain<PJ>> castToJson() {
        return (QueryConditional<JsonQueryChainer<P, PJ>, QueryChain<PJ>>) super.castToJson();
    }

    @Override
    public QueryConditional<JsonQueryChainer<P, PJ>, QueryChain<PJ>> castToJsonb() {
        return (QueryConditional<JsonQueryChainer<P, PJ>, QueryChain<PJ>>) super.castToJsonb();
    }

}