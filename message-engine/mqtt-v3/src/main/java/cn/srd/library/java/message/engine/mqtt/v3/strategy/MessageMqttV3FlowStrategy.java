// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v3.strategy;

import cn.srd.library.java.message.engine.contract.MessageConfig;
import cn.srd.library.java.message.engine.contract.strategy.MessageFlowStrategy;
import cn.srd.library.java.message.engine.mqtt.v3.model.dto.MessageMqttV3ConfigDTO;
import cn.srd.library.java.tool.spring.contract.Springs;

import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-05-27 11:54
 */
public class MessageMqttV3FlowStrategy implements MessageFlowStrategy {

    @Override
    public String getFlowId(Method producerMethod, MessageConfig messageConfigAnnotation) {
        return Springs.getBean(MessageMqttV3ConfigDTO.class).getProducerRouters().get(producerMethod).get(messageConfigAnnotation).getClientDTO().getFlowId();
    }

}