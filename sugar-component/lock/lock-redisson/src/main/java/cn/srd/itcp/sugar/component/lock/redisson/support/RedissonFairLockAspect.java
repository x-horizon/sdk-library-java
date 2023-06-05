package cn.srd.itcp.sugar.component.lock.redisson.support;

import cn.srd.itcp.sugar.component.lock.redisson.core.RedissonFairLock;
import jakarta.annotation.PostConstruct;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 基于 {@link RedissonFairLock} 的切面
 *
 * @author wjm
 * @since 2020/12/12 18:06
 */
@Aspect
public class RedissonFairLockAspect extends RedissonLockAspectSupporter {

    /**
     * instance
     */
    private static RedissonFairLockAspect instance = null;

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
    public static RedissonFairLockAspect getInstance() {
        return instance;
    }

    /**
     * 切点声明
     */
    @Pointcut("@annotation(cn.srd.itcp.sugar.component.lock.redisson.core.RedissonFairLock)")
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
