package com.test.id;

import com.mybatisflex.core.FlexGlobalConfig;

public interface IdGenerateStrategy {

    String getGeneratorName();

    default FlexGlobalConfig.KeyConfig buildConfig(IdGenerateConfig idGenerateConfig) {
        FlexGlobalConfig.KeyConfig keyConfig = new FlexGlobalConfig.KeyConfig();
        keyConfig.setKeyType(idGenerateConfig.type().getNativeIdType());
        keyConfig.setValue(getGeneratorName());
        keyConfig.setBefore(true);
        return keyConfig;
    }

}
