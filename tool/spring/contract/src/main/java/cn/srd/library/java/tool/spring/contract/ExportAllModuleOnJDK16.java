// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.spring.contract;

import org.burningwave.core.assembler.StaticComponentContainer;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;

/**
 * @author wjm
 * @since 2023-10-14 17:05
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExportAllModuleOnJDK16 implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(@NonNull ConfigurableApplicationContext applicationContext) {
        StaticComponentContainer.Modules.exportAllToAll();
    }

}
