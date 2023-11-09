package cn.srd.library.java.orm.mybatis.flex.base.id;

import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.keygen.IKeyGenerator;

public interface IdGenerateStrategy {

    String getGeneratorName();

    Class<? extends IKeyGenerator> getGenerator();

    default FlexGlobalConfig.KeyConfig buildConfig(IdGenerateConfig idGenerateConfig) {
        FlexGlobalConfig.KeyConfig keyConfig = new FlexGlobalConfig.KeyConfig();
        keyConfig.setKeyType(idGenerateConfig.type().getNativeIdType());
        keyConfig.setValue(getGeneratorName());
        keyConfig.setBefore(true);
        return keyConfig;
    }

}
