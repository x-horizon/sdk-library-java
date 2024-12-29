// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.client.kafka.autoconfigure;

import cn.srd.library.java.message.engine.client.kafka.model.property.KafkaClientProperty;
import cn.srd.library.java.message.engine.client.kafka.strategy.KafkaClientConfigStrategy;
import cn.srd.library.java.message.engine.client.kafka.strategy.KafkaClientFlowStrategy;
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
@EnableConfigurationProperties(KafkaClientProperty.class)
public class KafkaClientAutoConfigurer<K, V> {

    @Bean
    public KafkaClientConfigStrategy<K, V> kafkaConfigStrategy() {
        return new KafkaClientConfigStrategy<>();
    }

    @Bean
    public KafkaClientFlowStrategy kafkaFlowStrategy() {
        return new KafkaClientFlowStrategy();
    }

}