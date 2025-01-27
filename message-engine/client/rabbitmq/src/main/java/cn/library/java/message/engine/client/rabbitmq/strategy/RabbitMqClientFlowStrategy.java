package cn.library.java.message.engine.client.rabbitmq.strategy;

import cn.library.java.contract.model.throwable.UnsupportedException;
import cn.library.java.message.engine.client.contract.strategy.MessageClientFlowStrategy;

import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-05-27 11:54
 */
public class RabbitMqClientFlowStrategy implements MessageClientFlowStrategy {

    @Override
    public String getFlowId(Method producerMethod) {
        throw new UnsupportedException();
    }

}