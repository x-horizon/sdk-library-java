// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.contract.support.strategy;

import cn.srd.library.java.message.engine.contract.MessageProducer;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/**
 * @author wjm
 * @since 2024-05-27 11:53
 */
public interface MessageEngineStrategy {

    MessageEngineStrategy registerProducerFlowIfNeed(String flowId, MessageProducer messageProducerAnnotation);

    @CanIgnoreReturnValue
    <T> boolean send(String flowId, T message);

}