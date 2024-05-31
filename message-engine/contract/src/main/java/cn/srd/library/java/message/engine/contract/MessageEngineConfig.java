// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.contract;

import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wjm
 * @since 2024-05-30 15:36
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface MessageEngineConfig {

    MessageEngineType type();

    MessageEngineKafkaConfig kafka() default @MessageEngineKafkaConfig();

    MessageEngineMqttV3Config mqttV3() default @MessageEngineMqttV3Config();

    MessageEngineMqttV5Config mqttV5() default @MessageEngineMqttV5Config();

    MessageEngineRabbitmqConfig rabbitmq() default @MessageEngineRabbitmqConfig();

    MessageEngineRedisConfig redis() default @MessageEngineRedisConfig();

    MessageEngineRocketmqConfig rocketmq() default @MessageEngineRocketmqConfig();

}