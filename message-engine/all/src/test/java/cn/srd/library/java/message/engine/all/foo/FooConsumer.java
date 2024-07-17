// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.all.foo;

import cn.srd.library.java.contract.constant.suppress.SuppressWarningConstant;
import cn.srd.library.java.message.engine.contract.MessageConfig;
import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;
import cn.srd.library.java.message.engine.contract.model.enums.MessageQosType;
import cn.srd.library.java.message.engine.kafka.KafkaConfig;
import cn.srd.library.java.message.engine.mqtt.v3.MqttV3Config;
import cn.srd.library.java.tool.lang.time.Times;
import org.springframework.stereotype.Component;

/**
 * @author wjm
 * @since 2024-05-26 15:08
 */
@SuppressWarnings(SuppressWarningConstant.PREVIEW)
@Component
public class FooConsumer {

    // --------------------------------------------- kafka consumer ---------------------------------------------

    @MessageConsumer(
            topics = {FooTopicConstant.TOPIC_TEST1, FooTopicConstant.TOPIC_TEST2},
            config = @MessageConfig(engineType = MessageEngineType.KAFKA, kafka = @KafkaConfig(
                    clientConfig = @KafkaConfig.ClientConfig,
                    consumerConfig = @KafkaConfig.ConsumerConfig(groupId = "1")
            ))
    )
    public void kafkaReceive1(String message) {
        System.out.println(STR."kafka - 消费者1 -------- \{Times.getCurrentDateTime()}-receive-\{message}");
    }

    @MessageConsumer(
            topics = FooTopicConstant.TOPIC_TEST1,
            config = @MessageConfig(engineType = MessageEngineType.KAFKA, kafka = @KafkaConfig(
                    clientConfig = @KafkaConfig.ClientConfig,
                    consumerConfig = @KafkaConfig.ConsumerConfig(groupId = "1")
            ))
    )
    public void kafkaReceive2(String message) {
        System.out.println(STR."kafka - 消费者2 -------- \{Times.getCurrentDateTime()}-receive-\{message}");
    }

    @MessageConsumer(
            topics = FooTopicConstant.TOPIC_TEST1,
            config = @MessageConfig(engineType = MessageEngineType.KAFKA, kafka = @KafkaConfig(
                    clientConfig = @KafkaConfig.ClientConfig,
                    consumerConfig = @KafkaConfig.ConsumerConfig(groupId = "2")
            )),
            forwardTo = @MessageProducer(
                    topic = FooTopicConstant.TOPIC_TEST1,
                    config = @MessageConfig(engineType = MessageEngineType.MQTT_V3, mqttV3 = @MqttV3Config(
                            clientConfig = @MqttV3Config.ClientConfig(qosType = MessageQosType.EXACTLY_ONCE)
                    ))
            )
    )
    public String kafkaReceive3(String message) {
        System.out.println(STR."kafka - 消费者3 -------- \{Times.getCurrentDateTime()}-receive-\{message}");
        return "forward3";
    }

    // --------------------------------------------- mqtt-v3 consumer ---------------------------------------------

    @MessageConsumer(
            topics = {FooTopicConstant.TOPIC_TEST1, FooTopicConstant.TOPIC_TEST2},
            config = @MessageConfig(engineType = MessageEngineType.MQTT_V3, mqttV3 = @MqttV3Config(
                    clientConfig = @MqttV3Config.ClientConfig(qosType = MessageQosType.EXACTLY_ONCE)
            )),
            forwardTo = @MessageProducer(
                    topic = FooTopicConstant.TOPIC_TEST2,
                    config = @MessageConfig(engineType = MessageEngineType.KAFKA, kafka = @KafkaConfig)
            )
    )
    public void mqttV3Receive1(String message) {
        System.out.println(STR."mqtt-v3 - 消费者1 -------- \{Times.getCurrentDateTime()}-receive-\{message}");
    }

}