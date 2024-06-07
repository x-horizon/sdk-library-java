// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.rocketmq.strategy;

import cn.srd.library.java.contract.model.throwable.UnsupportedException;
import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.model.dto.MessageConfigDTO;
import cn.srd.library.java.message.engine.contract.strategy.MessageConfigStrategy;
import cn.srd.library.java.message.engine.rocketmq.model.dto.MessageRocketMqConfigDTO;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-06-04 17:10
 */
@Slf4j
public class MessageRocketMqConfigStrategy extends MessageConfigStrategy<MessageRocketMqConfigDTO, MessageRocketMqConfigDTO.BrokerDTO, MessageRocketMqConfigDTO.ClientDTO, MessageRocketMqConfigDTO.ProducerDTO, MessageRocketMqConfigDTO.ConsumerDTO> {

    @Override
    public MessageRocketMqConfigDTO initialize() {
        throw new UnsupportedException();
    }

    @Override
    public void registerProducerRouter(Method executeMethod, MessageConfigDTO.ProducerDTO producerDTO) {
        throw new UnsupportedException();
    }

    @Override
    public void onInitializeComplete() {
        throw new UnsupportedException();
    }

    @Override
    public MessageConfigDTO.ProducerDTO registerProducer(Method executeMethod, MessageProducer producerAnnotation) {
        throw new UnsupportedException();
    }

}