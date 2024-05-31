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
public enum MessageKafkaConsumerOffsetResetMode {

    LATEST("latest"),
    EARLIEST("earliest"),
    THROW_IF_PREVIOUS_OFFSET_NOT_FOUND("none"),
    THROW_IF_OFFSET_NOT_EXIST("default"),

    ;

    private final String code;

}