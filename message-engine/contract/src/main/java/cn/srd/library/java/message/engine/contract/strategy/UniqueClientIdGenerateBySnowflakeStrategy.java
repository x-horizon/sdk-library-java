// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.contract.strategy;

import cn.srd.library.java.tool.id.snowflake.support.SnowflakeIds;

/**
 * @author wjm
 * @since 2024-05-30 10:54
 */
public class UniqueClientIdGenerateBySnowflakeStrategy implements UniqueClientIdGenerateStrategy<Long> {

    @Override
    public Long getId() {
        return SnowflakeIds.get();
    }

}