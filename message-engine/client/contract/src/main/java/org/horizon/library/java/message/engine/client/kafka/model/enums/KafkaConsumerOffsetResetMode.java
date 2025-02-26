package org.horizon.library.java.message.engine.client.kafka.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjm
 * @since 2024-05-31 11:46
 */
@Getter
@AllArgsConstructor
public enum KafkaConsumerOffsetResetMode {

    LATEST("latest"),
    EARLIEST("earliest"),
    THROW_IF_PREVIOUS_OFFSET_NOT_FOUND("none"),
    THROW_IF_OFFSET_NOT_EXIST("default"),

    ;

    private final String code;

}