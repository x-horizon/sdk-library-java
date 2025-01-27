package cn.library.java.message.engine.client.mqtt.v5.strategy;

import cn.library.java.contract.model.throwable.UnsupportedException;
import cn.library.java.message.engine.client.contract.MessageClientConsumer;
import cn.library.java.message.engine.client.contract.MessageClientProducer;
import cn.library.java.message.engine.client.contract.model.dto.MessageClientConfigDTO;
import cn.library.java.message.engine.client.contract.model.dto.MessageClientVerificationConfigDTO;
import cn.library.java.message.engine.client.contract.model.enums.MessageClientType;
import cn.library.java.message.engine.client.contract.strategy.MessageClientConfigStrategy;
import cn.library.java.message.engine.client.mqtt.v5.model.dto.MqttV5ClientConfigDTO;
import cn.library.java.message.engine.client.mqtt.v5.model.property.MqttV5ClientProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.dsl.IntegrationFlow;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-06-04 17:10
 */
@Slf4j
public class MqttV5ClientConfigStrategy extends MessageClientConfigStrategy<MqttV5ClientProperty, MqttV5ClientConfigDTO, MqttV5ClientConfigDTO.BrokerDTO, MqttV5ClientConfigDTO.ClientDTO, MqttV5ClientConfigDTO.ProducerDTO, MqttV5ClientConfigDTO.ConsumerDTO> {

    @Override
    protected MessageClientVerificationConfigDTO getVerificationConfigDTO(MqttV5ClientConfigDTO configDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected Class<MqttV5ClientConfigDTO> getConfigType() {
        throw new UnsupportedException();
    }

    @Override
    protected Class<MqttV5ClientProperty> getPropertyType() {
        throw new UnsupportedException();
    }

    @Override
    protected MessageClientType getMessageEngineType() {
        throw new UnsupportedException();
    }

    @Override
    protected MqttV5ClientConfigDTO getMessageConfigDTO() {
        throw new UnsupportedException();
    }

    @Override
    protected MqttV5ClientConfigDTO.BrokerDTO getBrokerDTO() {
        throw new UnsupportedException();
    }

    @Override
    protected MqttV5ClientConfigDTO.ClientDTO getClientDTO(Annotation clientConfig, Method executeMethod) {
        throw new UnsupportedException();
    }

    @Override
    protected MqttV5ClientConfigDTO.ProducerDTO getProducerDTO(Method executeMethod, MessageClientProducer producerAnnotation) {
        throw new UnsupportedException();
    }

    @Override
    protected MqttV5ClientConfigDTO.ConsumerDTO getConsumerDTO(Method executeMethod, MessageClientConsumer consumerAnnotation, MessageClientConfigDTO.ProducerDTO forwardProducerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected IntegrationFlow getProducerFlow(MqttV5ClientConfigDTO.ProducerDTO producerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected IntegrationFlow getConsumerFlow(MqttV5ClientConfigDTO.ConsumerDTO consumerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void completeNativeConfigDTO(MqttV5ClientConfigDTO configDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerClientFactory(MqttV5ClientConfigDTO.BrokerDTO brokerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerProducerFactory(MqttV5ClientConfigDTO.ProducerDTO producerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerConsumerFactory(MqttV5ClientConfigDTO.ConsumerDTO consumerDTO) {
        throw new UnsupportedException();
    }

}