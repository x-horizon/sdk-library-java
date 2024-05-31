// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka;

import cn.srd.library.java.contract.constant.text.SymbolConstant;
import cn.srd.library.java.message.engine.kafka.model.enums.MessageKafkaConsumerAckMode;
import cn.srd.library.java.message.engine.kafka.model.enums.MessageKafkaConsumerListenerMode;
import cn.srd.library.java.message.engine.kafka.model.enums.MessageKafkaConsumerOffsetResetMode;

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

    ProducerConfig producerConfig() default @ProducerConfig();

    ConsumerConfig consumerConfig() default @ConsumerConfig();

    @interface ProducerConfig {

    }

    @interface ConsumerConfig {

        String groupId() default SymbolConstant.EMPTY;

        boolean allowToAutoCreateTopic() default true;

        MessageKafkaConsumerAckMode ackMode() default MessageKafkaConsumerAckMode.COMMIT_BATCH_OFFSET_AFTER_CONSUME;

        String autoCommitOffsetInterval() default "5s";

        MessageKafkaConsumerListenerMode listenerMode() default MessageKafkaConsumerListenerMode.RECORD;

        MessageKafkaConsumerOffsetResetMode offsetResetMode() default MessageKafkaConsumerOffsetResetMode.THROW_IF_OFFSET_NOT_EXIST;

    }

}