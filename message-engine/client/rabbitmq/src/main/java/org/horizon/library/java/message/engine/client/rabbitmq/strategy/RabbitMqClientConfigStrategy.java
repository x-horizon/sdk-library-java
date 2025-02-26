package org.horizon.library.java.message.engine.client.rabbitmq.strategy;

import org.horizon.library.java.contract.model.throwable.UnsupportedException;
import org.horizon.library.java.message.engine.client.contract.MessageClientConsumer;
import org.horizon.library.java.message.engine.client.contract.MessageClientProducer;
import org.horizon.library.java.message.engine.client.contract.model.dto.MessageClientConfigDTO;
import org.horizon.library.java.message.engine.client.contract.model.dto.MessageClientVerificationConfigDTO;
import org.horizon.library.java.message.engine.client.contract.model.enums.MessageClientType;
import org.horizon.library.java.message.engine.client.contract.strategy.MessageClientConfigStrategy;
import org.horizon.library.java.message.engine.client.rabbitmq.model.dto.RabbitMqClientConfigDTO;
import org.horizon.library.java.message.engine.client.rabbitmq.model.property.RabbitMqClientProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.dsl.IntegrationFlow;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-06-04 17:10
 */
@Slf4j
public class RabbitMqClientConfigStrategy extends MessageClientConfigStrategy<RabbitMqClientProperty, RabbitMqClientConfigDTO, RabbitMqClientConfigDTO.BrokerDTO, RabbitMqClientConfigDTO.ClientDTO, RabbitMqClientConfigDTO.ProducerDTO, RabbitMqClientConfigDTO.ConsumerDTO> {

    @Override
    protected MessageClientVerificationConfigDTO getVerificationConfigDTO(RabbitMqClientConfigDTO configDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected Class<RabbitMqClientConfigDTO> getConfigType() {
        throw new UnsupportedException();
    }

    @Override
    protected Class<RabbitMqClientProperty> getPropertyType() {
        throw new UnsupportedException();
    }

    @Override
    protected MessageClientType getMessageEngineType() {
        throw new UnsupportedException();
    }

    @Override
    protected RabbitMqClientConfigDTO getMessageConfigDTO() {
        throw new UnsupportedException();
    }

    @Override
    protected RabbitMqClientConfigDTO.BrokerDTO getBrokerDTO() {
        throw new UnsupportedException();
    }

    @Override
    protected RabbitMqClientConfigDTO.ClientDTO getClientDTO(Annotation clientConfig, Method executeMethod) {
        throw new UnsupportedException();
    }

    @Override
    protected RabbitMqClientConfigDTO.ProducerDTO getProducerDTO(Method executeMethod, MessageClientProducer producerAnnotation) {
        throw new UnsupportedException();
    }

    @Override
    protected RabbitMqClientConfigDTO.ConsumerDTO getConsumerDTO(Method executeMethod, MessageClientConsumer consumerAnnotation, MessageClientConfigDTO.ProducerDTO forwardProducerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected IntegrationFlow getProducerFlow(RabbitMqClientConfigDTO.ProducerDTO producerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected IntegrationFlow getConsumerFlow(RabbitMqClientConfigDTO.ConsumerDTO consumerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void completeNativeConfigDTO(RabbitMqClientConfigDTO configDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerClientFactory(RabbitMqClientConfigDTO.BrokerDTO brokerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerProducerFactory(RabbitMqClientConfigDTO.ProducerDTO producerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerConsumerFactory(RabbitMqClientConfigDTO.ConsumerDTO consumerDTO) {
        throw new UnsupportedException();
    }

}