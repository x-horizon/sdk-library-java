// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.adapter;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import com.mybatisflex.core.constant.SqlConnector;
import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.query.QueryConditionBuilder;
import com.mybatisflex.core.query.RawQueryColumn;
import com.mybatisflex.core.query.RawQueryCondition;

/**
 * @author wjm
 * @since 2024-04-20 15:04
 */
@SuppressWarnings(SuppressWarningConstant.SERIAL)
public class QueryWrapperAdapter<R extends QueryWrapperAdapter<R>> extends com.mybatisflex.core.query.QueryWrapperAdapter<R> {

    public QueryConditionBuilder<R> where(QueryColumn queryColumn) {
        return and(queryColumn);
    }

    public QueryConditionBuilder<R> where(RawQueryCondition rawQueryCondition) {
        return and(rawQueryCondition);
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public QueryConditionBuilder<R> and(QueryColumn queryColumn) {
        return new QueryConditionBuilder<>((R) this, queryColumn, SqlConnector.AND);
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public QueryConditionBuilder<R> and(RawQueryCondition rawQueryCondition) {
        return new QueryConditionBuilder<>((R) this, new RawQueryColumn(rawQueryCondition.getContent()), SqlConnector.AND);
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public QueryConditionBuilder<R> or(QueryColumn queryColumn) {
        return new QueryConditionBuilder<>((R) this, queryColumn, SqlConnector.OR);
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public QueryConditionBuilder<R> or(RawQueryCondition rawQueryCondition) {
        return new QueryConditionBuilder<>((R) this, new RawQueryColumn(rawQueryCondition.getContent()), SqlConnector.OR);
    }

}