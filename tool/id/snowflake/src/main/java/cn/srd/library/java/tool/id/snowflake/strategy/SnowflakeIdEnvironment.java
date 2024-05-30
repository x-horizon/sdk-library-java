// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.id.snowflake.strategy;

import cn.srd.library.java.tool.id.snowflake.autoconfigure.EnableSnowflakeId;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * current node instance environment
 *
 * @author wjm
 * @see EnableSnowflakeId
 * @since 2023-11-13 10:37
 */
@Getter
@AllArgsConstructor
public enum SnowflakeIdEnvironment {

    SINGLE_NODE(new SnowflakeIdOnSingleNodeStrategy()),
    MULTIPLE_NODE(new SnowflakeIdOnMultipleNodeStrategy()),

    ;

    private final SnowflakeIdEnvironmentStrategy strategy;

}