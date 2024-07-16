// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.contract.model.enums;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.message.engine.contract.autoconfigure.MessageEngineRegistrar;
import cn.srd.library.java.message.engine.contract.model.dto.MessageConfigDTO;
import cn.srd.library.java.message.engine.contract.model.property.MessageEngineProperty;
import cn.srd.library.java.message.engine.contract.strategy.MessageConfigStrategy;
import cn.srd.library.java.message.engine.contract.strategy.MessageFlowStrategy;
import cn.srd.library.java.message.engine.kafka.autoconfigure.MessageEngineKafkaRegistrar;
import cn.srd.library.java.message.engine.mqtt.v3.autoconfigure.MessageEngineMqttV3Registrar;
import cn.srd.library.java.message.engine.mqtt.v5.autoconfigure.MessageEngineMqttV5Registrar;
import cn.srd.library.java.message.engine.nil.autoconfigure.MessageEngineNilRegistrar;
import cn.srd.library.java.message.engine.rabbitmq.autoconfigure.MessageEngineRabbitMqRegistrar;
import cn.srd.library.java.message.engine.redis.stream.autoconfigure.MessageEngineRedisStreamRegistrar;
import cn.srd.library.java.message.engine.rocketmq.autoconfigure.MessageEngineRocketMqRegistrar;
import cn.srd.library.java.tool.enums.EnumAutowired;
import cn.srd.library.java.tool.enums.strategy.EnumAutowiredFieldMatchByContainIgnoreCaseRule;
import cn.srd.library.java.tool.lang.functional.Assert;
import lombok.Getter;

/**
 * @author wjm
 * @since 2024-05-26 15:04
 */
@Getter
@EnumAutowired(rootClasses = {MessageConfigStrategy.class, MessageFlowStrategy.class}, allowNull = true, matchRule = EnumAutowiredFieldMatchByContainIgnoreCaseRule.class)
public enum MessageEngineType {

    KAFKA(1, "kafka", MessageEngineKafkaRegistrar.class),
    MQTT_V3(2, "mqttV3", MessageEngineMqttV3Registrar.class),
    MQTT_V5(3, "mqttV5", MessageEngineMqttV5Registrar.class),
    NIL(4, "nil", MessageEngineNilRegistrar.class),
    RABBITMQ(5, "rabbitmq", MessageEngineRabbitMqRegistrar.class),
    REDIS(6, "redis", MessageEngineRedisStreamRegistrar.class),
    ROCKETMQ(7, "rocketmq", MessageEngineRocketMqRegistrar.class),

    ;

    MessageEngineType(int code, String description, Class<? extends MessageEngineRegistrar> systemSwitcher) {
        this.code = code;
        this.description = description;
        this.systemSwitcher = systemSwitcher;
    }

    private final int code;

    private final String description;

    private final Class<? extends MessageEngineRegistrar> systemSwitcher;

    private MessageConfigStrategy<MessageEngineProperty, MessageConfigDTO, MessageConfigDTO.BrokerDTO, MessageConfigDTO.ClientDTO, MessageConfigDTO.ProducerDTO, MessageConfigDTO.ConsumerDTO> configStrategy;

    private MessageFlowStrategy flowStrategy;

    public MessageConfigStrategy<MessageEngineProperty, MessageConfigDTO, MessageConfigDTO.BrokerDTO, MessageConfigDTO.ClientDTO, MessageConfigDTO.ProducerDTO, MessageConfigDTO.ConsumerDTO> getConfigStrategy() {
        Assert.of().setMessage("{}could not find the config strategy by message engine type [{}], please add the related library path to your classpath.", ModuleView.MESSAGE_ENGINE_SYSTEM, this.name())
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfNull(this.configStrategy);
        return this.configStrategy;
    }

    public MessageFlowStrategy getFlowStrategy() {
        Assert.of().setMessage("{}could not find the flow strategy by message engine type [{}], please add the related library path to your classpath.", ModuleView.MESSAGE_ENGINE_SYSTEM, this.name())
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfNull(this.flowStrategy);
        return this.flowStrategy;
    }
    
}