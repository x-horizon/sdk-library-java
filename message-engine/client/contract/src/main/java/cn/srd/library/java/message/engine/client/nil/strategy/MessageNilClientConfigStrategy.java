package cn.srd.library.java.message.engine.client.nil.strategy;

import cn.srd.library.java.message.engine.client.contract.MessageClientConsumer;
import cn.srd.library.java.message.engine.client.contract.MessageClientProducer;
import cn.srd.library.java.message.engine.client.contract.model.dto.MessageClientConfigDTO;
import cn.srd.library.java.message.engine.client.contract.model.dto.MessageClientVerificationConfigDTO;
import cn.srd.library.java.message.engine.client.contract.model.enums.MessageClientType;
import cn.srd.library.java.message.engine.client.contract.strategy.MessageClientConfigStrategy;
import cn.srd.library.java.message.engine.client.nil.model.dto.MessageNilClientConfigDTO;
import cn.srd.library.java.message.engine.client.nil.model.property.MessageNilClientProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.dsl.IntegrationFlow;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-06-04 17:10
 */
@Slf4j
public class MessageNilClientConfigStrategy extends MessageClientConfigStrategy<MessageNilClientProperty, MessageNilClientConfigDTO, MessageNilClientConfigDTO.BrokerDTO, MessageNilClientConfigDTO.ClientDTO, MessageNilClientConfigDTO.ProducerDTO, MessageNilClientConfigDTO.ConsumerDTO> {

    @Override
    protected MessageClientVerificationConfigDTO getVerificationConfigDTO(MessageNilClientConfigDTO configDTO) {
        return new MessageClientVerificationConfigDTO();
    }

    @Override
    protected Class<MessageNilClientConfigDTO> getConfigType() {
        return MessageNilClientConfigDTO.class;
    }

    @Override
    protected Class<MessageNilClientProperty> getPropertyType() {
        return MessageNilClientProperty.class;
    }

    @Override
    protected MessageClientType getMessageEngineType() {
        return MessageClientType.NIL;
    }

    @Override
    protected MessageNilClientConfigDTO getMessageConfigDTO() {
        return null;
    }

    @Override
    protected MessageNilClientConfigDTO.BrokerDTO getBrokerDTO() {
        return null;
    }

    @Override
    protected MessageNilClientConfigDTO.ClientDTO getClientDTO(Annotation clientConfig, Method executeMethod) {
        return null;
    }

    @Override
    protected MessageNilClientConfigDTO.ProducerDTO getProducerDTO(Method executeMethod, MessageClientProducer producerAnnotation) {
        return null;
    }

    @Override
    protected MessageNilClientConfigDTO.ConsumerDTO getConsumerDTO(Method executeMethod, MessageClientConsumer consumerAnnotation, MessageClientConfigDTO.ProducerDTO forwardProducerDTO) {
        return null;
    }

    @Override
    protected IntegrationFlow getProducerFlow(MessageNilClientConfigDTO.ProducerDTO producerDTO) {
        return null;
    }

    @Override
    protected IntegrationFlow getConsumerFlow(MessageNilClientConfigDTO.ConsumerDTO consumerDTO) {
        return null;
    }

    @Override
    protected void completeNativeConfigDTO(MessageNilClientConfigDTO configDTO) {

    }

    @Override
    protected void registerClientFactory(MessageNilClientConfigDTO.BrokerDTO brokerDTO) {

    }

    @Override
    protected void registerProducerFactory(MessageNilClientConfigDTO.ProducerDTO producerDTO) {

    }

    @Override
    protected void registerConsumerFactory(MessageNilClientConfigDTO.ConsumerDTO consumerDTO) {

    }

}