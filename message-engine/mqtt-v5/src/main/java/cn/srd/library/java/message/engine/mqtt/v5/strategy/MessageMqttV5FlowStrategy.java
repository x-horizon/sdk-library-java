// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v5.strategy;

import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.strategy.MessageFlowStrategy;

/**
 * @author wjm
 * @since 2024-05-27 11:54
 */
public class MessageMqttV5FlowStrategy implements MessageFlowStrategy {

    @Override
    public MessageMqttV5FlowStrategy registerProducerFlowIfNeed(String flowId, MessageProducer producerAnnotation) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> boolean send(String flowId, T message) {
        throw new UnsupportedOperationException();
    }

}