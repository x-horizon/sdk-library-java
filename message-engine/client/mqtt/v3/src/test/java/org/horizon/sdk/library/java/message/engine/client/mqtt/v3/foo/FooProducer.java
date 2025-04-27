package org.horizon.sdk.library.java.message.engine.client.mqtt.v3.foo;

import org.horizon.sdk.library.java.message.engine.client.contract.MessageClientConfig;
import org.horizon.sdk.library.java.message.engine.client.contract.MessageClientProducer;
import org.horizon.sdk.library.java.message.engine.client.contract.model.enums.MessageClientType;
import org.horizon.sdk.library.java.message.engine.client.contract.model.enums.MessageQualityOfServiceType;
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

    @MessageClientProducer(
            topic = FooTopicConstant.TOPIC_TEST1,
            config = @MessageClientConfig(engineType = MessageClientType.MQTT_V3, mqttV3 = @MqttV3Config(
                    clientConfig = @MqttV3Config.ClientConfig(qualityOfServiceType = MessageQualityOfServiceType.EXACTLY_ONCE)
            ))
    )
    public String send1() {
        System.out.println(Strings.format("生产者1 -------- {}-producer-send1", Times.getCurrentDateTime()));
        return "send1";
    }

    @MessageClientProducer(
            topic = FooTopicConstant.TOPIC_TEST2,
            config = @MessageClientConfig(engineType = MessageClientType.MQTT_V3, mqttV3 = @MqttV3Config(
                    clientConfig = @MqttV3Config.ClientConfig(qualityOfServiceType = MessageQualityOfServiceType.EXACTLY_ONCE)
            ))
    )
    public String send2() {
        System.out.println(Strings.format("生产者2 -------- {}-producer-send2", Times.getCurrentDateTime()));
        return "send2";
    }

}