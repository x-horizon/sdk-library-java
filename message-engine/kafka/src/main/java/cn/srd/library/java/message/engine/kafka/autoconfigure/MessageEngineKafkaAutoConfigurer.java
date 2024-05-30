// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka.autoconfigure;

import cn.srd.library.java.message.engine.kafka.properties.MessageEngineKafkaProperties;
import cn.srd.library.java.message.engine.kafka.strategy.MessageEngineKafkaStrategy;
import cn.srd.library.java.tool.spring.contract.Springs;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.DefaultKafkaHeaderMapper;

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
    public MessageEngineKafkaStrategy<K, V> messageEngineKafkaStrategy() {
        return new MessageEngineKafkaStrategy<>();
    }

    @Bean
    @ConditionalOnBean(MessageEngineKafkaSwitcher.class)
    public MessageEngineKafkaCustomizer<K, V> messageEngineKafkaCustomizer() {
        return new MessageEngineKafkaCustomizer<>();
    }

    @Bean
    @ConditionalOnBean(MessageEngineKafkaSwitcher.class)
    public ProducerFactory<K, V> kafkaProducerFactory(KafkaProperties kafkaProperties) {
        MessageEngineKafkaProperties libraryJavaKafkaProperties = Springs.getBean(MessageEngineKafkaProperties.class);
        kafkaProperties.setBootstrapServers(libraryJavaKafkaProperties.getServerUrls());
        return new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties(null));
    }

    @Bean
    @ConditionalOnBean(MessageEngineKafkaSwitcher.class)
    public DefaultKafkaHeaderMapper kafkaHeaderMapper() {
        return new DefaultKafkaHeaderMapper();
    }

}