// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka.model.enums;

import cn.srd.library.java.message.engine.kafka.strategy.*;
import cn.srd.library.java.tool.lang.convert.Converts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.kafka.listener.ContainerProperties;

/**
 * @author wjm
 * @since 2024-05-31 15:17
 */
@Getter
@AllArgsConstructor
public enum MessageKafkaConsumerAdapterAckMode {

    COMMIT_EACH_OFFSET_AFTER_CONSUME(
            MessageKafkaConsumerAckMode.COMMIT_EACH_OFFSET_AFTER_CONSUME.getCode(),
            ContainerProperties.AckMode.RECORD,
            new MessageKafkaConsumerAckByCommitEachOffsetAfterConsumeStrategy()
    ),

    COMMIT_BATCH_OFFSET_AFTER_CONSUME(
            MessageKafkaConsumerAckMode.COMMIT_BATCH_OFFSET_AFTER_CONSUME.getCode(),
            ContainerProperties.AckMode.BATCH,
            new MessageKafkaConsumerAckByCommitBatchOffsetAfterConsumeStrategy()
    ),

    COMMIT_BATCH_OFFSET_BY_LAST_ACK_TIME(
            MessageKafkaConsumerAckMode.COMMIT_BATCH_OFFSET_BY_LAST_ACK_TIME.getCode(),
            ContainerProperties.AckMode.TIME,
            new MessageKafkaConsumerAckByCommitBatchOffsetByLastAckTimeStrategy()
    ),

    COMMIT_BATCH_OFFSET_BY_ACK_COUNT(
            MessageKafkaConsumerAckMode.COMMIT_BATCH_OFFSET_BY_ACK_COUNT.getCode(),
            ContainerProperties.AckMode.COUNT,
            new MessageKafkaConsumerAckByCommitBatchOffsetByAckCountStrategy()
    ),

    COMMIT_BATCH_OFFSET_BY_ACK_COUNT_OR_LAST_ACK_TIME(
            MessageKafkaConsumerAckMode.COMMIT_BATCH_OFFSET_BY_ACK_COUNT_OR_LAST_ACK_TIME.getCode(),
            ContainerProperties.AckMode.COUNT_TIME,
            new MessageKafkaConsumerAckByCommitBatchOffsetByAckCountOrLastAckTimeStrategy()
    ),

    MANUAL_COMMIT_BATCH_OFFSET_IMMEDIATE(
            MessageKafkaConsumerAckMode.MANUAL_COMMIT_BATCH_OFFSET_IMMEDIATE.getCode(),
            ContainerProperties.AckMode.MANUAL_IMMEDIATE,
            new MessageKafkaConsumerAckByManualCommitBatchOffsetImmediateStrategy()
    ),

    MANUAL_COMMIT_BATCH_OFFSET_AFTER_NEXT_POLL(
            MessageKafkaConsumerAckMode.MANUAL_COMMIT_BATCH_OFFSET_AFTER_NEXT_POLL.getCode(),
            ContainerProperties.AckMode.MANUAL,
            new MessageKafkaConsumerAckByManualCommitBatchOffsetAfterNextPollStrategy()
    ),

    ;

    private final int ackModeCode;

    private final ContainerProperties.AckMode kafkaAckMode;

    private final MessageKafkaConsumerAckStrategy strategy;

    public static MessageKafkaConsumerAdapterAckMode fromAckMode(MessageKafkaConsumerAckMode ackMode) {
        return Converts.toEnumByValue(ackMode.getCode(), MessageKafkaConsumerAdapterAckMode.class);
    }

}