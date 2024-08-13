package cn.srd.library.java.message.engine.contract.aspect;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;
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

    @Pointcut("@annotation(cn.srd.library.java.message.engine.contract.MessageProducer)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object aroundPointcut(ProceedingJoinPoint joinPoint) {
        Serializable message = (Serializable) doProceed(joinPoint);
        MessageProducer messageProducer = getAnnotationMarkedOnMethod(joinPoint, MessageProducer.class);
        MessageEngineType messageEngineType = messageProducer.config().engineType();
        Assert.of().setMessage("{}send message failed, the message engine type is [{}], the topic is [{}], please check!", ModuleView.MESSAGE_ENGINE_SYSTEM, messageEngineType.getDescription(), messageProducer.topic())
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfFalse(sendMessage(joinPoint, messageEngineType, message));
        return message;
    }

}