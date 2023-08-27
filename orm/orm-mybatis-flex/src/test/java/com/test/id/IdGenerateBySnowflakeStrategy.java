package com.test.id;

import cn.srd.library.java.contract.throwable.core.UnsupportedOperationException;
import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.keygen.KeyGeneratorFactory;
import com.test.IdGenerateConfig;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdGenerateBySnowflakeStrategy implements IdGenerateStrategy {

    protected static final IdGenerateByAutoIncrementStrategy INSTANCE = new IdGenerateByAutoIncrementStrategy();

    private static final String snowflakeIdGeneratorName = "snowflakeIdGenerator";

    @Override
    public Object generate() {
        throw new UnsupportedOperationException();
    }

    @Override
    public FlexGlobalConfig.KeyConfig generateNativeIdConfig(IdGenerateConfig idGenerateConfig) {
        KeyGeneratorFactory.register(snowflakeIdGeneratorName, SnowflakeIdGenerator.INSTANCE);
        FlexGlobalConfig.KeyConfig keyConfig = new FlexGlobalConfig.KeyConfig();
        keyConfig.setKeyType(idGenerateConfig.type().getNativeIdType());
        keyConfig.setValue(snowflakeIdGeneratorName);
        keyConfig.setBefore(true);
        return keyConfig;
    }

}
