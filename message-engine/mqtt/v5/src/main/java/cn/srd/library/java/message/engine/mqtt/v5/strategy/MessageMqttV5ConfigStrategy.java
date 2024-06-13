// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v5.strategy;

import cn.srd.library.java.contract.model.throwable.UnsupportedException;
import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.model.dto.MessageConfigDTO;
import cn.srd.library.java.message.engine.contract.model.dto.MessageVerificationConfigDTO;
import cn.srd.library.java.message.engine.contract.strategy.MessageConfigStrategy;
import cn.srd.library.java.message.engine.mqtt.v5.model.dto.MessageMqttV5ConfigDTO;
import cn.srd.library.java.message.engine.mqtt.v5.model.properties.MessageMqttV5Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.dsl.IntegrationFlow;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-06-04 17:10
 */
@Slf4j
public class MessageMqttV5ConfigStrategy extends MessageConfigStrategy<MessageMqttV5Properties, MessageMqttV5ConfigDTO, MessageMqttV5ConfigDTO.BrokerDTO, MessageMqttV5ConfigDTO.ClientDTO, MessageMqttV5ConfigDTO.ProducerDTO, MessageMqttV5ConfigDTO.ConsumerDTO> {

    @Override
    protected MessageVerificationConfigDTO getVerificationConfigDTO(MessageMqttV5ConfigDTO configDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected Class<MessageMqttV5ConfigDTO> getConfigType() {
        throw new UnsupportedException();
    }

    @Override
    protected Class<MessageMqttV5Properties> getPropertiesType() {
        throw new UnsupportedException();
    }

    @Override
    protected MessageMqttV5ConfigDTO.BrokerDTO getBrokerDTO() {
        throw new UnsupportedException();
    }

    @Override
    protected MessageMqttV5ConfigDTO.ClientDTO getClientDTO(Annotation clientConfig, Method executeMethod) {
        throw new UnsupportedException();
    }

    @Override
    protected MessageMqttV5ConfigDTO.ProducerDTO getProducerDTO(Method executeMethod, MessageProducer producerAnnotation) {
        throw new UnsupportedException();
    }

    @Override
    protected MessageMqttV5ConfigDTO.ConsumerDTO getConsumerDTO(Method executeMethod, MessageConsumer consumerAnnotation, MessageConfigDTO.ProducerDTO forwardProducerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected IntegrationFlow getProducerFlow(MessageMqttV5ConfigDTO.ProducerDTO producerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected IntegrationFlow getConsumerFlow(MessageMqttV5ConfigDTO.ConsumerDTO consumerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void completeNativeConfigDTO(MessageMqttV5ConfigDTO configDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerClientFactory(MessageMqttV5ConfigDTO.BrokerDTO brokerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerProducerFactory(MessageMqttV5ConfigDTO.ProducerDTO producerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerConsumerFactory(MessageMqttV5ConfigDTO.ConsumerDTO consumerDTO) {
        throw new UnsupportedException();
    }

}