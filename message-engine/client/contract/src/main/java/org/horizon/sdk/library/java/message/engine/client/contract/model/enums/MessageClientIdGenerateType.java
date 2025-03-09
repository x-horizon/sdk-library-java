package org.horizon.sdk.library.java.message.engine.client.contract.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.horizon.sdk.library.java.message.engine.client.contract.strategy.MessageClientUniqueIdGenerateBySnowflakeStrategy;
import org.horizon.sdk.library.java.message.engine.client.contract.strategy.MessageClientUniqueIdGenerateByUUIDStrategy;
import org.horizon.sdk.library.java.message.engine.client.contract.strategy.MessageClientUniqueIdGenerateStrategy;

/**
 * @author wjm
 * @since 2024-05-30 10:54
 */
@Getter
@AllArgsConstructor
public enum MessageClientIdGenerateType {

    UUID(1, new MessageClientUniqueIdGenerateByUUIDStrategy()),
    SNOWFLAKE(2, new MessageClientUniqueIdGenerateBySnowflakeStrategy()),

    ;

    private final int code;

    private final MessageClientUniqueIdGenerateStrategy<?> strategy;

}