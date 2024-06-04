package cn.srd.library.java.message.engine.contract.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author wjm
 * @since 2023-06-04 10:07
 */
@Aspect
public class MessageConsumerAspect extends MessageAspect {

    @Pointcut("@annotation(cn.srd.library.java.message.engine.contract.MessageConsumer)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object aroundPointcut(ProceedingJoinPoint joinPoint) {
        Object message = doProceed(joinPoint);
        // MessageConsumer consumerAnnotation = getAnnotationMarkedOnMethod(joinPoint, MessageConsumer.class);
        // MessageProducer forwardMessageProducer = consumerAnnotation.forwardTo();
        // MessageEngineType forwardMessageEngineType = forwardMessageProducer.configs().engineType();
        // if (Comparators.equals(MessageEngineType.NIL, forwardMessageEngineType)) {
        //     return message;
        // }
        // Assert.of().setMessage("{}forward message failed, the forward message engine type is [{}], the forward topic is [{}], please check!", ModuleView.MESSAGE_ENGINE_SYSTEM, forwardMessageEngineType.getDescription(), forwardMessageProducer.topic())
        //         .setThrowable(LibraryJavaInternalException.class)
        //         .throwsIfFalse(forwardMessageEngineType.getFlowStrategy().send(getMethod(joinPoint), message));
        return message;
    }

}