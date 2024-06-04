// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.all.foo;

import cn.srd.library.java.message.engine.contract.MessageConfig;
import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;
import cn.srd.library.java.message.engine.kafka.MessageKafkaConfig;
import cn.srd.library.java.message.engine.mqtt.v3.MessageMqttV3Config;
import cn.srd.library.java.tool.lang.time.Times;
import org.springframework.stereotype.Component;

/**
 * @author wjm
 * @since 2024-05-27 14:50
 */
@Component
public class FooProducer {

    // // --------------------------------------------- kafka producer ---------------------------------------------
    //
    // @MessageProducer(
    //         configs = @MessageConfig(engineType = MessageEngineType.KAFKA, kafka = @MessageKafkaConfig),
    //         topic = FooTopicConstant.TOPIC_TEST1
    // )
    // public String kafkaSend1() {
    //     System.out.println("kafka - 生产者1 -------- " + Times.getCurrentDateTime() + "-producer-" + "send1");
    //     return "send1";
    // }
    //
    // @MessageProducer(
    //         configs = @MessageConfig(engineType = MessageEngineType.KAFKA, kafka = @MessageKafkaConfig),
    //         topic = FooTopicConstant.TOPIC_TEST2
    // )
    // public String kafkaSend2() {
    //     System.out.println("kafka - 生产者2 -------- " + Times.getCurrentDateTime() + "-producer-" + "send2");
    //     return "send2";
    // }
    //
    // // --------------------------------------------- mqtt-v3 producer ---------------------------------------------
    //
    // @MessageProducer(
    //         topic = FooTopicConstant.TOPIC_TEST1,
    //         configs = @MessageConfig(engineType = MessageEngineType.MQTT_V3, mqttV3 = @MessageMqttV3Config(
    //                 clientConfig = @MessageMqttV3Config.ClientConfig(idGenerateType = ClientIdGenerateType.SNOWFLAKE, qosType = MessageQosType.EXACTLY_ONCE)
    //         ))
    // )
    // public String mqttV3Send1() {
    //     System.out.println("mqtt-v3 - 生产者1 -------- " + Times.getCurrentDateTime() + "-producer-" + "send1");
    //     return "send1";
    // }
    //
    // @MessageProducer(
    //         topic = FooTopicConstant.TOPIC_TEST2,
    //         configs = @MessageConfig(engineType = MessageEngineType.MQTT_V3, mqttV3 = @MessageMqttV3Config(
    //                 clientConfig = @MessageMqttV3Config.ClientConfig(idGenerateType = ClientIdGenerateType.SNOWFLAKE, qosType = MessageQosType.EXACTLY_ONCE)
    //         ))
    // )
    // public String mqttV3Send2() {
    //     System.out.println("mqtt-v3 - 生产者2 -------- " + Times.getCurrentDateTime() + "-producer-" + "send2");
    //     return "send2";
    // }

    // --------------------------------------------- kafka, mqtt-v3 producer ---------------------------------------------

    @MessageProducer(
            configs = {
                    @MessageConfig(engineType = MessageEngineType.KAFKA, kafka = @MessageKafkaConfig),
                    @MessageConfig(engineType = MessageEngineType.MQTT_V3, mqttV3 = @MessageMqttV3Config),
                    @MessageConfig(engineType = MessageEngineType.MQTT_V3, mqttV3 = @MessageMqttV3Config)
            },
            topic = FooTopicConstant.TOPIC_TEST1
    )
    public String kafkaAndMqttV3Send1() {
        System.out.println("kafka/mqttV3 - 生产者1 -------- " + Times.getCurrentDateTime() + "-producer-" + "send1");
        return "send1";
    }

}