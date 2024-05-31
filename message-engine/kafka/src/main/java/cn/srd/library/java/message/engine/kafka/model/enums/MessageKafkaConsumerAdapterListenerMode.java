// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka.model.enums;

import cn.srd.library.java.tool.lang.convert.Converts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.integration.kafka.inbound.KafkaMessageDrivenChannelAdapter;

/**
 * @author wjm
 * @since 2024-05-31 17:42
 */
@Getter
@AllArgsConstructor
public enum MessageKafkaConsumerAdapterListenerMode {

    RECORD(MessageKafkaConsumerListenerMode.RECORD.getCode(), KafkaMessageDrivenChannelAdapter.ListenerMode.record),
    BATCH(MessageKafkaConsumerListenerMode.BATCH.getCode(), KafkaMessageDrivenChannelAdapter.ListenerMode.batch),

    ;

    private final int listenerModeCode;

    private final KafkaMessageDrivenChannelAdapter.ListenerMode kafkaListenerMode;

    public static MessageKafkaConsumerAdapterListenerMode fromListenerMode(MessageKafkaConsumerListenerMode listenerMode) {
        return Converts.toEnumByValue(listenerMode.getCode(), MessageKafkaConsumerAdapterListenerMode.class);
    }

}