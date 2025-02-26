package org.horizon.library.java.message.engine.client.mqtt.v5.strategy;

import org.horizon.library.java.contract.model.throwable.UnsupportedException;
import org.horizon.library.java.message.engine.client.contract.strategy.MessageClientFlowStrategy;

import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-05-27 11:54
 */
public class MqttV5ClientFlowStrategy implements MessageClientFlowStrategy {

    @Override
    public String getFlowId(Method producerMethod) {
        throw new UnsupportedException();
    }

}