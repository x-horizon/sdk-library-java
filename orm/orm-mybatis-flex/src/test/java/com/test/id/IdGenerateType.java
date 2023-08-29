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

    private static final String GENERATOR_PREFIX_NAME = "mybatis-flex-id-";
    public static final String AUTO_INCREMENT_GENERATOR_NAME = GENERATOR_PREFIX_NAME + "auto-increment-generator";
    public static final String UUID_GENERATOR_NAME = GENERATOR_PREFIX_NAME + "uuid-generator";
    public static final String SNOWFLAKE_GENERATOR_NAME = GENERATOR_PREFIX_NAME + "snowflake-generator";
    public static final String SQL_GENERATOR_NAME = GENERATOR_PREFIX_NAME + "sql-generator";
    public static final String CUSTOMER_GENERATOR_NAME = GENERATOR_PREFIX_NAME + "customer-generator";
    public static final String NONE_GENERATOR_NAME = GENERATOR_PREFIX_NAME + "none-generator";

    private final KeyType nativeIdType;

    private final IdGenerateStrategy strategy;

}
