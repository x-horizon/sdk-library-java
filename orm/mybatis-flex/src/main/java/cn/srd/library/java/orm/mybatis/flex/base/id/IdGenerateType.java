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
    CUSTOMER(KeyType.Generator, IdGenerateByCustomerStrategy.INSTANCE),
    SQL(KeyType.Sequence, IdGenerateBySQLStrategy.INSTANCE),
    UNCONTROLLED(KeyType.None, IdGenerateByUncontrolledStrategy.INSTANCE),

    ;

    private final KeyType mybatisFlexIdType;

    private final IdGenerateStrategy strategy;

}
