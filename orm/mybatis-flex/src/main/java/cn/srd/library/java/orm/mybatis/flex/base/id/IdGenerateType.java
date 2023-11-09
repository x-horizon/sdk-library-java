package cn.srd.library.java.orm.mybatis.flex.base.id;

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
    UNCONTROLLED(KeyType.None, IdGenerateByUncontrolledStrategy.INSTANCE),
    ;

    private static final String GENERATOR_PREFIX_NAME = "mybatis-flex-id-";

    static final String AUTO_INCREMENT_GENERATOR_NAME = GENERATOR_PREFIX_NAME + "auto-increment-generator";

    static final String UUID_GENERATOR_NAME = GENERATOR_PREFIX_NAME + "uuid-generator";

    static final String SNOWFLAKE_GENERATOR_NAME = GENERATOR_PREFIX_NAME + "snowflake-generator";

    static final String SQL_GENERATOR_NAME = GENERATOR_PREFIX_NAME + "sql-generator";

    static final String CUSTOMER_GENERATOR_NAME = GENERATOR_PREFIX_NAME + "customer-generator";

    static final String NONE_GENERATOR_NAME = GENERATOR_PREFIX_NAME + "none-generator";

    private final KeyType nativeIdType;

    private final IdGenerateStrategy strategy;

}
