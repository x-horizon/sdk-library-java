// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.contract;

/**
 * @author wjm
 * @since 2024-05-27 11:53
 */
public interface MessageEngineAction {

    MessageEngineAction registerSendFlowIfNeed(String flowId, MessageSend messageSendAnnotation);

    <T> boolean send(String flowId, T message);

}