package org.horizon.sdk.library.java.message.engine.client.kafka.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjm
 * @since 2024-05-31 17:41
 */
@Getter
@AllArgsConstructor
public enum KafkaConsumerListenerMode {

    RECORD(1),

    BATCH(2),

    ;

    private final int code;

}