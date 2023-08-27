package com.test.id;

import cn.srd.library.java.contract.throwable.core.UnsupportedOperationException;
import com.mybatisflex.core.FlexGlobalConfig;
import com.test.IdGenerateConfig;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdGenerateByUUIDStrategy implements IdGenerateStrategy {

    protected static final IdGenerateByAutoIncrementStrategy INSTANCE = new IdGenerateByAutoIncrementStrategy();

    @Override
    public Object generate() {
        throw new UnsupportedOperationException();
    }

    @Override
    public FlexGlobalConfig.KeyConfig generateNativeIdConfig(IdGenerateConfig idGenerateConfig) {
        FlexGlobalConfig.KeyConfig keyConfig = new FlexGlobalConfig.KeyConfig();
        keyConfig.setKeyType(idGenerateConfig.type().getNativeIdType());
        keyConfig.setBefore(true);
        return keyConfig;
    }

}
