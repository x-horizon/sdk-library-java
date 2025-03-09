package org.horizon.sdk.library.java.message.engine.client.contract.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.horizon.sdk.library.java.contract.model.protocol.MessageModel;
import org.horizon.sdk.library.java.message.engine.client.contract.model.enums.MessageClientType;
import org.horizon.sdk.library.java.tool.spring.contract.support.AopCaptor;
import org.springframework.messaging.support.GenericMessage;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-05-27 14:37
 */
public abstract class MessageAspect implements AopCaptor {

    public boolean sendMessage(ProceedingJoinPoint joinPoint, MessageClientType messageClientType, Serializable message) {
        Method producerMethod = getMethod(joinPoint);
        return messageClientType
                .getConfigStrategy()
                .getIntegrationFlowRegistration(
                        producerMethod,
                        getMethodParameterNames(joinPoint),
                        joinPoint.getArgs(),
                        messageClientType.getFlowStrategy().getFlowId(producerMethod)
                )
                .getInputChannel()
                .send(new GenericMessage<>(message instanceof MessageModel ? message : MessageModel.builder().data(message).build()));
    }

}