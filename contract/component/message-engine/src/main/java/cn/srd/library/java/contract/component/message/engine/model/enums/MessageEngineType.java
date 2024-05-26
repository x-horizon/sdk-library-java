// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.component.message.engine.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjm
 * @since 2024-05-26 15:04
 */
@Getter
@AllArgsConstructor
public enum MessageEngineType {

    KAFKA(1),
    MQTT_V3(2),
    MQTT_V5(3),
    RABBITMQ(4),
    REDIS(5),
    ROCKETMQ(6),

    ;

    /**
     * the status code
     */
    private final int status;

}