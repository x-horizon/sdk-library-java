package org.horizon.library.java.message.engine.client.kafka.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjm
 * @since 2024-05-31 11:46
 */
@Getter
@AllArgsConstructor
public enum KafkaConsumerAckMode {

    RECORD(1),

    BATCH(2),

    TIME(3),

    COUNT(4),

    COUNT_TIME(5),

    MANUAL_IMMEDIATE(6),

    MANUAL(7),

    ;

    private final int code;

}