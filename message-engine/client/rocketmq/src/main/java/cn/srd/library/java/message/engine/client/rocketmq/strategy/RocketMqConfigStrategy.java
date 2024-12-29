// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.client.rocketmq.strategy;

import cn.srd.library.java.contract.model.throwable.UnsupportedException;
import cn.srd.library.java.message.engine.client.contract.MessageConsumer;
import cn.srd.library.java.message.engine.client.contract.MessageProducer;
import cn.srd.library.java.message.engine.client.contract.model.dto.MessageConfigDTO;
import cn.srd.library.java.message.engine.client.contract.model.dto.MessageVerificationConfigDTO;
import cn.srd.library.java.message.engine.client.contract.model.enums.MessageEngineType;
import cn.srd.library.java.message.engine.client.contract.strategy.MessageConfigStrategy;
import cn.srd.library.java.message.engine.client.rocketmq.model.dto.RocketMqConfigDTO;
import cn.srd.library.java.message.engine.client.rocketmq.model.property.RocketMqProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.dsl.IntegrationFlow;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-06-04 17:10
 */
@Slf4j
public class RocketMqConfigStrategy extends MessageConfigStrategy<RocketMqProperty, RocketMqConfigDTO, RocketMqConfigDTO.BrokerDTO, RocketMqConfigDTO.ClientDTO, RocketMqConfigDTO.ProducerDTO, RocketMqConfigDTO.ConsumerDTO> {

    @Override
    protected MessageVerificationConfigDTO getVerificationConfigDTO(RocketMqConfigDTO configDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected Class<RocketMqConfigDTO> getConfigType() {
        throw new UnsupportedException();
    }

    @Override
    protected Class<RocketMqProperty> getPropertyType() {
        throw new UnsupportedException();
    }

    @Override
    protected MessageEngineType getMessageEngineType() {
        throw new UnsupportedException();
    }

    @Override
    protected RocketMqConfigDTO getMessageConfigDTO() {
        throw new UnsupportedException();
    }

    @Override
    protected RocketMqConfigDTO.BrokerDTO getBrokerDTO() {
        throw new UnsupportedException();
    }

    @Override
    protected RocketMqConfigDTO.ClientDTO getClientDTO(Annotation clientConfig, Method executeMethod) {
        throw new UnsupportedException();
    }

    @Override
    protected RocketMqConfigDTO.ProducerDTO getProducerDTO(Method executeMethod, MessageProducer producerAnnotation) {
        throw new UnsupportedException();
    }

    @Override
    protected RocketMqConfigDTO.ConsumerDTO getConsumerDTO(Method executeMethod, MessageConsumer consumerAnnotation, MessageConfigDTO.ProducerDTO forwardProducerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected IntegrationFlow getProducerFlow(RocketMqConfigDTO.ProducerDTO producerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected IntegrationFlow getConsumerFlow(RocketMqConfigDTO.ConsumerDTO consumerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void completeNativeConfigDTO(RocketMqConfigDTO configDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerClientFactory(RocketMqConfigDTO.BrokerDTO brokerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerProducerFactory(RocketMqConfigDTO.ProducerDTO producerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerConsumerFactory(RocketMqConfigDTO.ConsumerDTO consumerDTO) {
        throw new UnsupportedException();
    }

}