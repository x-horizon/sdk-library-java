// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka.autoconfigure;

import cn.srd.library.java.message.engine.kafka.model.properties.MessageKafkaProperties;
import cn.srd.library.java.message.engine.kafka.strategy.MessageKafkaConfigStrategy;
import cn.srd.library.java.message.engine.kafka.strategy.MessageKafkaFlowStrategy;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Message Engine Kafka
 *
 * @author wjm
 * @since 2024-05-24 16:56
 */
@AutoConfiguration
@EnableConfigurationProperties(MessageKafkaProperties.class)
public class MessageEngineKafkaAutoConfigurer<K, V> {

    @Bean
    public MessageKafkaConfigStrategy<K, V> messageKafkaConfigStrategy() {
        return new MessageKafkaConfigStrategy<>();
    }

    @Bean
    public MessageKafkaFlowStrategy messageEngineKafkaFlowStrategy() {
        return new MessageKafkaFlowStrategy();
    }

}