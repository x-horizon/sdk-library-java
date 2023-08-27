package com.test.id;

import com.mybatisflex.core.FlexGlobalConfig;
import com.test.IdGenerateConfig;

public interface IdGenerateStrategy {

    Object generate();

    FlexGlobalConfig.KeyConfig generateNativeIdConfig(IdGenerateConfig idGenerateConfig);

}
