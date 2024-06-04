// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v5.strategy;

import cn.srd.library.java.message.engine.contract.strategy.MessageConfigStrategy;
import cn.srd.library.java.message.engine.mqtt.v5.model.dto.MessageMqttV5ConfigDTO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wjm
 * @since 2024-06-04 17:10
 */
@Slf4j
public class MessageMqttV5ConfigStrategy implements MessageConfigStrategy<MessageMqttV5ConfigDTO> {

    @Override
    public MessageMqttV5ConfigDTO customize() {
        return MessageMqttV5ConfigDTO.builder().build();
    }

}