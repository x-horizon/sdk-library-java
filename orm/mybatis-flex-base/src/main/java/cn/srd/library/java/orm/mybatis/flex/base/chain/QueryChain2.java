// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.chain;

import com.mybatisflex.core.BaseMapper;

import java.io.Serial;

/**
 * @author wjm
 * @since 2024-04-20 15:08
 */
public class QueryChain2<T> extends com.mybatisflex.core.query.QueryChain<T> {

    @Serial private static final long serialVersionUID = 7957000468703564933L;

    public QueryChain2(BaseMapper<T> baseMapper) {
        super(baseMapper);
    }

}