// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v5.strategy;

import cn.srd.library.java.contract.model.throwable.UnsupportedException;
import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.model.dto.MessageConfigDTO;
import cn.srd.library.java.message.engine.contract.model.dto.MessageVerificationConfigDTO;
import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;
import cn.srd.library.java.message.engine.contract.strategy.MessageConfigStrategy;
import cn.srd.library.java.message.engine.mqtt.v5.model.dto.MqttV5ConfigDTO;
import cn.srd.library.java.message.engine.mqtt.v5.model.property.MqttV5Property;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.dsl.IntegrationFlow;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-06-04 17:10
 */
@Slf4j
public class MqttV5ConfigStrategy extends MessageConfigStrategy<MqttV5Property, MqttV5ConfigDTO, MqttV5ConfigDTO.BrokerDTO, MqttV5ConfigDTO.ClientDTO, MqttV5ConfigDTO.ProducerDTO, MqttV5ConfigDTO.ConsumerDTO> {

    @Override
    protected MessageVerificationConfigDTO getVerificationConfigDTO(MqttV5ConfigDTO configDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected Class<MqttV5ConfigDTO> getConfigType() {
        throw new UnsupportedException();
    }

    @Override
    protected Class<MqttV5Property> getPropertyType() {
        throw new UnsupportedException();
    }

    @Override
    protected MessageEngineType getMessageEngineType() {
        throw new UnsupportedException();
    }

    @Override
    protected MqttV5ConfigDTO getMessageConfigDTO() {
        throw new UnsupportedException();
    }

    @Override
    protected MqttV5ConfigDTO.BrokerDTO getBrokerDTO() {
        throw new UnsupportedException();
    }

    @Override
    protected MqttV5ConfigDTO.ClientDTO getClientDTO(Annotation clientConfig, Method executeMethod) {
        throw new UnsupportedException();
    }

    @Override
    protected MqttV5ConfigDTO.ProducerDTO getProducerDTO(Method executeMethod, MessageProducer producerAnnotation) {
        throw new UnsupportedException();
    }

    @Override
    protected MqttV5ConfigDTO.ConsumerDTO getConsumerDTO(Method executeMethod, MessageConsumer consumerAnnotation, MessageConfigDTO.ProducerDTO forwardProducerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected IntegrationFlow getProducerFlow(MqttV5ConfigDTO.ProducerDTO producerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected IntegrationFlow getConsumerFlow(MqttV5ConfigDTO.ConsumerDTO consumerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void completeNativeConfigDTO(MqttV5ConfigDTO configDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerClientFactory(MqttV5ConfigDTO.BrokerDTO brokerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerProducerFactory(MqttV5ConfigDTO.ProducerDTO producerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerConsumerFactory(MqttV5ConfigDTO.ConsumerDTO consumerDTO) {
        throw new UnsupportedException();
    }

}