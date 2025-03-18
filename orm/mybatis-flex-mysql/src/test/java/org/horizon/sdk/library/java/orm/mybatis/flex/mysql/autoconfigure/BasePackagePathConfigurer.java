package org.horizon.sdk.library.java.orm.mybatis.flex.mysql.autoconfigure;

import org.horizon.sdk.library.java.contract.constant.spring.SpringInitializeConstant;
import org.horizon.sdk.library.java.tool.lang.object.BasePackagePath;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;

@Order(SpringInitializeConstant.HIGHER_INITIALIZE_PRIORITY)
public class BasePackagePathConfigurer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(@NonNull ConfigurableApplicationContext applicationContext) {
        BasePackagePath.register("org.horizon.sdk.library.java");
    }

}