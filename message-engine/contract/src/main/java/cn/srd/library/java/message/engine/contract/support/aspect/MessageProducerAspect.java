package cn.srd.library.java.message.engine.contract.support.aspect;

import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.support.MessageFlows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

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
        String flowId = MessageFlows.getUniqueFlowId(getMethod(joinPoint));
        MessageProducer messageProducerAnnotation = getAnnotationMarkedOnMethod(joinPoint, MessageProducer.class);
        messageProducerAnnotation.engine()
                .getAction()
                .registerProducerFlowIfNeed(flowId, messageProducerAnnotation)
                .send(flowId, message);
        return message;
    }

}