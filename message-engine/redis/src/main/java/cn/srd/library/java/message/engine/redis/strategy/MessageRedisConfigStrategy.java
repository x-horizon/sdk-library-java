// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.redis.strategy;

import cn.srd.library.java.contract.model.throwable.UnsupportedException;
import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.model.dto.MessageConfigDTO;
import cn.srd.library.java.message.engine.contract.strategy.MessageConfigStrategy;
import cn.srd.library.java.message.engine.redis.model.dto.MessageRedisConfigDTO;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-06-04 17:10
 */
@Slf4j
public class MessageRedisConfigStrategy extends MessageConfigStrategy<MessageRedisConfigDTO, MessageRedisConfigDTO.BrokerDTO, MessageRedisConfigDTO.ClientDTO, MessageRedisConfigDTO.ProducerDTO, MessageRedisConfigDTO.ConsumerDTO> {

    @Override
    protected Class<MessageRedisConfigDTO> getConfigType() {
        throw new UnsupportedException();
    }

    @Override
    protected MessageRedisConfigDTO.BrokerDTO getBrokerDTO() {
        throw new UnsupportedException();
    }

    @Override
    protected void registerClientFactory(MessageRedisConfigDTO.BrokerDTO brokerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected MessageRedisConfigDTO.ClientDTO getClientDTO(Annotation clientConfig, Method executeMethod) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerProducerFlow(MessageRedisConfigDTO.ProducerDTO producerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerConsumerFactory(MessageRedisConfigDTO.ConsumerDTO consumerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerConsumerFlow(MessageRedisConfigDTO.ConsumerDTO consumerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected MessageRedisConfigDTO.ProducerDTO getProducerDTO(Method executeMethod, MessageProducer producerAnnotation) {
        throw new UnsupportedException();
    }

    @Override
    protected MessageRedisConfigDTO.ConsumerDTO getConsumerDTO(Method executeMethod, MessageConsumer consumerAnnotation, MessageConfigDTO.ProducerDTO forwardProducerDTO) {
        throw new UnsupportedException();
    }

}