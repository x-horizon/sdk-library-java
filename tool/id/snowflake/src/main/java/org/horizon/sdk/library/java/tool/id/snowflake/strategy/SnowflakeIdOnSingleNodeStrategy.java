package org.horizon.sdk.library.java.tool.id.snowflake.strategy;

import org.horizon.sdk.library.java.tool.id.snowflake.autoconfigure.EnableSnowflakeId;

/**
 * single node strategy
 *
 * @author wjm
 * @since 2023-11-13 10:42
 */
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