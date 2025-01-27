package cn.library.java.message.engine.client.contract.aspect;

import cn.library.java.contract.constant.module.ModuleView;
import cn.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.library.java.message.engine.client.contract.MessageClientConsumer;
import cn.library.java.message.engine.client.contract.MessageClientProducer;
import cn.library.java.message.engine.client.contract.model.enums.MessageClientType;
import cn.library.java.tool.lang.compare.Comparators;
import cn.library.java.tool.lang.functional.Assert;
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

    @Pointcut("@annotation(cn.library.java.message.engine.client.contract.MessageClientConsumer)")
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