package cn.srd.library.java.message.engine.client.contract.model.enums;

import cn.srd.library.java.message.engine.client.contract.strategy.UniqueClientIdGenerateBySnowflakeStrategy;
import cn.srd.library.java.message.engine.client.contract.strategy.UniqueClientIdGenerateByUUIDStrategy;
import cn.srd.library.java.message.engine.client.contract.strategy.UniqueClientIdGenerateStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjm
 * @since 2024-05-30 10:54
 */
@Getter
@AllArgsConstructor
public enum ClientIdGenerateType {

    UUID(1, new UniqueClientIdGenerateByUUIDStrategy()),
    SNOWFLAKE(2, new UniqueClientIdGenerateBySnowflakeStrategy()),

    ;

    private final int code;

    private final UniqueClientIdGenerateStrategy<?> strategy;

}