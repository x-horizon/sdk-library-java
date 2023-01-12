package cn.srd.itcp.sugar.redisson.support.lock;

import cn.srd.itcp.sugar.redisson.core.lock.RedissonNonFairLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 基于 {@link RedissonNonFairLock} 的切面
 *
 * @author wjm
 * @since 2020/12/12 18:06
 */
@Aspect
@Component
public class RedissonNonFairLockAspect extends RedissonLockAspectSupporter {

    private static RedissonNonFairLockAspect instance = null;

    @PostConstruct
    public void init() {
        instance = this;
    }

    public static RedissonNonFairLockAspect getInstance() {
        return instance;
    }

    @Pointcut("@annotation(cn.srd.itcp.sugar.redisson.core.lock.RedissonNonFairLock)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object aroundPointcut(ProceedingJoinPoint joinPoint) {
        RedissonNonFairLock lockAnnotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(RedissonNonFairLock.class);
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
