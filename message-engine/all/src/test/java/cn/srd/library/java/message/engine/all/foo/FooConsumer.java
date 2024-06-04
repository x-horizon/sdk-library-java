// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.all.foo;

import cn.srd.library.java.message.engine.contract.MessageConfig;
import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.model.enums.ClientIdGenerateType;
import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;
import cn.srd.library.java.message.engine.contract.model.enums.MessageQosType;
import cn.srd.library.java.message.engine.kafka.MessageKafkaConfig;
import cn.srd.library.java.message.engine.kafka.model.enums.MessageKafkaConsumerAckMode;
import cn.srd.library.java.message.engine.kafka.model.enums.MessageKafkaConsumerOffsetResetMode;
import cn.srd.library.java.message.engine.mqtt.v3.MessageMqttV3Config;
import cn.srd.library.java.tool.lang.time.Times;
import org.springframework.stereotype.Component;

import static cn.srd.library.java.message.engine.kafka.MessageKafkaConfig.ClientConfig;
import static cn.srd.library.java.message.engine.kafka.MessageKafkaConfig.ConsumerConfig;

/**
 * @author wjm
 * @since 2024-05-26 15:08
 */
@Component
public class FooConsumer {

    // --------------------------------------------- kafka consumer ---------------------------------------------

    @MessageConsumer(
            topics = {FooTopicConstant.TOPIC_TEST1, FooTopicConstant.TOPIC_TEST2},
            config = @MessageConfig(engineType = MessageEngineType.KAFKA, kafka = @MessageKafkaConfig(
                    clientConfig = @ClientConfig(idGenerateType = ClientIdGenerateType.SNOWFLAKE),
                    consumerConfig = @ConsumerConfig(groupId = "1", ackMode = MessageKafkaConsumerAckMode.COMMIT_EACH_OFFSET_AFTER_CONSUME, offsetResetMode = MessageKafkaConsumerOffsetResetMode.LATEST)
            ))
    )
    public void kafkaReceive1(String message) {
        System.out.println("kafka - 消费者1 -------- " + Times.getCurrentDateTime() + "-receive-" + message);
    }

    @MessageConsumer(
            topics = FooTopicConstant.TOPIC_TEST1,
            config = @MessageConfig(engineType = MessageEngineType.KAFKA, kafka = @MessageKafkaConfig(
                    clientConfig = @ClientConfig(idGenerateType = ClientIdGenerateType.SNOWFLAKE),
                    consumerConfig = @ConsumerConfig(groupId = "1", ackMode = MessageKafkaConsumerAckMode.COMMIT_EACH_OFFSET_AFTER_CONSUME, offsetResetMode = MessageKafkaConsumerOffsetResetMode.LATEST)
            ))
    )
    public void kafkaReceive2(String message) {
        System.out.println("kafka - 消费者2 -------- " + Times.getCurrentDateTime() + "-receive-" + message);
    }

    @MessageConsumer(
            topics = FooTopicConstant.TOPIC_TEST1,
            config = @MessageConfig(engineType = MessageEngineType.KAFKA, kafka = @MessageKafkaConfig(
                    clientConfig = @ClientConfig(idGenerateType = ClientIdGenerateType.SNOWFLAKE),
                    consumerConfig = @ConsumerConfig(groupId = "2", ackMode = MessageKafkaConsumerAckMode.COMMIT_EACH_OFFSET_AFTER_CONSUME, offsetResetMode = MessageKafkaConsumerOffsetResetMode.LATEST)
            ))
    )
    public void kafkaReceive3(String message) {
        System.out.println("kafka - 消费者3 -------- " + Times.getCurrentDateTime() + "-receive-" + message);
    }

    // --------------------------------------------- mqtt-v3 consumer ---------------------------------------------

    @MessageConsumer(
            topics = {FooTopicConstant.TOPIC_TEST1, FooTopicConstant.TOPIC_TEST2},
            config = @MessageConfig(engineType = MessageEngineType.MQTT_V3, mqttV3 = @MessageMqttV3Config(
                    clientConfig = @MessageMqttV3Config.ClientConfig(idGenerateType = ClientIdGenerateType.SNOWFLAKE, qosType = MessageQosType.EXACTLY_ONCE)
            ))
    )
    public void mqttV3Receive1(String message) {
        System.out.println("mqtt-v3 - 消费者1 -------- " + Times.getCurrentDateTime() + "-receive-" + message);
    }

}