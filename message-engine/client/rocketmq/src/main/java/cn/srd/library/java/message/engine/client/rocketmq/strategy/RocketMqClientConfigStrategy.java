package cn.srd.library.java.message.engine.client.rocketmq.strategy;

import cn.srd.library.java.contract.model.throwable.UnsupportedException;
import cn.srd.library.java.message.engine.client.contract.MessageClientConsumer;
import cn.srd.library.java.message.engine.client.contract.MessageClientProducer;
import cn.srd.library.java.message.engine.client.contract.model.dto.MessageClientConfigDTO;
import cn.srd.library.java.message.engine.client.contract.model.dto.MessageClientVerificationConfigDTO;
import cn.srd.library.java.message.engine.client.contract.model.enums.MessageClientType;
import cn.srd.library.java.message.engine.client.contract.strategy.MessageClientConfigStrategy;
import cn.srd.library.java.message.engine.client.rocketmq.model.dto.RocketMqClientConfigDTO;
import cn.srd.library.java.message.engine.client.rocketmq.model.property.RocketMqClientProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.dsl.IntegrationFlow;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-06-04 17:10
 */
@Slf4j
public class RocketMqClientConfigStrategy extends MessageClientConfigStrategy<RocketMqClientProperty, RocketMqClientConfigDTO, RocketMqClientConfigDTO.BrokerDTO, RocketMqClientConfigDTO.ClientDTO, RocketMqClientConfigDTO.ProducerDTO, RocketMqClientConfigDTO.ConsumerDTO> {

    @Override
    protected MessageClientVerificationConfigDTO getVerificationConfigDTO(RocketMqClientConfigDTO configDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected Class<RocketMqClientConfigDTO> getConfigType() {
        throw new UnsupportedException();
    }

    @Override
    protected Class<RocketMqClientProperty> getPropertyType() {
        throw new UnsupportedException();
    }

    @Override
    protected MessageClientType getMessageEngineType() {
        throw new UnsupportedException();
    }

    @Override
    protected RocketMqClientConfigDTO getMessageConfigDTO() {
        throw new UnsupportedException();
    }

    @Override
    protected RocketMqClientConfigDTO.BrokerDTO getBrokerDTO() {
        throw new UnsupportedException();
    }

    @Override
    protected RocketMqClientConfigDTO.ClientDTO getClientDTO(Annotation clientConfig, Method executeMethod) {
        throw new UnsupportedException();
    }

    @Override
    protected RocketMqClientConfigDTO.ProducerDTO getProducerDTO(Method executeMethod, MessageClientProducer producerAnnotation) {
        throw new UnsupportedException();
    }

    @Override
    protected RocketMqClientConfigDTO.ConsumerDTO getConsumerDTO(Method executeMethod, MessageClientConsumer consumerAnnotation, MessageClientConfigDTO.ProducerDTO forwardProducerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected IntegrationFlow getProducerFlow(RocketMqClientConfigDTO.ProducerDTO producerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected IntegrationFlow getConsumerFlow(RocketMqClientConfigDTO.ConsumerDTO consumerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void completeNativeConfigDTO(RocketMqClientConfigDTO configDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerClientFactory(RocketMqClientConfigDTO.BrokerDTO brokerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerProducerFactory(RocketMqClientConfigDTO.ProducerDTO producerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerConsumerFactory(RocketMqClientConfigDTO.ConsumerDTO consumerDTO) {
        throw new UnsupportedException();
    }

}