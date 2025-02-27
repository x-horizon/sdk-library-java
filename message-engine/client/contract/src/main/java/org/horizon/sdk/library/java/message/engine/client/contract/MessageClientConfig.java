package org.horizon.sdk.library.java.message.engine.client.contract;

import org.horizon.sdk.library.java.message.engine.client.contract.model.enums.MessageClientType;
import org.horizon.sdk.library.java.message.engine.client.kafka.KafkaConfig;
import org.horizon.sdk.library.java.message.engine.client.mqtt.v3.MqttV3Config;
import org.horizon.sdk.library.java.message.engine.client.mqtt.v5.MqttV5Config;
import org.horizon.sdk.library.java.message.engine.client.nil.MessageNilConfig;
import org.horizon.sdk.library.java.message.engine.client.rabbitmq.RabbitMqConfig;
import org.horizon.sdk.library.java.message.engine.client.redis.stream.RedisStreamConfig;
import org.horizon.sdk.library.java.message.engine.client.rocketmq.RocketMqConfig;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wjm
 * @since 2024-05-30 15:36
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface MessageClientConfig {

    MessageClientType engineType();

    KafkaConfig kafka() default @KafkaConfig();

    MqttV3Config mqttV3() default @MqttV3Config();

    MqttV5Config mqttV5() default @MqttV5Config();

    MessageNilConfig nil() default @MessageNilConfig();

    RabbitMqConfig rabbitmq() default @RabbitMqConfig();

    RedisStreamConfig redis() default @RedisStreamConfig();

    RocketMqConfig rocketmq() default @RocketMqConfig();

}