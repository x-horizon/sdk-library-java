// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.id.snowflake.strategy;

import cn.srd.library.java.tool.id.snowflake.autoconfigure.EnableSnowflakeId;

/**
 * current node instance environment strategy
 *
 * @author wjm
 * @since 2023-11-13 10:42
 */
public interface SnowflakeIdEnvironmentStrategy {

    /**
     * get worker id based on different environments
     *
     * @param snowflakeIdConfig the snowflake id config
     * @return worker id
     */
    short getWorkerId(EnableSnowflakeId snowflakeIdConfig);

}