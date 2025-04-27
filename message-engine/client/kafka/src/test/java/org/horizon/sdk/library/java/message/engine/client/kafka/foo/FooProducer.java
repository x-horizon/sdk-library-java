package org.horizon.sdk.library.java.message.engine.client.kafka.foo;

import org.horizon.sdk.library.java.message.engine.client.contract.MessageClientConfig;
import org.horizon.sdk.library.java.message.engine.client.contract.MessageClientProducer;
import org.horizon.sdk.library.java.message.engine.client.contract.model.enums.MessageClientType;
import org.horizon.sdk.library.java.message.engine.client.kafka.KafkaConfig;
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
            config = @MessageClientConfig(engineType = MessageClientType.KAFKA, kafka = @KafkaConfig)
    )
    public String send1() {
        System.out.println(Strings.format("生产者1 -------- {}-producer-send1", Times.getCurrentDateTime()));
        return "send1";
    }

    @MessageClientProducer(
            topic = FooTopicConstant.TOPIC_TEST2,
            config = @MessageClientConfig(engineType = MessageClientType.KAFKA, kafka = @KafkaConfig)
    )
    public String send2() {
        System.out.println(Strings.format("生产者2 -------- {}-producer-send2", Times.getCurrentDateTime()));
        return "send2";
    }

}