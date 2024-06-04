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

import java.util.Arrays;

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
        Object message = doProceed(joinPoint);
        MessageProducer messageProducer = getAnnotationMarkedOnMethod(joinPoint, MessageProducer.class);
        Arrays.stream(messageProducer.configs()).forEach(messageConfigAnnotation -> {
            MessageEngineType messageEngineType = messageConfigAnnotation.engineType();
            Assert.of().setMessage("{}send message failed, the message engine type is [{}], the topic is [{}], please check!", ModuleView.MESSAGE_ENGINE_SYSTEM, messageEngineType.getDescription(), messageProducer.topic())
                    .setThrowable(LibraryJavaInternalException.class)
                    .throwsIfFalse(messageEngineType.getFlowStrategy().send(getMethod(joinPoint), messageConfigAnnotation, message));
        });
        return message;
    }

}