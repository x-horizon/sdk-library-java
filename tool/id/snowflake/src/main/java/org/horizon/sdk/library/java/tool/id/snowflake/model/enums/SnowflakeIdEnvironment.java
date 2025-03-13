package org.horizon.sdk.library.java.tool.id.snowflake.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.horizon.sdk.library.java.tool.id.snowflake.autoconfigure.EnableSnowflakeId;
import org.horizon.sdk.library.java.tool.id.snowflake.strategy.SnowflakeIdEnvironmentStrategy;
import org.horizon.sdk.library.java.tool.id.snowflake.strategy.SnowflakeIdOnMultipleNodeStrategy;
import org.horizon.sdk.library.java.tool.id.snowflake.strategy.SnowflakeIdOnSingleNodeStrategy;

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