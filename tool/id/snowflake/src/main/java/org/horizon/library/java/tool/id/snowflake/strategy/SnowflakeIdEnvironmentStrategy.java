package org.horizon.library.java.tool.id.snowflake.strategy;

import org.horizon.library.java.tool.id.snowflake.autoconfigure.EnableSnowflakeId;

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