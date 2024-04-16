// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.chain;

import cn.srd.library.java.orm.contract.model.base.PO;
import com.mybatisflex.core.BaseMapper;

/**
 * @author wjm
 * @since 2023-12-05 16:38
 */
public abstract class BaseChainer<P extends PO> {

    protected abstract BaseMapper<P> getNativeBaseMapper();

}