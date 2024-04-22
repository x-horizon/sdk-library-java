// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.adapter;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import com.mybatisflex.core.constant.SqlConnector;
import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.query.QueryConditionBuilder;
import com.mybatisflex.core.query.RawQueryCondition;

import java.io.Serial;

/**
 * @author wjm
 * @since 2024-04-20 15:04
 */
public class QueryWrapperAdapter<R extends QueryWrapperAdapter<R>> extends com.mybatisflex.core.query.QueryWrapperAdapter<R> {

    @Serial private static final long serialVersionUID = 971536476435856761L;

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public QueryConditionBuilder<R> where(QueryColumn queryColumn) {
        return new QueryConditionBuilder<>((R) this, queryColumn, SqlConnector.AND);
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public QueryConditionBuilder<R> and(QueryColumn queryColumn) {
        return new QueryConditionBuilder<>((R) this, queryColumn, SqlConnector.AND);
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public QueryConditionBuilder<R> and(RawQueryCondition rawQueryCondition) {
        QueryColumn queryColumn = new QueryColumn();
        queryColumn.setName(rawQueryCondition.getContent());
        return new QueryConditionBuilder<>((R) this, queryColumn, SqlConnector.AND);
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public QueryConditionBuilder<R> or(QueryColumn queryColumn) {
        return new QueryConditionBuilder<>((R) this, queryColumn, SqlConnector.OR);
    }

}