// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.client.kafka.foo;

import cn.srd.library.java.contract.constant.suppress.SuppressWarningConstant;
import cn.srd.library.java.message.engine.client.contract.MessageClientConfig;
import cn.srd.library.java.message.engine.client.contract.MessageClientConsumer;
import cn.srd.library.java.message.engine.client.contract.model.enums.MessageClientType;
import cn.srd.library.java.message.engine.client.kafka.KafkaConfig;
import cn.srd.library.java.tool.lang.time.Times;
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
            config = @MessageClientConfig(engineType = MessageClientType.KAFKA, kafka = @KafkaConfig(
                    clientConfig = @KafkaConfig.ClientConfig,
                    consumerConfig = @KafkaConfig.ConsumerConfig(groupId = "1")
            ))
    )
    public void receive1(String message) {
        System.out.println(STR."消费者1 -------- \{Times.getCurrentDateTime()}-receive-\{message}");
    }

    @MessageClientConsumer(
            topics = FooTopicConstant.TOPIC_TEST1,
            config = @MessageClientConfig(engineType = MessageClientType.KAFKA, kafka = @KafkaConfig(
                    clientConfig = @KafkaConfig.ClientConfig,
                    consumerConfig = @KafkaConfig.ConsumerConfig(groupId = "1")
            ))
    )
    public void receive2(String message) {
        System.out.println(STR."消费者2 -------- \{Times.getCurrentDateTime()}-receive-\{message}");
    }

    @MessageClientConsumer(
            topics = FooTopicConstant.TOPIC_TEST1,
            config = @MessageClientConfig(engineType = MessageClientType.KAFKA, kafka = @KafkaConfig(
                    clientConfig = @KafkaConfig.ClientConfig,
                    consumerConfig = @KafkaConfig.ConsumerConfig(groupId = "2")
            ))
    )
    public void receive3(String message) {
        System.out.println(STR."消费者3 -------- \{Times.getCurrentDateTime()}-receive-\{message}");
    }

}