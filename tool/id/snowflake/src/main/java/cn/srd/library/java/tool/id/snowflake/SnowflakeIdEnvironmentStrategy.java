// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.id.snowflake;

/**
 * @author wjm
 * @since 2023-11-13 10:42
 */
public interface SnowflakeIdEnvironmentStrategy {

    short getWorkerId(EnableSnowflakeId snowflakeIdConfig);

}


