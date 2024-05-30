package cn.srd.library.java.message.engine.contract.strategy;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjm
 * @since 2024-05-30 10:54
 */
@Getter
@AllArgsConstructor
public enum UniqueClientIdGenerateType {

    UUID(1, new UniqueClientIdGenerateByUUIDStrategy()),
    SNOWFLAKE(2, new UniqueClientIdGenerateBySnowflakeStrategy()),

    ;

    private final int code;

    private final UniqueClientIdGenerateStrategy<?> strategy;

}