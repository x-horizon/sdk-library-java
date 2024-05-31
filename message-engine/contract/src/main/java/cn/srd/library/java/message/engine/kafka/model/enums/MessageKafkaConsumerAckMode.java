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

    COMMIT_EACH_OFFSET_AFTER_CONSUME(1),

    COMMIT_BATCH_OFFSET_AFTER_CONSUME(2),

    COMMIT_BATCH_OFFSET_BY_LAST_ACK_TIME(3),

    COMMIT_BATCH_OFFSET_BY_ACK_COUNT(4),

    COMMIT_BATCH_OFFSET_BY_ACK_COUNT_OR_LAST_ACK_TIME(5),

    MANUAL_COMMIT_BATCH_OFFSET_IMMEDIATE(6),

    MANUAL_COMMIT_BATCH_OFFSET_AFTER_NEXT_POLL(7),

    ;

    private final int code;

}