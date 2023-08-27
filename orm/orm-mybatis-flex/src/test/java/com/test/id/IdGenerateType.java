package com.test.id;

import com.mybatisflex.annotation.KeyType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IdGenerateType {

    AUTO_INCREMENT(KeyType.Auto, IdGenerateByAutoIncrementStrategy.INSTANCE),
    UUID(KeyType.Generator, IdGenerateByUUIDStrategy.INSTANCE),
    SNOWFLAKE(KeyType.Generator, IdGenerateBySnowflakeStrategy.INSTANCE),
    SQL(KeyType.Sequence, IdGenerateBySQLStrategy.INSTANCE),
    CUSTOMER(KeyType.Generator, IdGenerateByCustomerStrategy.INSTANCE),
    NONE(KeyType.None, IdGenerateByNoneStrategy.INSTANCE),
    ;

    private final KeyType nativeIdType;

    private final IdGenerateStrategy strategy;

}
