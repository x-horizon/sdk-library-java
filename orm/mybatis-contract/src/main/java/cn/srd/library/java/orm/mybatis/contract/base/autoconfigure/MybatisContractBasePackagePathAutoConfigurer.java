// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.contract.base.autoconfigure;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.spring.SpringInitializeConstant;
import cn.srd.library.java.tool.lang.object.BasePackagePath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;

/**
 * register orm mybatis library base package path
 *
 * @author wjm
 * @since 2023-11-08 17:07
 */
@Slf4j
@Order(SpringInitializeConstant.HIGHER_INITIALIZE_PRIORITY)
public class MybatisContractBasePackagePathAutoConfigurer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final String LIBRARY_JAVA_MYBATIS_BASE_PACKAGE_PATH = "cn.srd.library.java.orm.mybatis";

    @Override
    public void initialize(@NonNull ConfigurableApplicationContext applicationContext) {
        BasePackagePath.register(LIBRARY_JAVA_MYBATIS_BASE_PACKAGE_PATH);
        log.debug("{}the path [{}] has already been registered to global base package paths, current global base package paths: {}.", ModuleView.TOOL_CLASS_SYSTEM, LIBRARY_JAVA_MYBATIS_BASE_PACKAGE_PATH, BasePackagePath.get());
    }

}
