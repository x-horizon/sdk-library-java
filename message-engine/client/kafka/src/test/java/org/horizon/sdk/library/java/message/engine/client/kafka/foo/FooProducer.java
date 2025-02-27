package org.horizon.sdk.library.java.message.engine.client.kafka.foo;

import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.message.engine.client.contract.MessageClientConfig;
import org.horizon.sdk.library.java.message.engine.client.contract.MessageClientProducer;
import org.horizon.sdk.library.java.message.engine.client.contract.model.enums.MessageClientType;
import org.horizon.sdk.library.java.message.engine.client.kafka.KafkaConfig;
import org.horizon.sdk.library.java.tool.lang.time.Times;
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
            config = @MessageClientConfig(engineType = MessageClientType.KAFKA, kafka = @KafkaConfig)
    )
    public String send1() {
        System.out.println(STR."生产者1 -------- \{Times.getCurrentDateTime()}-producer-send1");
        return "send1";
    }

    @MessageClientProducer(
            topic = FooTopicConstant.TOPIC_TEST2,
            config = @MessageClientConfig(engineType = MessageClientType.KAFKA, kafka = @KafkaConfig)
    )
    public String send2() {
        System.out.println(STR."生产者2 -------- \{Times.getCurrentDateTime()}-producer-send2");
        return "send2";
    }

}