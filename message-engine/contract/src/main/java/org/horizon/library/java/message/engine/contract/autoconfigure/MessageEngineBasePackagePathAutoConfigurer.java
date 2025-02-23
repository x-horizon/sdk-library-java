package org.horizon.library.java.message.engine.contract.autoconfigure;

import org.horizon.library.java.contract.constant.spring.SpringInitializeConstant;
import org.horizon.library.java.tool.lang.object.BasePackagePath;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;

/**
 * register message engine base package path
 *
 * @author wjm
 * @since 2024-05-24 16:56
 */
@Order(SpringInitializeConstant.HIGHER_INITIALIZE_PRIORITY)
public class MessageEngineBasePackagePathAutoConfigurer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    public static final String BASE_PACKAGE_PATH = "org.horizon.library.java.message.engine";

    @Override
    public void initialize(@NonNull ConfigurableApplicationContext applicationContext) {
        BasePackagePath.register(BASE_PACKAGE_PATH);
    }

}