package org.horizon.sdk.library.java.message.engine.client.contract.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.horizon.sdk.library.java.contract.constant.module.ModuleView;
import org.horizon.sdk.library.java.contract.model.throwable.LibraryJavaInternalException;
import org.horizon.sdk.library.java.message.engine.client.contract.MessageClientProducer;
import org.horizon.sdk.library.java.message.engine.client.contract.model.enums.MessageClientType;
import org.horizon.sdk.library.java.tool.lang.functional.Assert;

import java.io.Serializable;

/**
 * @author wjm
 * @since 2023-05-25 17:02
 */
@Aspect
public class MessageProducerAspect extends MessageAspect {

    @Pointcut("@annotation(org.horizon.sdk.library.java.message.engine.client.contract.MessageClientProducer)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object aroundPointcut(ProceedingJoinPoint joinPoint) {
        Serializable message = (Serializable) doProceed(joinPoint);
        MessageClientProducer messageClientProducer = getAnnotationMarkedOnMethod(joinPoint, MessageClientProducer.class);
        MessageClientType messageClientType = messageClientProducer.config().engineType();
        Assert.of().setMessage("{}send message failed, the message engine type is [{}], the topic is [{}], please check!", ModuleView.MESSAGE_ENGINE_CLIENT_SYSTEM, messageClientType.getDescription(), messageClientProducer.topic())
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfFalse(sendMessage(joinPoint, messageClientType, message));
        return message;
    }

}