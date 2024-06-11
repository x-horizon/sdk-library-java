// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka;

import cn.srd.library.java.message.engine.contract.model.enums.ClientIdGenerateType;
import cn.srd.library.java.message.engine.kafka.model.enums.MessageKafkaConsumerAckMode;
import cn.srd.library.java.message.engine.kafka.model.enums.MessageKafkaConsumerListenerMode;
import cn.srd.library.java.message.engine.kafka.model.enums.MessageKafkaConsumerOffsetResetMode;
import cn.srd.library.java.message.engine.kafka.support.MessageKafkaDefaultConfigConstant;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wjm
 * @since 2024-05-30 15:36
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface MessageKafkaConfig {

    ClientConfig clientConfig() default @ClientConfig();

    ProducerConfig producerConfig() default @ProducerConfig();

    ConsumerConfig consumerConfig() default @ConsumerConfig();

    @interface ClientConfig {

        // TODO wjm 此处实现不够好，与 snowflake id 强绑定，客户端不一定需要用到 snowflake id，目前客户端必须提供正确的 redis 配置，否则项目启动报错
        ClientIdGenerateType idGenerateType() default ClientIdGenerateType.SNOWFLAKE;

    }

    @interface ProducerConfig {

    }

    @interface ConsumerConfig {

        String groupId() default MessageKafkaDefaultConfigConstant.Consumer.GROUP_ID;

        boolean allowToAutoCreateTopic() default MessageKafkaDefaultConfigConstant.Consumer.ALLOW_TO_AUTO_CREATE_TOPIC;

        String autoCommitOffsetInterval() default MessageKafkaDefaultConfigConstant.Consumer.AUTO_COMMIT_OFFSET_INTERVAL;

        MessageKafkaConsumerAckMode ackMode() default MessageKafkaConsumerAckMode.RECORD;

        MessageKafkaConsumerListenerMode listenerMode() default MessageKafkaConsumerListenerMode.RECORD;

        MessageKafkaConsumerOffsetResetMode offsetResetMode() default MessageKafkaConsumerOffsetResetMode.LATEST;

    }

}