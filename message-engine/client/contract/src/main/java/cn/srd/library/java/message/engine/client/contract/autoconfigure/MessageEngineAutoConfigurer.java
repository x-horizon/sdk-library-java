// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.client.contract.autoconfigure;

import cn.srd.library.java.message.engine.client.contract.aspect.MessageConsumerAspect;
import cn.srd.library.java.message.engine.client.contract.aspect.MessageProducerAspect;
import cn.srd.library.java.message.engine.client.contract.event.MessageEngineConfigEvent;
import cn.srd.library.java.tool.enums.autoconfigure.EnableEnumAutowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

import static cn.srd.library.java.message.engine.client.contract.autoconfigure.MessageEngineBasePackagePathAutoConfigurer.BASE_PACKAGE_PATH;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Message Engine
 *
 * @author wjm
 * @since 2024-05-24 16:56
 */
@AutoConfiguration
@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableEnumAutowired(scanPackagePaths = BASE_PACKAGE_PATH)
@EnableIntegration
@IntegrationComponentScan
public class MessageEngineAutoConfigurer {

    @Bean
    public MessageEngineConfigEvent messageEngineConfigEvent() {
        return new MessageEngineConfigEvent();
    }

    @Bean
    public MessageProducerAspect messageProducerAspect() {
        return new MessageProducerAspect();
    }

    @Bean
    public MessageConsumerAspect messageConsumerAspect() {
        return new MessageConsumerAspect();
    }

}