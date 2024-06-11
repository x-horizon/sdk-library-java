// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.rabbitmq.strategy;

import cn.srd.library.java.contract.model.throwable.UnsupportedException;
import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.model.dto.MessageConfigDTO;
import cn.srd.library.java.message.engine.contract.model.dto.MessageVerificationConfigDTO;
import cn.srd.library.java.message.engine.contract.strategy.MessageConfigStrategy;
import cn.srd.library.java.message.engine.rabbitmq.model.dto.MessageRabbitMqConfigDTO;
import cn.srd.library.java.message.engine.rabbitmq.model.properties.MessageRabbitMqProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.dsl.IntegrationFlow;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-06-04 17:10
 */
@Slf4j
public class MessageRabbitMqConfigStrategy extends MessageConfigStrategy<MessageRabbitMqProperties, MessageRabbitMqConfigDTO, MessageRabbitMqConfigDTO.BrokerDTO, MessageRabbitMqConfigDTO.ClientDTO, MessageRabbitMqConfigDTO.ProducerDTO, MessageRabbitMqConfigDTO.ConsumerDTO> {

    @Override
    protected MessageVerificationConfigDTO getVerificationConfigDTO(MessageRabbitMqConfigDTO configDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected Class<MessageRabbitMqConfigDTO> getConfigType() {
        throw new UnsupportedException();
    }

    @Override
    protected Class<MessageRabbitMqProperties> getPropertiesType() {
        throw new UnsupportedException();
    }

    @Override
    protected MessageRabbitMqConfigDTO.BrokerDTO getBrokerDTO() {
        throw new UnsupportedException();
    }

    @Override
    protected MessageRabbitMqConfigDTO.ClientDTO getClientDTO(Annotation clientConfig, Method executeMethod) {
        throw new UnsupportedException();
    }

    @Override
    protected MessageRabbitMqConfigDTO.ProducerDTO getProducerDTO(Method executeMethod, MessageProducer producerAnnotation) {
        throw new UnsupportedException();
    }

    @Override
    protected MessageRabbitMqConfigDTO.ConsumerDTO getConsumerDTO(Method executeMethod, MessageConsumer consumerAnnotation, MessageConfigDTO.ProducerDTO forwardProducerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected IntegrationFlow getProducerFlow(MessageRabbitMqConfigDTO.ProducerDTO producerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected IntegrationFlow getConsumerFlow(MessageRabbitMqConfigDTO.ConsumerDTO consumerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerClientFactory(MessageRabbitMqConfigDTO.BrokerDTO brokerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerProducerFactory(MessageRabbitMqConfigDTO.ProducerDTO producerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerConsumerFactory(MessageRabbitMqConfigDTO.ConsumerDTO consumerDTO) {
        throw new UnsupportedException();
    }

}