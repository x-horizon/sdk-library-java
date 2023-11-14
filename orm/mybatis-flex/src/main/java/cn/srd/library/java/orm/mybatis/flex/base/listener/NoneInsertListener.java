// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.listener;

import cn.srd.library.java.contract.model.throwable.UnsupportedException;
import cn.srd.library.java.tool.lang.text.Strings;

/**
 * the invalid {@link BaseInsertListener} implement
 *
 * @author wjm
 * @since 2023-11-13 21:14
 */
public class NoneInsertListener implements BaseInsertListener<Void> {

    @Override
    public Class<Void> getEntityType() {
        throw new UnsupportedException(Strings.format("{}unsupported insert listener function!"));
    }

    @Override
    public void action(Void entity) {
        throw new UnsupportedException(Strings.format("{}unsupported insert listener function!"));
    }

}
