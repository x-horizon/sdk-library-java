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

    RECORD(MessageKafkaConsumerAckMode.RECORD.getCode(), ContainerProperties.AckMode.RECORD, new MessageKafkaConsumerAckByRecordStrategy()),

    BATCH(MessageKafkaConsumerAckMode.BATCH.getCode(), ContainerProperties.AckMode.BATCH, new MessageKafkaConsumerAckByBatchStrategy()),

    TIME(MessageKafkaConsumerAckMode.TIME.getCode(), ContainerProperties.AckMode.TIME, new MessageKafkaConsumerAckByTimeStrategy()),

    COUNT(MessageKafkaConsumerAckMode.COUNT.getCode(), ContainerProperties.AckMode.COUNT, new MessageKafkaConsumerAckByCountStrategy()),

    COUNT_TIME(MessageKafkaConsumerAckMode.COUNT_TIME.getCode(), ContainerProperties.AckMode.COUNT_TIME, new MessageKafkaConsumerAckByCountTimeStrategy()),

    MANUAL_IMMEDIATE(MessageKafkaConsumerAckMode.MANUAL_IMMEDIATE.getCode(), ContainerProperties.AckMode.MANUAL_IMMEDIATE, new MessageKafkaConsumerAckByManualImmediateStrategy()),

    MANUAL(MessageKafkaConsumerAckMode.MANUAL.getCode(), ContainerProperties.AckMode.MANUAL, new MessageKafkaConsumerAckByManualStrategy()),

    ;

    private final int ackModeCode;

    private final ContainerProperties.AckMode kafkaAckMode;

    private final MessageKafkaConsumerAckStrategy strategy;

    public static MessageKafkaConsumerAdapterAckMode fromAckMode(MessageKafkaConsumerAckMode ackMode) {
        return Converts.toEnumByValue(ackMode.getCode(), MessageKafkaConsumerAdapterAckMode.class);
    }

}