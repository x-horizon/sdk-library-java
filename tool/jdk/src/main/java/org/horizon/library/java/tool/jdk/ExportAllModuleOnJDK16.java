package org.horizon.library.java.tool.jdk;

import org.horizon.library.java.contract.constant.spring.SpringInitializeConstant;
import org.burningwave.core.assembler.StaticComponentContainer;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;

/**
 * export all module on jdk16
 *
 * @author wjm
 * @since 2023-10-14 17:05
 */
@Order(SpringInitializeConstant.HIGHEST_INITIALIZE_PRIORITY)
public class ExportAllModuleOnJDK16 implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(@NonNull ConfigurableApplicationContext applicationContext) {
        StaticComponentContainer.Modules.exportAllToAll();
    }

}