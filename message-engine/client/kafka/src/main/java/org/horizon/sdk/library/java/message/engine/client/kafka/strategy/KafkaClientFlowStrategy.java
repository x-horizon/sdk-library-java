package org.horizon.sdk.library.java.message.engine.client.kafka.strategy;

import org.horizon.sdk.library.java.message.engine.client.contract.strategy.MessageClientFlowStrategy;
import org.horizon.sdk.library.java.message.engine.client.kafka.model.dto.KafkaClientConfigDTO;
import org.horizon.sdk.library.java.tool.spring.contract.support.Springs;

import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-05-27 11:54
 */
public class KafkaClientFlowStrategy implements MessageClientFlowStrategy {

    @Override
    public String getFlowId(Method producerMethod) {
        return Springs.getBean(KafkaClientConfigDTO.class).getProducerRouter().get(producerMethod).getClientDTO().getFlowId();
    }

}