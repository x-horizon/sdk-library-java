package cn.srd.itcp.sugar.redisson.support;

import cn.srd.itcp.sugar.redisson.core.RedissonFairLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 基于 {@link RedissonFairLock} 的切面
 *
 * @author wjm
 * @date 2020/12/12 18:06
 */
@Aspect
@Component
public class RedissonFairLockAspect extends RedissonLockAspectSupporter {

    private static RedissonFairLockAspect instance = null;

    @PostConstruct
    public void init() {
        instance = this;
    }

    public static RedissonFairLockAspect getInstance() {
        return instance;
    }

    @Pointcut("@annotation(cn.srd.itcp.sugar.redisson.core.RedissonFairLock)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object aroundPointcut(ProceedingJoinPoint joinPoint) {
        RedissonFairLock lockAnnotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(RedissonFairLock.class);
        return lock(
                lockAnnotation.lockName(),
                lockAnnotation.fieldName(),
                lockAnnotation.fieldOrder(),
                lockAnnotation.waitTime(),
                lockAnnotation.leaseTime(),
                lockAnnotation.timeUnit(),
                lockAnnotation.redissonLockTemplate(),
                joinPoint
        );
    }

}
