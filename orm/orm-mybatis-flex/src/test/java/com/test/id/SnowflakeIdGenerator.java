package com.test.id;

import cn.srd.library.java.id.snowflake.core.SnowflakeIds;
import com.mybatisflex.core.keygen.IKeyGenerator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SnowflakeIdGenerator implements IKeyGenerator {

    protected static final SnowflakeIdGenerator INSTANCE = new SnowflakeIdGenerator();

    @Override
    public Object generate(Object entity, String keyColumn) {
        return SnowflakeIds.getId();
    }

}