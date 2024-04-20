// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.adapter;

import com.mybatisflex.core.constant.SqlConnector;
import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.query.QueryConditionBuilder;

import java.io.Serial;

/**
 * @author wjm
 * @since 2024-04-20 15:04
 */
public class QueryWrapperAdapter2<R extends QueryWrapperAdapter2<R>> extends com.mybatisflex.core.query.QueryWrapperAdapter<R> {

    @Serial private static final long serialVersionUID = 971536476435856761L;

    public QueryConditionBuilder<R> where(QueryColumn queryColumn) {
        return new QueryConditionBuilder<>((R) this, queryColumn, SqlConnector.AND);
    }

    public QueryConditionBuilder<R> and(QueryColumn queryColumn) {
        return new QueryConditionBuilder<>((R) this, queryColumn, SqlConnector.AND);
    }

    public QueryConditionBuilder<R> or(QueryColumn queryColumn) {
        return new QueryConditionBuilder<>((R) this, queryColumn, SqlConnector.OR);
    }

}