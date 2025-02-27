package org.horizon.sdk.library.java.message.engine.client.mqtt.v3.strategy;

import org.horizon.sdk.library.java.message.engine.client.contract.strategy.MessageClientFlowStrategy;
import org.horizon.sdk.library.java.message.engine.client.mqtt.v3.model.dto.MqttV3ClientConfigDTO;
import org.horizon.sdk.library.java.tool.spring.contract.support.Springs;

import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-05-27 11:54
 */
public class MqttV3ClientFlowStrategy implements MessageClientFlowStrategy {

    @Override
    public String getFlowId(Method producerMethod) {
        return Springs.getBean(MqttV3ClientConfigDTO.class).getProducerRouter().get(producerMethod).getClientDTO().getFlowId();
    }

}