// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.listener;

import cn.srd.library.java.contract.model.throwable.UnsupportedException;
import cn.srd.library.java.tool.lang.text.Strings;
import com.mybatisflex.core.FlexGlobalConfig;

/**
 * the invalid {@link BaseInsertListener} implement, will not add it to {@link FlexGlobalConfig#getEntityInsertListeners()}.
 *
 * @author wjm
 * @since 2023-11-13 21:14
 */
public class UnsupportedInsertListener implements BaseInsertListener<Void> {

    @Override
    public Class<Void> getEntityType() {
        throw new UnsupportedException(Strings.format("{}unsupported insert listener, please check!"));
    }

    @Override
    public void action(Void entity) {
        throw new UnsupportedException(Strings.format("{}unsupported insert listener, please check!"));
    }

}