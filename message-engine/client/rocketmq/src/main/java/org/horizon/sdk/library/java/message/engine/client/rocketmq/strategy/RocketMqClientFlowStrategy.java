package org.horizon.sdk.library.java.message.engine.client.rocketmq.strategy;

import org.horizon.sdk.library.java.contract.model.throwable.UnsupportedException;
import org.horizon.sdk.library.java.message.engine.client.contract.strategy.MessageClientFlowStrategy;

import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-05-27 11:54
 */
public class RocketMqClientFlowStrategy implements MessageClientFlowStrategy {

    @Override
    public String getFlowId(Method producerMethod) {
        throw new UnsupportedException();
    }

}