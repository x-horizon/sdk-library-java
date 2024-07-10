// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.chain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wjm
 * @since 2023-12-05 16:38
 */
@Getter
@Slf4j
public abstract class BaseChainer {

    private boolean allowToRunSql = true;

    public void setAllowToRunSql(boolean allowToRunSql) {
        if (this.allowToRunSql) {
            this.allowToRunSql = allowToRunSql;
        }
    }

}