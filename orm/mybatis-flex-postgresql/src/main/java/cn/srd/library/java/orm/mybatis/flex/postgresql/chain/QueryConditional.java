// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.chain;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.mybatis.flex.base.chain.BaseChainer;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.mybatisflex.core.query.QueryConditionBuilder;
import com.mybatisflex.core.query.QueryWrapper;

/**
 * @param <P> the entity extends {@link PO}
 * @param <Q> the chainer extends {@link BaseChainer}
 * @param <N> the wrapper extends {@link QueryWrapper}
 * @author wjm
 * @since 2024-04-18 23:59
 */
@CanIgnoreReturnValue
@SuppressWarnings(SuppressWarningConstant.UNUSED)
public class QueryConditional<P extends PO, Q extends BaseChainer<P>, N extends QueryWrapper> extends cn.srd.library.java.orm.mybatis.flex.base.chain.QueryConditional<P, Q, N> {

    public QueryConditional(QueryConditionBuilder<N> nativeQueryConditional, Q chainer) {
        super(nativeQueryConditional, chainer);
    }

}