package cn.srd.library.java.contract.component.message.engine;

import cn.srd.library.java.tool.spring.contract.AopCaptor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.List;

/**
 * @author wjm
 * @since 2023-05-25 17:02
 */
@Aspect
public class MessageSendAspect implements AopCaptor {

    @Pointcut("@annotation(cn.srd.library.java.contract.component.message.engine.MessageSend)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public <V> List<V> aroundPointcut(ProceedingJoinPoint joinPoint) {
        MessageSend annotation = getAnnotationMarkedOnMethod(joinPoint, MessageSend.class);
        return null;
    }

}