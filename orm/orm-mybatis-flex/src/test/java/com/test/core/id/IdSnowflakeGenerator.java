package com.test.core.id;

import cn.srd.library.java.id.snowflake.core.SnowflakeIds;
import com.mybatisflex.core.keygen.IKeyGenerator;

// @NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdSnowflakeGenerator implements IKeyGenerator {

    protected static final IdSnowflakeGenerator INSTANCE = new IdSnowflakeGenerator();

    @Override
    public Object generate(Object entity, String keyColumn) {
        return SnowflakeIds.getId();
    }

}