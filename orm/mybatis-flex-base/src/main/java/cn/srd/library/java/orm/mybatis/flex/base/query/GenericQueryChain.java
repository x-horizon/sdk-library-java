// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.query;

import cn.srd.library.java.orm.contract.model.base.PO;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.QueryChain;

import java.io.Serial;

/**
 * @author wjm
 * @since 2023-11-28 22:57
 */
public class GenericQueryChain<T extends PO> extends QueryChain<T> {

    @Serial private static final long serialVersionUID = 7214746557544965890L;

    public GenericQueryChain(BaseMapper<T> baseMapper) {
        super(baseMapper);
    }

}
