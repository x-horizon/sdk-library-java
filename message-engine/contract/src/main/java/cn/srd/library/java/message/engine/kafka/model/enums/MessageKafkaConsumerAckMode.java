// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjm
 * @since 2024-05-31 11:46
 */
@Getter
@AllArgsConstructor
public enum MessageKafkaConsumerAckMode {

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