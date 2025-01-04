package cn.srd.library.java.message.engine.client.contract.aspect;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.message.engine.client.contract.MessageClientConsumer;
import cn.srd.library.java.message.engine.client.contract.MessageClientProducer;
import cn.srd.library.java.message.engine.client.contract.model.enums.MessageClientType;
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

    @Pointcut("@annotation(cn.srd.library.java.message.engine.client.contract.MessageClientConsumer)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object aroundPointcut(ProceedingJoinPoint joinPoint) {
        Serializable message = (Serializable) doProceed(joinPoint);
        MessageClientConsumer consumerAnnotation = getAnnotationMarkedOnMethod(joinPoint, MessageClientConsumer.class);
        MessageClientProducer forwardMessageClientProducer = consumerAnnotation.forwardTo();
        MessageClientType forwardMessageClientType = forwardMessageClientProducer.config().engineType();
        if (Comparators.equals(MessageClientType.NIL, forwardMessageClientType)) {
            return message;
        }
        Assert.of().setMessage("{}forward message failed, the forward message engine type is [{}], the forward topic is [{}], please check!", ModuleView.MESSAGE_ENGINE_CLIENT_SYSTEM, forwardMessageClientType.getDescription(), forwardMessageClientProducer.topic())
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfFalse(sendMessage(joinPoint, forwardMessageClientType, message));
        return message;
    }

}