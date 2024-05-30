// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.id.snowflake.strategy;

import cn.srd.library.java.tool.id.snowflake.autoconfigure.EnableSnowflakeId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * single node strategy
 *
 * @author wjm
 * @since 2023-11-13 10:42
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SnowflakeIdOnSingleNodeStrategy implements SnowflakeIdEnvironmentStrategy {

    /**
     * the default worker id
     */
    private static final short DEFAULT_WORKER_ID = 1;

    @Override
    public short getWorkerId(EnableSnowflakeId snowflakeIdConfig) {
        return DEFAULT_WORKER_ID;
    }

}