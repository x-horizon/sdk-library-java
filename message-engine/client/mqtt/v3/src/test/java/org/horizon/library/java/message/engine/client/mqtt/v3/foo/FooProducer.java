package org.horizon.library.java.message.engine.client.mqtt.v3.foo;

import org.horizon.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.library.java.message.engine.client.contract.MessageClientConfig;
import org.horizon.library.java.message.engine.client.contract.MessageClientProducer;
import org.horizon.library.java.message.engine.client.contract.model.enums.MessageClientType;
import org.horizon.library.java.message.engine.client.contract.model.enums.MessageQualityOfServiceType;
import org.horizon.library.java.message.engine.client.mqtt.v3.MqttV3Config;
import org.horizon.library.java.tool.lang.time.Times;
import org.springframework.stereotype.Component;

/**
 * @author wjm
 * @since 2024-05-27 14:50
 */
@SuppressWarnings(SuppressWarningConstant.PREVIEW)
@Component
public class FooProducer {

    @MessageClientProducer(
            topic = FooTopicConstant.TOPIC_TEST1,
            config = @MessageClientConfig(engineType = MessageClientType.MQTT_V3, mqttV3 = @MqttV3Config(
                    clientConfig = @MqttV3Config.ClientConfig(qualityOfServiceType = MessageQualityOfServiceType.EXACTLY_ONCE)
            ))
    )
    public String send1() {
        System.out.println(STR."生产者1 -------- \{Times.getCurrentDateTime()}-producer-send1");
        return "send1";
    }

    @MessageClientProducer(
            topic = FooTopicConstant.TOPIC_TEST2,
            config = @MessageClientConfig(engineType = MessageClientType.MQTT_V3, mqttV3 = @MqttV3Config(
                    clientConfig = @MqttV3Config.ClientConfig(qualityOfServiceType = MessageQualityOfServiceType.EXACTLY_ONCE)
            ))
    )
    public String send2() {
        System.out.println(STR."生产者2 -------- \{Times.getCurrentDateTime()}-producer-send2");
        return "send2";
    }

}