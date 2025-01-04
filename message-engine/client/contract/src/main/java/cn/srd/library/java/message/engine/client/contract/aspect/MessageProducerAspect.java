package cn.srd.library.java.message.engine.client.contract.aspect;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.message.engine.client.contract.MessageClientProducer;
import cn.srd.library.java.message.engine.client.contract.model.enums.MessageClientType;
import cn.srd.library.java.tool.lang.functional.Assert;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.io.Serializable;

/**
 * @author wjm
 * @since 2023-05-25 17:02
 */
@Aspect
public class MessageProducerAspect extends MessageAspect {

    @Pointcut("@annotation(cn.srd.library.java.message.engine.client.contract.MessageClientProducer)")
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