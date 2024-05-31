// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjm
 * @since 2024-05-31 17:41
 */
@Getter
@AllArgsConstructor
public enum MessageKafkaConsumerListenerMode {

    RECORD(1),

    BATCH(2),

    ;

    private final int code;

}