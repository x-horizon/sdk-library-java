// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.contract.model.enums;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.message.engine.contract.autoconfigure.MessageEngineSwitcher;
import cn.srd.library.java.message.engine.contract.model.dto.MessageConfigDTO;
import cn.srd.library.java.message.engine.contract.strategy.MessageConfigStrategy;
import cn.srd.library.java.message.engine.contract.strategy.MessageFlowStrategy;
import cn.srd.library.java.message.engine.kafka.autoconfigure.MessageEngineKafkaSwitcher;
import cn.srd.library.java.message.engine.mqtt.v3.autoconfigure.MessageEngineMqttV3Switcher;
import cn.srd.library.java.message.engine.mqtt.v5.autoconfigure.MessageEngineMqttV5Switcher;
import cn.srd.library.java.message.engine.nil.autoconfigure.MessageEngineNilSwitcher;
import cn.srd.library.java.message.engine.rabbitmq.autoconfigure.MessageEngineRabbitMqSwitcher;
import cn.srd.library.java.message.engine.redis.autoconfigure.MessageEngineRedisSwitcher;
import cn.srd.library.java.message.engine.rocketmq.autoconfigure.MessageEngineRocketMqSwitcher;
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

    KAFKA(1, "kafka", MessageEngineKafkaSwitcher.class),
    MQTT_V3(2, "mqttV3", MessageEngineMqttV3Switcher.class),
    MQTT_V5(3, "mattV5", MessageEngineMqttV5Switcher.class),
    NIL(4, "nil", MessageEngineNilSwitcher.class),
    RABBITMQ(5, "rabbitmq", MessageEngineRabbitMqSwitcher.class),
    REDIS(6, "redis", MessageEngineRedisSwitcher.class),
    ROCKETMQ(7, "rocketmq", MessageEngineRocketMqSwitcher.class),

    ;

    MessageEngineType(int code, String description, Class<? extends MessageEngineSwitcher> systemSwitcher) {
        this.code = code;
        this.description = description;
        this.systemSwitcher = systemSwitcher;
    }

    private final int code;

    private final String description;

    private final Class<? extends MessageEngineSwitcher> systemSwitcher;

    private MessageConfigStrategy<MessageConfigDTO> configStrategy;

    private MessageFlowStrategy flowStrategy;

    public MessageConfigStrategy<MessageConfigDTO> getConfigStrategy() {
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