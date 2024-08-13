// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.nil.strategy;

import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.model.dto.MessageConfigDTO;
import cn.srd.library.java.message.engine.contract.model.dto.MessageVerificationConfigDTO;
import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;
import cn.srd.library.java.message.engine.contract.strategy.MessageConfigStrategy;
import cn.srd.library.java.message.engine.nil.model.dto.MessageNilConfigDTO;
import cn.srd.library.java.message.engine.nil.model.property.MessageNilProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.dsl.IntegrationFlow;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-06-04 17:10
 */
@Slf4j
public class MessageNilConfigStrategy extends MessageConfigStrategy<MessageNilProperty, MessageNilConfigDTO, MessageNilConfigDTO.BrokerDTO, MessageNilConfigDTO.ClientDTO, MessageNilConfigDTO.ProducerDTO, MessageNilConfigDTO.ConsumerDTO> {

    @Override
    protected MessageVerificationConfigDTO getVerificationConfigDTO(MessageNilConfigDTO configDTO) {
        return new MessageVerificationConfigDTO();
    }

    @Override
    protected Class<MessageNilConfigDTO> getConfigType() {
        return MessageNilConfigDTO.class;
    }

    @Override
    protected Class<MessageNilProperty> getPropertyType() {
        return MessageNilProperty.class;
    }

    @Override
    protected MessageEngineType getMessageEngineType() {
        return MessageEngineType.NIL;
    }

    @Override
    protected MessageNilConfigDTO getMessageConfigDTO() {
        return null;
    }

    @Override
    protected MessageNilConfigDTO.BrokerDTO getBrokerDTO() {
        return null;
    }

    @Override
    protected MessageNilConfigDTO.ClientDTO getClientDTO(Annotation clientConfig, Method executeMethod) {
        return null;
    }

    @Override
    protected MessageNilConfigDTO.ProducerDTO getProducerDTO(Method executeMethod, MessageProducer producerAnnotation) {
        return null;
    }

    @Override
    protected MessageNilConfigDTO.ConsumerDTO getConsumerDTO(Method executeMethod, MessageConsumer consumerAnnotation, MessageConfigDTO.ProducerDTO forwardProducerDTO) {
        return null;
    }

    @Override
    protected IntegrationFlow getProducerFlow(MessageNilConfigDTO.ProducerDTO producerDTO) {
        return null;
    }

    @Override
    protected IntegrationFlow getConsumerFlow(MessageNilConfigDTO.ConsumerDTO consumerDTO) {
        return null;
    }

    @Override
    protected void completeNativeConfigDTO(MessageNilConfigDTO configDTO) {

    }

    @Override
    protected void registerClientFactory(MessageNilConfigDTO.BrokerDTO brokerDTO) {

    }

    @Override
    protected void registerProducerFactory(MessageNilConfigDTO.ProducerDTO producerDTO) {

    }

    @Override
    protected void registerConsumerFactory(MessageNilConfigDTO.ConsumerDTO consumerDTO) {

    }

}