package com.test.id;

import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.keygen.IKeyGenerator;
import com.mybatisflex.core.keygen.KeyGeneratorFactory;
import com.mybatisflex.core.keygen.impl.UUIDKeyGenerator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdGenerateByUUIDStrategy implements IdGenerateStrategy {

    protected static final IdGenerateByUUIDStrategy INSTANCE = new IdGenerateByUUIDStrategy();

    @Override
    public String getGeneratorName() {
        return IdGenerateType.UUID_GENERATOR_NAME;
    }

    @Override
    public Class<? extends IKeyGenerator> getGenerator() {
        return UUIDKeyGenerator.class;
    }

    @Override
    public FlexGlobalConfig.KeyConfig buildConfig(IdGenerateConfig idGenerateConfig) {
        KeyGeneratorFactory.register(getGeneratorName(), new UUIDKeyGenerator());
        return IdGenerateStrategy.super.buildConfig(idGenerateConfig);
    }

}
