package org.horizon.sdk.library.java.message.engine.client.contract.autoconfigure;

import org.horizon.sdk.library.java.message.engine.client.contract.aspect.MessageConsumerAspect;
import org.horizon.sdk.library.java.message.engine.client.contract.aspect.MessageProducerAspect;
import org.horizon.sdk.library.java.message.engine.client.contract.event.MessageClientConfigEvent;
import org.horizon.sdk.library.java.tool.enums.autoconfigure.EnableEnumAutowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

import static org.horizon.sdk.library.java.message.engine.contract.autoconfigure.MessageEngineBasePackagePathAutoConfigurer.BASE_PACKAGE_PATH;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Message Engine Client
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
public class MessageClientAutoConfigurer {

    @Bean
    public MessageClientConfigEvent messageEngineConfigEvent() {
        return new MessageClientConfigEvent();
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