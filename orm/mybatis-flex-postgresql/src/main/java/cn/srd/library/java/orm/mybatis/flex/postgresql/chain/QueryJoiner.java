// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.chain;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.mybatis.flex.base.chain.QueryChainer;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.mybatisflex.core.query.Joiner;
import com.mybatisflex.core.query.QueryChain;

/**
 * @author wjm
 * @since 2024-04-19 00:10
 */
@CanIgnoreReturnValue
@SuppressWarnings(SuppressWarningConstant.UNUSED)
public class QueryJoiner<P extends PO, Q extends QueryChainer<P>> extends cn.srd.library.java.orm.mybatis.flex.base.chain.QueryJoiner<P, Q> {

    protected QueryJoiner(Joiner<QueryChain<P>> nativeQueryJoiner, Q queryChainer) {
        super(nativeQueryJoiner, queryChainer);
    }

}