package org.horizon.library.java.oss.contract.autoconfigure;

import org.horizon.library.java.contract.constant.spring.SpringInitializeConstant;
import org.horizon.library.java.tool.lang.object.BasePackagePath;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;

/**
 * register oss base package path
 *
 * @author wjm
 * @since 2024-07-17 16:26
 */
@Order(SpringInitializeConstant.HIGHER_INITIALIZE_PRIORITY)
public class OssBasePackagePathAutoConfigurer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    static final String BASE_PACKAGE_PATH = "org.horizon.library.java.oss";

    static final String BASE_CONTRACT_PACKAGE_PATH = "org.horizon.library.java.contract.component.oss";

    @Override
    public void initialize(@NonNull ConfigurableApplicationContext applicationContext) {
        BasePackagePath.register(BASE_PACKAGE_PATH, BASE_CONTRACT_PACKAGE_PATH);
    }

}