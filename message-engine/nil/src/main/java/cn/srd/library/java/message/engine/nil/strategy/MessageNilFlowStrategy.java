// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.nil.strategy;

import cn.srd.library.java.contract.constant.text.SymbolConstant;
import cn.srd.library.java.message.engine.contract.MessageConfig;
import cn.srd.library.java.message.engine.contract.strategy.MessageFlowStrategy;

import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-06-04 10:54
 */
public class MessageNilFlowStrategy implements MessageFlowStrategy {

    @Override
    public String getFlowId(Method producerMethod, MessageConfig messageConfigAnnotation) {
        return SymbolConstant.EMPTY;
    }

    @Override
    public <T> boolean send(String flowId, T message) {
        return true;
    }

}