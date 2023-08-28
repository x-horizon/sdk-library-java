package com.test.id;

import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.keygen.KeyGeneratorFactory;
import com.mybatisflex.core.keygen.impl.UUIDKeyGenerator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdGenerateByUUIDStrategy implements IdGenerateStrategy {

    protected static final IdGenerateByUUIDStrategy INSTANCE = new IdGenerateByUUIDStrategy();

    @Override
    public String getGeneratorName() {
        return "mybatis-flex-id-uuid-generator";
    }

    @Override
    public FlexGlobalConfig.KeyConfig buildConfig(IdGenerateConfig idGenerateConfig) {
        KeyGeneratorFactory.register(getGeneratorName(), new UUIDKeyGenerator());
        return IdGenerateStrategy.super.buildConfig(idGenerateConfig);
    }

}
