package cn.library.java.studio.low.code.autoconfigure;

import cn.library.java.contract.constant.spring.SpringInitializeConstant;
import cn.library.java.tool.lang.object.BasePackagePath;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;

/**
 * @author wjm
 * @since 2024-04-17 00:21
 */
@Order(SpringInitializeConstant.HIGHER_INITIALIZE_PRIORITY)
public class BasePackagePathConfigurer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(@NonNull ConfigurableApplicationContext applicationContext) {
        BasePackagePath.register("cn.library.java");
    }

}