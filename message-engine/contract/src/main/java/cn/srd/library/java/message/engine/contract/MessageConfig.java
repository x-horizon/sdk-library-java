// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.contract;

import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;
import cn.srd.library.java.message.engine.kafka.KafkaConfig;
import cn.srd.library.java.message.engine.mqtt.v3.MqttV3Config;
import cn.srd.library.java.message.engine.mqtt.v5.MqttV5Config;
import cn.srd.library.java.message.engine.nil.MessageNilConfig;
import cn.srd.library.java.message.engine.rabbitmq.RabbitMqConfig;
import cn.srd.library.java.message.engine.redis.stream.RedisStreamConfig;
import cn.srd.library.java.message.engine.rocketmq.RocketMqConfig;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wjm
 * @since 2024-05-30 15:36
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface MessageConfig {

    MessageEngineType engineType();

    KafkaConfig kafka() default @KafkaConfig();

    MqttV3Config mqttV3() default @MqttV3Config();

    MqttV5Config mqttV5() default @MqttV5Config();

    MessageNilConfig nil() default @MessageNilConfig();

    RabbitMqConfig rabbitmq() default @RabbitMqConfig();

    RedisStreamConfig redis() default @RedisStreamConfig();

    RocketMqConfig rocketmq() default @RocketMqConfig();

}