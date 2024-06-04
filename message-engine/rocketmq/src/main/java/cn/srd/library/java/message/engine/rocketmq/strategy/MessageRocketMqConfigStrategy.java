// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.rocketmq.strategy;

import cn.srd.library.java.message.engine.contract.strategy.MessageConfigStrategy;
import cn.srd.library.java.message.engine.rocketmq.model.dto.MessageRocketMqConfigDTO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wjm
 * @since 2024-06-04 17:10
 */
@Slf4j
public class MessageRocketMqConfigStrategy implements MessageConfigStrategy<MessageRocketMqConfigDTO> {

    @Override
    public MessageRocketMqConfigDTO customize() {
        return MessageRocketMqConfigDTO.builder().build();
    }

}