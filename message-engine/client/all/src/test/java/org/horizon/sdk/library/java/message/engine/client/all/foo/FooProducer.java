package org.horizon.sdk.library.java.message.engine.client.all.foo;

import org.horizon.sdk.library.java.message.engine.client.contract.MessageClientConfig;
import org.horizon.sdk.library.java.message.engine.client.contract.MessageClientProducer;
import org.horizon.sdk.library.java.message.engine.client.contract.model.enums.MessageClientType;
import org.horizon.sdk.library.java.message.engine.client.contract.model.enums.MessageQualityOfServiceType;
import org.horizon.sdk.library.java.message.engine.client.kafka.KafkaConfig;
import org.horizon.sdk.library.java.message.engine.client.mqtt.v3.MqttV3Config;
import org.horizon.sdk.library.java.tool.lang.text.Strings;
import org.horizon.sdk.library.java.tool.lang.time.Times;
import org.springframework.stereotype.Component;

/**
 * @author wjm
 * @since 2024-05-27 14:50
 */
@Component
public class FooProducer {

    // --------------------------------------------- kafka producer ---------------------------------------------

    @MessageClientProducer(
            topic = FooTopicConstant.TOPIC_TEST1,
            config = @MessageClientConfig(engineType = MessageClientType.KAFKA, kafka = @KafkaConfig)
    )
    public String kafkaSend1() {
        System.out.println(Strings.format("kafka - 生产者1 -------- {}-producer-send1", Times.getCurrentDateTime()));
        return "send1";
    }

    @MessageClientProducer(
            topic = FooTopicConstant.TOPIC_TEST2,
            config = @MessageClientConfig(engineType = MessageClientType.KAFKA, kafka = @KafkaConfig)
    )
    public String kafkaSend2() {
        System.out.println(Strings.format("kafka - 生产者2 -------- {}-producer-send2", Times.getCurrentDateTime()));
        return "send2";
    }

    // --------------------------------------------- mqtt-v3 producer ---------------------------------------------

    @MessageClientProducer(
            topic = FooTopicConstant.TOPIC_TEST1,
            config = @MessageClientConfig(engineType = MessageClientType.MQTT_V3, mqttV3 = @MqttV3Config(
                    clientConfig = @MqttV3Config.ClientConfig(qualityOfServiceType = MessageQualityOfServiceType.EXACTLY_ONCE)
            ))
    )
    public String mqttV3Send1() {
        System.out.println(Strings.format("mqtt-v3 - 生产者1 -------- {}-producer-send1", Times.getCurrentDateTime()));
        return "send1";
    }

    @MessageClientProducer(
            topic = FooTopicConstant.TOPIC_TEST2,
            config = @MessageClientConfig(engineType = MessageClientType.MQTT_V3, mqttV3 = @MqttV3Config(
                    clientConfig = @MqttV3Config.ClientConfig(qualityOfServiceType = MessageQualityOfServiceType.EXACTLY_ONCE)
            ))
    )
    public String mqttV3Send2() {
        System.out.println(Strings.format("mqtt-v3 - 生产者2 -------- {}-producer-send2", Times.getCurrentDateTime()));
        return "send2";
    }

    @MessageClientProducer(
            topic = "#topic",
            config = @MessageClientConfig(engineType = MessageClientType.MQTT_V3, mqttV3 = @MqttV3Config(
                    clientConfig = @MqttV3Config.ClientConfig(qualityOfServiceType = MessageQualityOfServiceType.EXACTLY_ONCE)
            ))
    )
    public String mqttV3SendDynamic(String topic) {
        System.out.println(Strings.format("mqtt-v3 - 动态生产者 -------- {}-dynamic-producer-send", Times.getCurrentDateTime()));
        return "send2";
    }

}