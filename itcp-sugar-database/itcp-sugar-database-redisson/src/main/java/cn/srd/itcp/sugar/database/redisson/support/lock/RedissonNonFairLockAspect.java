package cn.srd.itcp.sugar.database.redisson.support.lock;

import cn.srd.itcp.sugar.database.redisson.core.lock.RedissonNonFairLock;
import jakarta.annotation.PostConstruct;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 基于 {@link RedissonNonFairLock} 的切面
 *
 * @author wjm
 * @since 2020/12/12 18:06
 */
@Aspect
@Component
public class RedissonNonFairLockAspect extends RedissonLockAspectSupporter {

    /**
     * 实例
     */
    private static RedissonNonFairLockAspect instance = null;

    /**
     * 实例初始化
     */
    @PostConstruct
    public void init() {
        instance = this;
    }

    /**
     * 获取实例
     *
     * @return 实例
     */
    public static RedissonNonFairLockAspect getInstance() {
        return instance;
    }

    /**
     * 切点声明
     */
    @Pointcut("@annotation(cn.srd.itcp.sugar.redisson.core.lock.RedissonNonFairLock)")
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
