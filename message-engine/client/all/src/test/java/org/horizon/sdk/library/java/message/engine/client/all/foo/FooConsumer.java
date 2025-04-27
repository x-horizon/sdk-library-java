package org.horizon.sdk.library.java.message.engine.client.all.foo;

import org.horizon.sdk.library.java.message.engine.client.contract.MessageClientConfig;
import org.horizon.sdk.library.java.message.engine.client.contract.MessageClientConsumer;
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
 * @since 2024-05-26 15:08
 */
@Component
public class FooConsumer {

    // --------------------------------------------- kafka consumer ---------------------------------------------

    @MessageClientConsumer(
            topics = {FooTopicConstant.TOPIC_TEST1, FooTopicConstant.TOPIC_TEST2},
            config = @MessageClientConfig(engineType = MessageClientType.KAFKA, kafka = @KafkaConfig(
                    clientConfig = @KafkaConfig.ClientConfig,
                    consumerConfig = @KafkaConfig.ConsumerConfig(groupId = "1")
            ))
    )
    public void kafkaReceive1(String message) {
        System.out.println(Strings.format("kafka - 消费者1 -------- {}-receive-{}", Times.getCurrentDateTime(), message));
    }

    @MessageClientConsumer(
            topics = FooTopicConstant.TOPIC_TEST1,
            config = @MessageClientConfig(engineType = MessageClientType.KAFKA, kafka = @KafkaConfig(
                    clientConfig = @KafkaConfig.ClientConfig,
                    consumerConfig = @KafkaConfig.ConsumerConfig(groupId = "1")
            ))
    )
    public void kafkaReceive2(String message) {
        System.out.println(Strings.format("kafka - 消费者2 -------- {}-receive-{}", Times.getCurrentDateTime(), message));
    }

    @MessageClientConsumer(
            topics = FooTopicConstant.TOPIC_TEST1,
            config = @MessageClientConfig(engineType = MessageClientType.KAFKA, kafka = @KafkaConfig(
                    clientConfig = @KafkaConfig.ClientConfig,
                    consumerConfig = @KafkaConfig.ConsumerConfig(groupId = "2")
            )),
            forwardTo = @MessageClientProducer(
                    topic = FooTopicConstant.TOPIC_TEST1,
                    config = @MessageClientConfig(engineType = MessageClientType.MQTT_V3, mqttV3 = @MqttV3Config(
                            clientConfig = @MqttV3Config.ClientConfig(qualityOfServiceType = MessageQualityOfServiceType.EXACTLY_ONCE)
                    ))
            )
    )
    public String kafkaReceive3(String message) {
        System.out.println(Strings.format("kafka - 消费者3 -------- {}-receive-{}", Times.getCurrentDateTime(), message));
        return "forward3";
    }

    // --------------------------------------------- mqtt-v3 consumer ---------------------------------------------

    @MessageClientConsumer(
            topics = {FooTopicConstant.TOPIC_TEST1, FooTopicConstant.TOPIC_TEST2},
            config = @MessageClientConfig(engineType = MessageClientType.MQTT_V3, mqttV3 = @MqttV3Config(
                    clientConfig = @MqttV3Config.ClientConfig(qualityOfServiceType = MessageQualityOfServiceType.EXACTLY_ONCE)
            )),
            forwardTo = @MessageClientProducer(
                    topic = FooTopicConstant.TOPIC_TEST2,
                    config = @MessageClientConfig(engineType = MessageClientType.KAFKA, kafka = @KafkaConfig)
            )
    )
    public void mqttV3Receive1(String message) {
        System.out.println(Strings.format("mqtt-v3 - 消费者1 -------- {}-receive-{}", Times.getCurrentDateTime(), message));
    }

}