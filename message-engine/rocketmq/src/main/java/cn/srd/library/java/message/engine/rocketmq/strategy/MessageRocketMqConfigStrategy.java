// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.rocketmq.strategy;

import cn.srd.library.java.contract.model.throwable.UnsupportedException;
import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.model.dto.MessageConfigDTO;
import cn.srd.library.java.message.engine.contract.strategy.MessageConfigStrategy;
import cn.srd.library.java.message.engine.rocketmq.model.dto.MessageRocketMqConfigDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.dsl.IntegrationFlow;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-06-04 17:10
 */
@Slf4j
public class MessageRocketMqConfigStrategy extends MessageConfigStrategy<MessageRocketMqConfigDTO, MessageRocketMqConfigDTO.BrokerDTO, MessageRocketMqConfigDTO.ClientDTO, MessageRocketMqConfigDTO.ProducerDTO, MessageRocketMqConfigDTO.ConsumerDTO> {

    @Override
    protected Class<MessageRocketMqConfigDTO> getConfigType() {
        throw new UnsupportedException();
    }

    @Override
    protected MessageRocketMqConfigDTO.BrokerDTO getBrokerDTO() {
        throw new UnsupportedException();
    }

    @Override
    protected MessageRocketMqConfigDTO.ClientDTO getClientDTO(Annotation clientConfig, Method executeMethod) {
        throw new UnsupportedException();
    }

    @Override
    protected MessageRocketMqConfigDTO.ProducerDTO getProducerDTO(Method executeMethod, MessageProducer producerAnnotation) {
        throw new UnsupportedException();
    }

    @Override
    protected MessageRocketMqConfigDTO.ConsumerDTO getConsumerDTO(Method executeMethod, MessageConsumer consumerAnnotation, MessageConfigDTO.ProducerDTO forwardProducerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected IntegrationFlow getProducerFlow(MessageRocketMqConfigDTO.ProducerDTO producerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected IntegrationFlow getConsumerFlow(MessageRocketMqConfigDTO.ConsumerDTO consumerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerClientFactory(MessageRocketMqConfigDTO.BrokerDTO brokerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerConsumerFactory(MessageRocketMqConfigDTO.ConsumerDTO consumerDTO) {
        throw new UnsupportedException();
    }

}