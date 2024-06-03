// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka.autoconfigure;

import cn.srd.library.java.message.engine.kafka.model.domain.MessageKafkaConfigDO;
import cn.srd.library.java.message.engine.kafka.model.properties.MessageEngineKafkaProperties;
import cn.srd.library.java.message.engine.kafka.strategy.MessageKafkaFlowStrategy;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Message Engine Kafka
 *
 * @author wjm
 * @since 2024-05-24 16:56
 */
@AllArgsConstructor
@AutoConfiguration
@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableIntegration
@EnableConfigurationProperties(MessageEngineKafkaProperties.class)
@IntegrationComponentScan
public class MessageEngineKafkaAutoConfigurer<K, V> {

    @Bean
    public MessageKafkaFlowStrategy messageEngineKafkaStrategy() {
        return new MessageKafkaFlowStrategy();
    }

    @Bean
    @ConditionalOnBean(MessageEngineKafkaSwitcher.class)
    public MessageKafkaConfigDO<K, V> messageKafkaConfigDO() {
        return new MessageKafkaConfigDO<>();
    }

}