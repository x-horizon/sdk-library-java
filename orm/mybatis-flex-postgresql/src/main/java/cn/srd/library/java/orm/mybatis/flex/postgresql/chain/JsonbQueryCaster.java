// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.chain;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.model.base.PO;
import cn.srd.library.java.contract.model.base.POJO;
import cn.srd.library.java.orm.mybatis.flex.base.chain.QueryChain;
import cn.srd.library.java.orm.mybatis.flex.base.chain.QueryConditional;
import com.mybatisflex.core.constant.SqlConnector;
import lombok.AccessLevel;
import lombok.Getter;

/**
 * @author wjm
 * @since 2024-04-25 15:02
 */
@SuppressWarnings(SuppressWarningConstant.UNCHECKED)
public class JsonbQueryCaster<P extends PO, PJ extends POJO> extends QueryCaster<PJ> {

    @Getter(AccessLevel.PROTECTED)
    private final JsonbQueryChainer<P, PJ> queryChainer;

    public JsonbQueryCaster(String sql, SqlConnector sqlConnector, QueryChain<PJ> nativeQueryChain, JsonbQueryChainer<P, PJ> jsonbQueryChainer) {
        super(sql, sqlConnector, nativeQueryChain);
        this.queryChainer = jsonbQueryChainer;
    }

    @Override
    public QueryConditional<JsonbQueryChainer<P, PJ>, QueryChain<PJ>> castToBoolean() {
        return (QueryConditional<JsonbQueryChainer<P, PJ>, QueryChain<PJ>>) super.castToBoolean();
    }

    @Override
    public QueryConditional<JsonbQueryChainer<P, PJ>, QueryChain<PJ>> castToSmallint() {
        return (QueryConditional<JsonbQueryChainer<P, PJ>, QueryChain<PJ>>) super.castToSmallint();
    }

    @Override
    public QueryConditional<JsonbQueryChainer<P, PJ>, QueryChain<PJ>> castToInteger() {
        return (QueryConditional<JsonbQueryChainer<P, PJ>, QueryChain<PJ>>) super.castToInteger();
    }

    @Override
    public QueryConditional<JsonbQueryChainer<P, PJ>, QueryChain<PJ>> castToBigint() {
        return (QueryConditional<JsonbQueryChainer<P, PJ>, QueryChain<PJ>>) super.castToBigint();
    }

    @Override
    public QueryConditional<JsonbQueryChainer<P, PJ>, QueryChain<PJ>> castToDecimal() {
        return (QueryConditional<JsonbQueryChainer<P, PJ>, QueryChain<PJ>>) super.castToDecimal();
    }

    @Override
    public QueryConditional<JsonbQueryChainer<P, PJ>, QueryChain<PJ>> castToNumberic() {
        return (QueryConditional<JsonbQueryChainer<P, PJ>, QueryChain<PJ>>) super.castToNumberic();
    }

    @Override
    public QueryConditional<JsonbQueryChainer<P, PJ>, QueryChain<PJ>> castToReal() {
        return (QueryConditional<JsonbQueryChainer<P, PJ>, QueryChain<PJ>>) super.castToReal();
    }

    @Override
    public QueryConditional<JsonbQueryChainer<P, PJ>, QueryChain<PJ>> castToChar() {
        return (QueryConditional<JsonbQueryChainer<P, PJ>, QueryChain<PJ>>) super.castToChar();
    }

    @Override
    public QueryConditional<JsonbQueryChainer<P, PJ>, QueryChain<PJ>> castToVarchar() {
        return (QueryConditional<JsonbQueryChainer<P, PJ>, QueryChain<PJ>>) super.castToVarchar();
    }

    @Override
    public QueryConditional<JsonbQueryChainer<P, PJ>, QueryChain<PJ>> castToText() {
        return (QueryConditional<JsonbQueryChainer<P, PJ>, QueryChain<PJ>>) super.castToText();
    }

    @Override
    public QueryConditional<JsonbQueryChainer<P, PJ>, QueryChain<PJ>> castToTimestamp() {
        return (QueryConditional<JsonbQueryChainer<P, PJ>, QueryChain<PJ>>) super.castToTimestamp();
    }

    @Override
    public QueryConditional<JsonbQueryChainer<P, PJ>, QueryChain<PJ>> castToJson() {
        return (QueryConditional<JsonbQueryChainer<P, PJ>, QueryChain<PJ>>) super.castToJson();
    }

    @Override
    public QueryConditional<JsonbQueryChainer<P, PJ>, QueryChain<PJ>> castToJsonb() {
        return (QueryConditional<JsonbQueryChainer<P, PJ>, QueryChain<PJ>>) super.castToJsonb();
    }

}