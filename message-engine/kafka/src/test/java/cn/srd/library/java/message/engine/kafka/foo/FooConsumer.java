// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka.foo;

import cn.srd.library.java.message.engine.contract.MessageConfig;
import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.model.enums.ClientIdGenerateType;
import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;
import cn.srd.library.java.message.engine.kafka.MessageKafkaConfig;
import cn.srd.library.java.message.engine.kafka.model.enums.MessageKafkaConsumerAckMode;
import cn.srd.library.java.message.engine.kafka.model.enums.MessageKafkaConsumerOffsetResetMode;
import cn.srd.library.java.tool.lang.time.Times;
import org.springframework.stereotype.Component;

import static cn.srd.library.java.message.engine.kafka.MessageKafkaConfig.ClientConfig;
import static cn.srd.library.java.message.engine.kafka.MessageKafkaConfig.ConsumerConfig;

/**
 * @author wjm
 * @since 2024-05-26 15:08
 */
@Component
public class FooConsumer {

    @MessageConsumer(
            topics = {FooTopicConstant.TOPIC_TEST1, FooTopicConstant.TOPIC_TEST2},
            config = @MessageConfig(engineType = MessageEngineType.KAFKA, kafka = @MessageKafkaConfig(
                    clientConfig = @ClientConfig(idGenerateType = ClientIdGenerateType.SNOWFLAKE),
                    consumerConfig = @ConsumerConfig(groupId = "1", ackMode = MessageKafkaConsumerAckMode.COMMIT_EACH_OFFSET_AFTER_CONSUME, offsetResetMode = MessageKafkaConsumerOffsetResetMode.LATEST)
            ))
    )
    public void receive1(String message) {
        System.out.println("消费者1 -------- " + Times.getCurrentDateTime() + "-receive-" + message);
    }

    @MessageConsumer(
            topics = FooTopicConstant.TOPIC_TEST1,
            config = @MessageConfig(engineType = MessageEngineType.KAFKA, kafka = @MessageKafkaConfig(
                    clientConfig = @ClientConfig(idGenerateType = ClientIdGenerateType.SNOWFLAKE),
                    consumerConfig = @ConsumerConfig(groupId = "1", ackMode = MessageKafkaConsumerAckMode.COMMIT_EACH_OFFSET_AFTER_CONSUME, offsetResetMode = MessageKafkaConsumerOffsetResetMode.LATEST)
            ))
    )
    public void receive2(String message) {
        System.out.println("消费者2 -------- " + Times.getCurrentDateTime() + "-receive-" + message);
    }

}