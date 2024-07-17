// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.oss.local.autoconfigure;

import cn.srd.library.java.oss.contract.autoconfigure.OssAutoConfigurer;
import cn.srd.library.java.oss.contract.model.property.OssProperty;
import cn.srd.library.java.oss.local.strategy.OssLocalStorage;
import org.dromara.x.file.storage.spring.FileStorageAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Oss Local
 *
 * @author wjm
 * @since 2024-07-17 16:35
 */
@AutoConfigureBefore({OssAutoConfigurer.class, FileStorageAutoConfiguration.class})
@ConditionalOnBean(OssLocalRegistrar.class)
@EnableConfigurationProperties(OssProperty.class)
public class OssLocalAutoConfigurer {

    @Bean
    public OssLocalStorage ossLocalStorage() {
        return new OssLocalStorage();
    }

}