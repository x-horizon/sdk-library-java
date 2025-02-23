package org.horizon.library.java.message.engine.client.nil.strategy;

import org.horizon.library.java.contract.constant.text.SymbolConstant;
import org.horizon.library.java.message.engine.client.contract.strategy.MessageClientFlowStrategy;

import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-06-04 10:54
 */
public class MessageNilClientFlowStrategy implements MessageClientFlowStrategy {

    @Override
    public String getFlowId(Method producerMethod) {
        return SymbolConstant.EMPTY;
    }

}