// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.all.foo;

import cn.srd.library.java.contract.constant.suppress.SuppressWarningConstant;
import cn.srd.library.java.message.engine.contract.MessageConfig;
import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;
import cn.srd.library.java.message.engine.contract.model.enums.MessageQosType;
import cn.srd.library.java.message.engine.kafka.KafkaConfig;
import cn.srd.library.java.message.engine.mqtt.v3.MqttV3Config;
import cn.srd.library.java.tool.lang.time.Times;
import org.springframework.stereotype.Component;

/**
 * @author wjm
 * @since 2024-05-27 14:50
 */
@SuppressWarnings(SuppressWarningConstant.PREVIEW)
@Component
public class FooProducer {

    // --------------------------------------------- kafka producer ---------------------------------------------

    @MessageProducer(
            topic = FooTopicConstant.TOPIC_TEST1,
            config = @MessageConfig(engineType = MessageEngineType.KAFKA, kafka = @KafkaConfig)
    )
    public String kafkaSend1() {
        System.out.println(STR."kafka - 生产者1 -------- \{Times.getCurrentDateTime()}-producer-send1");
        return "send1";
    }

    @MessageProducer(
            topic = FooTopicConstant.TOPIC_TEST2,
            config = @MessageConfig(engineType = MessageEngineType.KAFKA, kafka = @KafkaConfig)
    )
    public String kafkaSend2() {
        System.out.println(STR."kafka - 生产者2 -------- \{Times.getCurrentDateTime()}-producer-send2");
        return "send2";
    }

    // --------------------------------------------- mqtt-v3 producer ---------------------------------------------

    @MessageProducer(
            topic = FooTopicConstant.TOPIC_TEST1,
            config = @MessageConfig(engineType = MessageEngineType.MQTT_V3, mqttV3 = @MqttV3Config(
                    clientConfig = @MqttV3Config.ClientConfig(qosType = MessageQosType.EXACTLY_ONCE)
            ))
    )
    public String mqttV3Send1() {
        System.out.println(STR."mqtt-v3 - 生产者1 -------- \{Times.getCurrentDateTime()}-producer-send1");
        return "send1";
    }

    @MessageProducer(
            topic = FooTopicConstant.TOPIC_TEST2,
            config = @MessageConfig(engineType = MessageEngineType.MQTT_V3, mqttV3 = @MqttV3Config(
                    clientConfig = @MqttV3Config.ClientConfig(qosType = MessageQosType.EXACTLY_ONCE)
            ))
    )
    public String mqttV3Send2() {
        System.out.println(STR."mqtt-v3 - 生产者2 -------- \{Times.getCurrentDateTime()}-producer-send2");
        return "send2";
    }

    @MessageProducer(
            topic = "#topic",
            config = @MessageConfig(engineType = MessageEngineType.MQTT_V3, mqttV3 = @MqttV3Config(
                    clientConfig = @MqttV3Config.ClientConfig(qosType = MessageQosType.EXACTLY_ONCE)
            ))
    )
    public String mqttV3SendDynamic(String topic) {
        System.out.println(STR."mqtt-v3 - 动态生产者 -------- \{Times.getCurrentDateTime()}-dynamic-producer-send");
        return "send2";
    }

}