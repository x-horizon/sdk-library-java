package org.horizon.sdk.library.java.message.engine.client.contract.model.enums;

import lombok.Getter;
import org.horizon.sdk.library.java.contract.constant.module.ModuleView;
import org.horizon.sdk.library.java.contract.model.throwable.LibraryJavaInternalException;
import org.horizon.sdk.library.java.message.engine.client.contract.autoconfigure.MessageClientRegistrar;
import org.horizon.sdk.library.java.message.engine.client.contract.model.dto.MessageClientConfigDTO;
import org.horizon.sdk.library.java.message.engine.client.contract.model.property.MessageClientProperty;
import org.horizon.sdk.library.java.message.engine.client.contract.strategy.MessageClientConfigStrategy;
import org.horizon.sdk.library.java.message.engine.client.contract.strategy.MessageClientFlowStrategy;
import org.horizon.sdk.library.java.message.engine.client.kafka.autoconfigure.MessageKafkaClientRegistrar;
import org.horizon.sdk.library.java.message.engine.client.mqtt.v3.autoconfigure.MessageMqttV3ClientRegistrar;
import org.horizon.sdk.library.java.message.engine.client.mqtt.v5.autoconfigure.MessageMqttV5ClientRegistrar;
import org.horizon.sdk.library.java.message.engine.client.nil.autoconfigure.MessageNilClientRegistrar;
import org.horizon.sdk.library.java.message.engine.client.rabbitmq.autoconfigure.MessageRabbitMqClientRegistrar;
import org.horizon.sdk.library.java.message.engine.client.redis.stream.autoconfigure.MessageRedisStreamClientRegistrar;
import org.horizon.sdk.library.java.message.engine.client.rocketmq.autoconfigure.MessageRocketMqClientRegistrar;
import org.horizon.sdk.library.java.message.engine.contract.model.enums.MessageEngineType;
import org.horizon.sdk.library.java.tool.enums.EnumAutowired;
import org.horizon.sdk.library.java.tool.enums.strategy.EnumAutowiredFieldMatchByContainIgnoreCaseRule;
import org.horizon.sdk.library.java.tool.lang.functional.Assert;

/**
 * @author wjm
 * @since 2024-05-26 15:04
 */
@Getter
@EnumAutowired(rootClasses = {MessageClientConfigStrategy.class, MessageClientFlowStrategy.class}, allowNull = true, matchRule = EnumAutowiredFieldMatchByContainIgnoreCaseRule.class)
public enum MessageClientType {

    KAFKA(MessageEngineType.KAFKA.getCode(), MessageEngineType.KAFKA.getDescription(), MessageKafkaClientRegistrar.class),
    MQTT_V3(MessageEngineType.MQTT_V3.getCode(), MessageEngineType.MQTT_V3.getDescription(), MessageMqttV3ClientRegistrar.class),
    MQTT_V5(MessageEngineType.MQTT_V5.getCode(), MessageEngineType.MQTT_V5.getDescription(), MessageMqttV5ClientRegistrar.class),
    NIL(MessageEngineType.NIL.getCode(), MessageEngineType.NIL.getDescription(), MessageNilClientRegistrar.class),
    RABBITMQ(MessageEngineType.RABBITMQ.getCode(), MessageEngineType.RABBITMQ.getDescription(), MessageRabbitMqClientRegistrar.class),
    REDIS(MessageEngineType.REDIS.getCode(), MessageEngineType.REDIS.getDescription(), MessageRedisStreamClientRegistrar.class),
    ROCKETMQ(MessageEngineType.ROCKETMQ.getCode(), MessageEngineType.ROCKETMQ.getDescription(), MessageRocketMqClientRegistrar.class),

    ;

    MessageClientType(int code, String description, Class<? extends MessageClientRegistrar> systemSwitcher) {
        this.code = code;
        this.description = description;
        this.systemSwitcher = systemSwitcher;
    }

    private final int code;

    private final String description;

    private final Class<? extends MessageClientRegistrar> systemSwitcher;

    private MessageClientConfigStrategy<MessageClientProperty, MessageClientConfigDTO, MessageClientConfigDTO.BrokerDTO, MessageClientConfigDTO.ClientDTO, MessageClientConfigDTO.ProducerDTO, MessageClientConfigDTO.ConsumerDTO> configStrategy;

    private MessageClientFlowStrategy flowStrategy;

    public MessageClientConfigStrategy<MessageClientProperty, MessageClientConfigDTO, MessageClientConfigDTO.BrokerDTO, MessageClientConfigDTO.ClientDTO, MessageClientConfigDTO.ProducerDTO, MessageClientConfigDTO.ConsumerDTO> getConfigStrategy() {
        Assert.of().setMessage("{}could not find the config strategy by message engine type [{}], please add the related library path to your classpath.", ModuleView.MESSAGE_ENGINE_CLIENT_SYSTEM, this.name())
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfNull(this.configStrategy);
        return this.configStrategy;
    }

    public MessageClientFlowStrategy getFlowStrategy() {
        Assert.of().setMessage("{}could not find the flow strategy by message engine type [{}], please add the related library path to your classpath.", ModuleView.MESSAGE_ENGINE_CLIENT_SYSTEM, this.name())
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfNull(this.flowStrategy);
        return this.flowStrategy;
    }

}