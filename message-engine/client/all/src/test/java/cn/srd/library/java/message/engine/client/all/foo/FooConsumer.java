package cn.srd.library.java.message.engine.client.all.foo;

import cn.srd.library.java.contract.constant.suppress.SuppressWarningConstant;
import cn.srd.library.java.message.engine.client.contract.MessageClientConfig;
import cn.srd.library.java.message.engine.client.contract.MessageClientConsumer;
import cn.srd.library.java.message.engine.client.contract.MessageClientProducer;
import cn.srd.library.java.message.engine.client.contract.model.enums.MessageClientType;
import cn.srd.library.java.message.engine.client.contract.model.enums.MessageQualityOfServiceType;
import cn.srd.library.java.message.engine.client.kafka.KafkaConfig;
import cn.srd.library.java.message.engine.client.mqtt.v3.MqttV3Config;
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

    @MessageClientConsumer(
            topics = {FooTopicConstant.TOPIC_TEST1, FooTopicConstant.TOPIC_TEST2},
            config = @MessageClientConfig(engineType = MessageClientType.KAFKA, kafka = @KafkaConfig(
                    clientConfig = @KafkaConfig.ClientConfig,
                    consumerConfig = @KafkaConfig.ConsumerConfig(groupId = "1")
            ))
    )
    public void kafkaReceive1(String message) {
        System.out.println(STR."kafka - 消费者1 -------- \{Times.getCurrentDateTime()}-receive-\{message}");
    }

    @MessageClientConsumer(
            topics = FooTopicConstant.TOPIC_TEST1,
            config = @MessageClientConfig(engineType = MessageClientType.KAFKA, kafka = @KafkaConfig(
                    clientConfig = @KafkaConfig.ClientConfig,
                    consumerConfig = @KafkaConfig.ConsumerConfig(groupId = "1")
            ))
    )
    public void kafkaReceive2(String message) {
        System.out.println(STR."kafka - 消费者2 -------- \{Times.getCurrentDateTime()}-receive-\{message}");
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
        System.out.println(STR."kafka - 消费者3 -------- \{Times.getCurrentDateTime()}-receive-\{message}");
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
        System.out.println(STR."mqtt-v3 - 消费者1 -------- \{Times.getCurrentDateTime()}-receive-\{message}");
    }

}