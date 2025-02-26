package org.horizon.library.java.message.engine.client.contract.strategy;

import org.horizon.library.java.tool.id.snowflake.support.SnowflakeIds;

/**
 * @author wjm
 * @since 2024-05-30 10:54
 */
public class MessageClientUniqueIdGenerateBySnowflakeStrategy implements MessageClientUniqueIdGenerateStrategy<Long> {

    @Override
    public Long getId() {
        return SnowflakeIds.get();
    }

}