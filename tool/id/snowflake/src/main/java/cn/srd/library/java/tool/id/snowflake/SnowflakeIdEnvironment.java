// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.id.snowflake;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjm
 * @since 2023-11-13 10:37
 */
@Getter
@AllArgsConstructor
public enum SnowflakeIdEnvironment {

    STAND_ALONE_SINGLE_INSTANCE(new SnowflakeIdOnStandAloneSingleInstanceStrategy()),
    STAND_ALONE_MULTIPLE_INSTANCE(new SnowflakeIdOnStandAloneMultipleInstanceStrategy()),

    ;

    private final SnowflakeIdEnvironmentStrategy strategy;

}
