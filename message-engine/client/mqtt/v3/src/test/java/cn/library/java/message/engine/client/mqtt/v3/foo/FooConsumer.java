package cn.library.java.message.engine.client.mqtt.v3.foo;

import cn.library.java.contract.constant.suppress.SuppressWarningConstant;
import cn.library.java.message.engine.client.contract.MessageClientConfig;
import cn.library.java.message.engine.client.contract.MessageClientConsumer;
import cn.library.java.message.engine.client.contract.model.enums.MessageClientType;
import cn.library.java.message.engine.client.contract.model.enums.MessageQualityOfServiceType;
import cn.library.java.message.engine.client.mqtt.v3.MqttV3Config;
import cn.library.java.tool.lang.time.Times;
import org.springframework.stereotype.Component;

/**
 * @author wjm
 * @since 2024-05-26 15:08
 */
@SuppressWarnings(SuppressWarningConstant.PREVIEW)
@Component
public class FooConsumer {

    @MessageClientConsumer(
            topics = {FooTopicConstant.TOPIC_TEST1, FooTopicConstant.TOPIC_TEST2},
            config = @MessageClientConfig(engineType = MessageClientType.MQTT_V3, mqttV3 = @MqttV3Config(
                    clientConfig = @MqttV3Config.ClientConfig(qualityOfServiceType = MessageQualityOfServiceType.EXACTLY_ONCE)
            ))
    )
    public void receive(String message) {
        System.out.println(STR."消费者1 -------- \{Times.getCurrentDateTime()}-receive-\{message}");
    }

}