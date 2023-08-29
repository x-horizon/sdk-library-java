package com.test.id;

import com.mybatisflex.core.keygen.IKeyGenerator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdGenerateByNoneStrategy implements IdGenerateStrategy {

    protected static final IdGenerateByNoneStrategy INSTANCE = new IdGenerateByNoneStrategy();

    @Override
    public String getGeneratorName() {
        return IdGenerateType.NONE_GENERATOR_NAME;
    }

    @Override
    public Class<? extends IKeyGenerator> getGenerator() {
        return IdInvalidGenerator.class;
    }

}
