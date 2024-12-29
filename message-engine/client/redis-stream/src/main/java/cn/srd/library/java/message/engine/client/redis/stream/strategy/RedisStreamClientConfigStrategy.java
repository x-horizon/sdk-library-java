package cn.srd.library.java.message.engine.client.redis.stream.strategy;

import cn.srd.library.java.contract.model.throwable.UnsupportedException;
import cn.srd.library.java.message.engine.client.contract.MessageClientConsumer;
import cn.srd.library.java.message.engine.client.contract.MessageClientProducer;
import cn.srd.library.java.message.engine.client.contract.model.dto.MessageClientConfigDTO;
import cn.srd.library.java.message.engine.client.contract.model.dto.MessageClientVerificationConfigDTO;
import cn.srd.library.java.message.engine.client.contract.model.enums.MessageClientType;
import cn.srd.library.java.message.engine.client.contract.strategy.MessageClientConfigStrategy;
import cn.srd.library.java.message.engine.client.redis.stream.model.dto.RedisStreamClientConfigDTO;
import cn.srd.library.java.message.engine.client.redis.stream.model.property.RedisStreamClientProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.dsl.IntegrationFlow;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-06-04 17:10
 */
@Slf4j
public class RedisStreamClientConfigStrategy extends MessageClientConfigStrategy<RedisStreamClientProperty, RedisStreamClientConfigDTO, RedisStreamClientConfigDTO.BrokerDTO, RedisStreamClientConfigDTO.ClientDTO, RedisStreamClientConfigDTO.ProducerDTO, RedisStreamClientConfigDTO.ConsumerDTO> {

    @Override
    protected MessageClientVerificationConfigDTO getVerificationConfigDTO(RedisStreamClientConfigDTO configDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected Class<RedisStreamClientConfigDTO> getConfigType() {
        throw new UnsupportedException();
    }

    @Override
    protected Class<RedisStreamClientProperty> getPropertyType() {
        throw new UnsupportedException();
    }

    @Override
    protected MessageClientType getMessageEngineType() {
        throw new UnsupportedException();
    }

    @Override
    protected RedisStreamClientConfigDTO getMessageConfigDTO() {
        throw new UnsupportedException();
    }

    @Override
    protected RedisStreamClientConfigDTO.BrokerDTO getBrokerDTO() {
        throw new UnsupportedException();
    }

    @Override
    protected RedisStreamClientConfigDTO.ClientDTO getClientDTO(Annotation clientConfig, Method executeMethod) {
        throw new UnsupportedException();
    }

    @Override
    protected RedisStreamClientConfigDTO.ProducerDTO getProducerDTO(Method executeMethod, MessageClientProducer producerAnnotation) {
        throw new UnsupportedException();
    }

    @Override
    protected RedisStreamClientConfigDTO.ConsumerDTO getConsumerDTO(Method executeMethod, MessageClientConsumer consumerAnnotation, MessageClientConfigDTO.ProducerDTO forwardProducerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected IntegrationFlow getProducerFlow(RedisStreamClientConfigDTO.ProducerDTO producerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected IntegrationFlow getConsumerFlow(RedisStreamClientConfigDTO.ConsumerDTO consumerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void completeNativeConfigDTO(RedisStreamClientConfigDTO configDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerClientFactory(RedisStreamClientConfigDTO.BrokerDTO brokerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerProducerFactory(RedisStreamClientConfigDTO.ProducerDTO producerDTO) {
        throw new UnsupportedException();
    }

    @Override
    protected void registerConsumerFactory(RedisStreamClientConfigDTO.ConsumerDTO consumerDTO) {
        throw new UnsupportedException();
    }

}