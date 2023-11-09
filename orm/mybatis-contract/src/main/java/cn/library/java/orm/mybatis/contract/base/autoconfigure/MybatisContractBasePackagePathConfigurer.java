// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.library.java.orm.mybatis.contract.base.autoconfigure;

import cn.srd.library.java.tool.lang.object.BasePackagePath;
import cn.srd.library.java.tool.spring.contract.Classes;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;

/**
 * register orm mybatis library base package path
 *
 * @author wjm
 * @since 2023-11-08 17:07
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MybatisContractBasePackagePathConfigurer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(@NonNull ConfigurableApplicationContext applicationContext) {
        BasePackagePath.register(Classes.getPackagePath(this.getClass()));
    }

}
