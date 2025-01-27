package cn.library.java.concurrent.redis.aspect;

import cn.library.java.concurrent.redis.RedisFairLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 基于 {@link RedisFairLock} 的切面
 *
 * @author wjm
 * @since 2020-12-12 18:06
 */
@Aspect
public class RedisFairLockAspect extends RedisLockAspect {

    /**
     * 切点声明
     */
    @Pointcut("@annotation(cn.library.java.concurrent.redis.RedisFairLock)")
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
        RedisFairLock lockAnnotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(RedisFairLock.class);
        return lock(
                lockAnnotation.lockName(),
                lockAnnotation.fieldName(),
                lockAnnotation.fieldOrder(),
                lockAnnotation.waitTime(),
                lockAnnotation.leaseTime(),
                lockAnnotation.timeUnit(),
                lockAnnotation.redisLockTemplate(),
                joinPoint
        );
    }

}