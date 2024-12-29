package cn.srd.library.java.tool.id.snowflake.model.enums;

import cn.srd.library.java.tool.id.snowflake.autoconfigure.EnableSnowflakeId;
import cn.srd.library.java.tool.id.snowflake.strategy.SnowflakeIdEnvironmentStrategy;
import cn.srd.library.java.tool.id.snowflake.strategy.SnowflakeIdOnMultipleNodeStrategy;
import cn.srd.library.java.tool.id.snowflake.strategy.SnowflakeIdOnSingleNodeStrategy;
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