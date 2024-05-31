// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.contract.model.enums;

import cn.srd.library.java.message.engine.contract.strategy.MessageEngineStrategy;
import cn.srd.library.java.tool.enums.EnumAutowired;
import lombok.Getter;

/**
 * @author wjm
 * @since 2024-05-26 15:04
 */
@Getter
@EnumAutowired(rootClass = MessageEngineStrategy.class)
public enum MessageEngineType {

    KAFKA(1, "kafka"),
    MQTT_V3(2, "mqttV3"),
    MQTT_V5(3, "mattV5"),
    RABBITMQ(4, "rabbitmq"),
    REDIS(5, "redis"),
    ROCKETMQ(6, "rocketmq"),

    ;

    MessageEngineType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    private final int code;

    private final String description;

    private MessageEngineStrategy strategy;

}