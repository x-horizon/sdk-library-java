package cn.srd.library.java.orm.mybatis.flex.base.id;

import com.mybatisflex.annotation.KeyType;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjm
 * @since 2023-11-12 21:06
 */
@Getter
@AllArgsConstructor
public enum IdGenerateType {

    AUTO_INCREMENT(KeyType.Auto, new IdGenerateByAutoIncrementStrategy()),
    UUID(KeyType.Generator, new IdGenerateByUUIDStrategy()),
    SNOWFLAKE(KeyType.Generator, new IdGenerateBySnowflakeStrategy()),
    CUSTOMER(KeyType.Generator, new IdGenerateByCustomerStrategy()),
    SQL(KeyType.Sequence, new IdGenerateBySQLStrategy()),
    UNCONTROLLED(KeyType.None, new IdGenerateByUncontrolledStrategy()),

    ;

    private final KeyType mybatisFlexIdType;

    private final IdGenerateStrategy strategy;

}