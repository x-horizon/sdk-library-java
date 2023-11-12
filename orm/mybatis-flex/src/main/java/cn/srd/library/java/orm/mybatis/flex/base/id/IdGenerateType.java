// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

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
