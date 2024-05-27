// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.contract;

import java.util.function.Consumer;

/**
 * @author wjm
 * @since 2024-05-27 11:53
 */
public interface MessageEngineAction {

    MessageEngineAction registerSendFlowIfNeed(String flowId, MessageSend messageSendAnnotation);

    <T> MessageEngineAction registerReceiveFlowIfNeed(String flowId, MessageReceive messageReceiveAnnotation, Consumer<T> function);

    <T> boolean send(String flowId, T message);

    <T> T receive(String flowId);

}