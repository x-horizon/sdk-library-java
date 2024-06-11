// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.contract;

import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;
import cn.srd.library.java.message.engine.kafka.MessageKafkaConfig;
import cn.srd.library.java.message.engine.mqtt.v3.MessageMqttV3Config;
import cn.srd.library.java.message.engine.mqtt.v5.MessageMqttV5Config;
import cn.srd.library.java.message.engine.nil.MessageNilConfig;
import cn.srd.library.java.message.engine.rabbitmq.MessageRabbitMqConfig;
import cn.srd.library.java.message.engine.redis.MessageRedisConfig;
import cn.srd.library.java.message.engine.rocketmq.MessageRocketMqConfig;

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

    MessageKafkaConfig kafka() default @MessageKafkaConfig();

    MessageMqttV3Config mqttV3() default @MessageMqttV3Config();

    MessageMqttV5Config mqttV5() default @MessageMqttV5Config();

    MessageNilConfig nil() default @MessageNilConfig();

    MessageRabbitMqConfig rabbitmq() default @MessageRabbitMqConfig();

    MessageRedisConfig redis() default @MessageRedisConfig();

    MessageRocketMqConfig rocketmq() default @MessageRocketMqConfig();

}