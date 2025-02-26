package org.horizon.library.java.message.engine.contract.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author wjm
 * @since 2024-12-29 18:08
 */
@Getter
@RequiredArgsConstructor
public enum MessageEngineType {

    KAFKA(1, "kafka"),
    MQTT(2, "mqtt"),
    MQTT_V3(3, "mqttV3"),
    MQTT_V5(4, "mqttV5"),
    NIL(5, "nil"),
    RABBITMQ(6, "rabbitmq"),
    REDIS(7, "redis"),
    ROCKETMQ(8, "rocketmq"),

    ;

    private final int code;

    private final String description;

}