package cn.srd.library.java.message.engine.contract;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author wjm
 * @since 2023-05-25 17:02
 */
@Aspect
public class MessageSendAspect extends MessageAspect {

    @Pointcut("@annotation(cn.srd.library.java.message.engine.contract.MessageSend)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object aroundPointcut(ProceedingJoinPoint joinPoint) {
        Object message = doProceed(joinPoint);
        String flowId = MessageFlows.getId(getMethod(joinPoint));
        MessageSend messageSendAnnotation = getAnnotationMarkedOnMethod(joinPoint, MessageSend.class);
        messageSendAnnotation.type()
                .getAction()
                .registerSendFlowIfNeed(flowId, messageSendAnnotation)
                .send(flowId, message);
        return message;
    }

}