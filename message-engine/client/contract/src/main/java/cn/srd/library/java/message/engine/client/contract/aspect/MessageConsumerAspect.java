package cn.srd.library.java.message.engine.client.contract.aspect;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.message.engine.client.contract.MessageConsumer;
import cn.srd.library.java.message.engine.client.contract.MessageProducer;
import cn.srd.library.java.message.engine.client.contract.model.enums.MessageEngineType;
import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.functional.Assert;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.io.Serializable;

/**
 * @author wjm
 * @since 2023-06-04 10:07
 */
@Aspect
public class MessageConsumerAspect extends MessageAspect {

    @Pointcut("@annotation(cn.srd.library.java.message.engine.client.contract.MessageConsumer)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object aroundPointcut(ProceedingJoinPoint joinPoint) {
        Serializable message = (Serializable) doProceed(joinPoint);
        MessageConsumer consumerAnnotation = getAnnotationMarkedOnMethod(joinPoint, MessageConsumer.class);
        MessageProducer forwardMessageProducer = consumerAnnotation.forwardTo();
        MessageEngineType forwardMessageEngineType = forwardMessageProducer.config().engineType();
        if (Comparators.equals(MessageEngineType.NIL, forwardMessageEngineType)) {
            return message;
        }
        Assert.of().setMessage("{}forward message failed, the forward message engine type is [{}], the forward topic is [{}], please check!", ModuleView.MESSAGE_ENGINE_SYSTEM, forwardMessageEngineType.getDescription(), forwardMessageProducer.topic())
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfFalse(sendMessage(joinPoint, forwardMessageEngineType, message));
        return message;
    }

}