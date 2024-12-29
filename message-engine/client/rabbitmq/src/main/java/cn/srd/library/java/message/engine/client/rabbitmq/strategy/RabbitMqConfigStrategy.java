// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.client.rabbitmq.strategy;

import cn.srd.library.java.contract.model.throwable.UnsupportedException;
import cn.srd.library.java.message.engine.client.contract.MessageConsumer;
import cn.srd.library.java.message.engine.client.contract.MessageProducer;
import cn.srd.library.java.message.engine.client.contract.model.dto.MessageConfigDTO;
import cn.srd.library.java.message.engine.client.contract.model.dto.MessageVerificationConfigDTO;
import cn.srd.library.java.message.engine.client.contract.model.enums.MessageEngineType;
import cn.srd.library.java.message.engine.client.contract.strategy.MessageConfigStrategy;
import cn.srd.library.java.message.engine.client.rabbitmq.model.dto.RabbitMqConfigDTO;
import cn.srd.library.java.message.engine.client.rabbitmq.model.property.RabbitMqProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.dsl.IntegrationFlow;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-06-04 17:10
 */
@Slf4j
public class RabbitMqConfigStrategy extends MessageConfigStrategy<RabbitMqProperty, RabbitMqConfigDTO, RabbitMqConfigDTO.BrokerDTO, RabbitMqConfigDTO.ClientDTO, RabbitMqConfigDTO.ProducerDTO, RabbitMqConfigDTO.ConsumerDTO> {

    @Override
    protected MessageVerificationConfigDTO getVerificationConfigDTO(RabbitMqConfigDTO configDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected Class<RabbitMqConfigDTO> getConfigType() {
        throw new UnsupportedException();
    }

    @Override
    protected Class<RabbitMqProperty> getPropertyType() {
        throw new UnsupportedException();
    }

    @Override
    protected MessageEngineType getMessageEngineType() {
        throw new UnsupportedException();
    }

    @Override
    protected RabbitMqConfigDTO getMessageConfigDTO() {
        throw new UnsupportedException();
    }

    @Override
    protected RabbitMqConfigDTO.BrokerDTO getBrokerDTO() {
        throw new UnsupportedException();
    }

    @Override
    protected RabbitMqConfigDTO.ClientDTO getClientDTO(Annotation clientConfig, Method executeMethod) {
        throw new UnsupportedException();
    }

    @Override
    protected RabbitMqConfigDTO.ProducerDTO getProducerDTO(Method executeMethod, MessageProducer producerAnnotation) {
        throw new UnsupportedException();
    }

    @Override
    protected RabbitMqConfigDTO.ConsumerDTO getConsumerDTO(Method executeMethod, MessageConsumer consumerAnnotation, MessageConfigDTO.ProducerDTO forwardProducerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected IntegrationFlow getProducerFlow(RabbitMqConfigDTO.ProducerDTO producerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected IntegrationFlow getConsumerFlow(RabbitMqConfigDTO.ConsumerDTO consumerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void completeNativeConfigDTO(RabbitMqConfigDTO configDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerClientFactory(RabbitMqConfigDTO.BrokerDTO brokerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerProducerFactory(RabbitMqConfigDTO.ProducerDTO producerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerConsumerFactory(RabbitMqConfigDTO.ConsumerDTO consumerDTO) {
        throw new UnsupportedException();
    }

}