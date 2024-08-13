// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.redis.stream.strategy;

import cn.srd.library.java.contract.model.throwable.UnsupportedException;
import cn.srd.library.java.message.engine.contract.MessageConsumer;
import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.model.dto.MessageConfigDTO;
import cn.srd.library.java.message.engine.contract.model.dto.MessageVerificationConfigDTO;
import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;
import cn.srd.library.java.message.engine.contract.strategy.MessageConfigStrategy;
import cn.srd.library.java.message.engine.redis.stream.model.dto.RedisStreamConfigDTO;
import cn.srd.library.java.message.engine.redis.stream.model.property.RedisStreamProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.dsl.IntegrationFlow;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-06-04 17:10
 */
@Slf4j
public class RedisStreamConfigStrategy extends MessageConfigStrategy<RedisStreamProperty, RedisStreamConfigDTO, RedisStreamConfigDTO.BrokerDTO, RedisStreamConfigDTO.ClientDTO, RedisStreamConfigDTO.ProducerDTO, RedisStreamConfigDTO.ConsumerDTO> {

    @Override
    protected MessageVerificationConfigDTO getVerificationConfigDTO(RedisStreamConfigDTO configDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected Class<RedisStreamConfigDTO> getConfigType() {
        throw new UnsupportedException();
    }

    @Override
    protected Class<RedisStreamProperty> getPropertyType() {
        throw new UnsupportedException();
    }

    @Override
    protected MessageEngineType getMessageEngineType() {
        throw new UnsupportedException();
    }

    @Override
    protected RedisStreamConfigDTO getMessageConfigDTO() {
        throw new UnsupportedException();
    }

    @Override
    protected RedisStreamConfigDTO.BrokerDTO getBrokerDTO() {
        throw new UnsupportedException();
    }

    @Override
    protected RedisStreamConfigDTO.ClientDTO getClientDTO(Annotation clientConfig, Method executeMethod) {
        throw new UnsupportedException();
    }

    @Override
    protected RedisStreamConfigDTO.ProducerDTO getProducerDTO(Method executeMethod, MessageProducer producerAnnotation) {
        throw new UnsupportedException();
    }

    @Override
    protected RedisStreamConfigDTO.ConsumerDTO getConsumerDTO(Method executeMethod, MessageConsumer consumerAnnotation, MessageConfigDTO.ProducerDTO forwardProducerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected IntegrationFlow getProducerFlow(RedisStreamConfigDTO.ProducerDTO producerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected IntegrationFlow getConsumerFlow(RedisStreamConfigDTO.ConsumerDTO consumerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void completeNativeConfigDTO(RedisStreamConfigDTO configDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerClientFactory(RedisStreamConfigDTO.BrokerDTO brokerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerProducerFactory(RedisStreamConfigDTO.ProducerDTO producerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerConsumerFactory(RedisStreamConfigDTO.ConsumerDTO consumerDTO) {
        throw new UnsupportedException();
    }

}