package cn.srd.itcp.sugar.component.lock.redisson.support;

import cn.srd.itcp.sugar.component.lock.redisson.core.RedissonNonFairLock;
import jakarta.annotation.PostConstruct;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 基于 {@link RedissonNonFairLock} 的切面
 *
 * @author wjm
 * @since 2020/12/12 18:06
 */
@Aspect
public class RedissonNonFairLockAspect extends RedissonLockAspectSupporter {

    /**
     * instance
     */
    private static RedissonNonFairLockAspect instance = null;

    /**
     * instance init
     */
    @PostConstruct
    public void init() {
        instance = this;
    }

    /**
     * get singleton instance
     *
     * @return instance
     */
    public static RedissonNonFairLockAspect getInstance() {
        return instance;
    }

    /**
     * 切点声明
     */
    @Pointcut("@annotation(cn.srd.itcp.sugar.component.lock.redisson.core.RedissonNonFairLock)")
    public void pointcut() {
    }

    /**
     * 切点环绕操作
     *
     * @param joinPoint 切点
     * @return 切面响应值
     */
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
