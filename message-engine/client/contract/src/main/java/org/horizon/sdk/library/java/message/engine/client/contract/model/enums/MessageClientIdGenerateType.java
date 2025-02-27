package org.horizon.sdk.library.java.message.engine.client.contract.model.enums;

import org.horizon.sdk.library.java.message.engine.client.contract.strategy.MessageClientUniqueIdGenerateBySnowflakeStrategy;
import org.horizon.sdk.library.java.message.engine.client.contract.strategy.MessageClientUniqueIdGenerateByUUIDStrategy;
import org.horizon.sdk.library.java.message.engine.client.contract.strategy.MessageClientUniqueIdGenerateStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;

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