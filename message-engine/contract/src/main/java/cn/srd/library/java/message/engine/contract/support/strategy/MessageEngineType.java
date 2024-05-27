// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.contract.support.strategy;

import cn.srd.library.java.tool.enums.autowired.EnumAutowired;
import lombok.Getter;

/**
 * @author wjm
 * @since 2024-05-26 15:04
 */
@Getter
@EnumAutowired(rootClass = MessageEngineStrategy.class)
public enum MessageEngineType {

    KAFKA(1),
    MQTT_V3(2),
    MQTT_V5(3),
    RABBITMQ(4),
    REDIS(5),
    ROCKETMQ(6),

    ;

    MessageEngineType(int status) {
        this.status = status;
    }

    private final int status;

    private MessageEngineStrategy strategy;

}