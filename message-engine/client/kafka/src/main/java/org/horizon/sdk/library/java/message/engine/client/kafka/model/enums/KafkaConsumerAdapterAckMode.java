package org.horizon.sdk.library.java.message.engine.client.kafka.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.horizon.sdk.library.java.message.engine.client.kafka.strategy.*;
import org.horizon.sdk.library.java.tool.lang.convert.Converts;
import org.springframework.kafka.listener.ContainerProperties;

/**
 * @author wjm
 * @since 2024-05-31 15:17
 */
@Getter
@AllArgsConstructor
public enum KafkaConsumerAdapterAckMode {

    RECORD(KafkaConsumerAckMode.RECORD.getCode(), ContainerProperties.AckMode.RECORD, new KafkaConsumerAckByRecordStrategy()),

    BATCH(KafkaConsumerAckMode.BATCH.getCode(), ContainerProperties.AckMode.BATCH, new KafkaConsumerAckByBatchStrategy()),

    TIME(KafkaConsumerAckMode.TIME.getCode(), ContainerProperties.AckMode.TIME, new KafkaConsumerAckByTimeStrategy()),

    COUNT(KafkaConsumerAckMode.COUNT.getCode(), ContainerProperties.AckMode.COUNT, new KafkaConsumerAckByCountStrategy()),

    COUNT_TIME(KafkaConsumerAckMode.COUNT_TIME.getCode(), ContainerProperties.AckMode.COUNT_TIME, new KafkaConsumerAckByCountTimeStrategy()),

    MANUAL_IMMEDIATE(KafkaConsumerAckMode.MANUAL_IMMEDIATE.getCode(), ContainerProperties.AckMode.MANUAL_IMMEDIATE, new KafkaConsumerAckByManualImmediateStrategy()),

    MANUAL(KafkaConsumerAckMode.MANUAL.getCode(), ContainerProperties.AckMode.MANUAL, new KafkaConsumerAckByManualStrategy()),

    ;

    private final int ackModeCode;

    private final ContainerProperties.AckMode kafkaAckMode;

    private final KafkaConsumerAckStrategy strategy;

    public static KafkaConsumerAdapterAckMode fromAckMode(KafkaConsumerAckMode ackMode) {
        return Converts.toEnumByValue(ackMode.getCode(), KafkaConsumerAdapterAckMode.class);
    }

}