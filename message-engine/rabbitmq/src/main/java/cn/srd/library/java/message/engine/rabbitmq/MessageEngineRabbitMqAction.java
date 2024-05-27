// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.rabbitmq;

import cn.srd.library.java.message.engine.contract.MessageEngineAction;
import cn.srd.library.java.message.engine.contract.MessageSend;

/**
 * @author wjm
 * @since 2024-05-27 11:54
 */
public class MessageEngineRabbitMqAction implements MessageEngineAction {

    @Override
    public MessageEngineRabbitMqAction registerSendFlowIfNeed(String flowId, MessageSend messageSendAnnotation) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> boolean send(String flowId, T message) {
        throw new UnsupportedOperationException();
    }

}