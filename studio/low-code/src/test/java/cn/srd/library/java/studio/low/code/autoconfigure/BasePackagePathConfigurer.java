// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.studio.low.code.autoconfigure;

import cn.srd.library.java.contract.constant.spring.SpringInitializeConstant;
import cn.srd.library.java.tool.lang.object.BasePackagePath;
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
        BasePackagePath.register("cn.srd.library.java");
    }

}