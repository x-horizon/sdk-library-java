package com.test.id;

import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.keygen.KeyGeneratorFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdGenerateBySnowflakeStrategy implements IdGenerateStrategy {

    protected static final IdGenerateBySnowflakeStrategy INSTANCE = new IdGenerateBySnowflakeStrategy();

    @Override
    public String getGeneratorName() {
        return "mybatis-flex-id-snowflake-generator";
    }

    @Override
    public FlexGlobalConfig.KeyConfig buildConfig(IdGenerateConfig idGenerateConfig) {
        KeyGeneratorFactory.register(getGeneratorName(), IdSnowflakeGenerator.INSTANCE);
        return IdGenerateStrategy.super.buildConfig(idGenerateConfig);
    }

}
