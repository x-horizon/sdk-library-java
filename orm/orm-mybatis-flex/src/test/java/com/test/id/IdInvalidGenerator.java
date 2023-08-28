package com.test.id;

import cn.srd.library.java.id.snowflake.core.SnowflakeIds;
import com.mybatisflex.core.keygen.IKeyGenerator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdInvalidGenerator implements IKeyGenerator {

    protected static final IdInvalidGenerator INSTANCE = new IdInvalidGenerator();

    @Override
    public Object generate(Object entity, String keyColumn) {
        return SnowflakeIds.getId();
    }

}