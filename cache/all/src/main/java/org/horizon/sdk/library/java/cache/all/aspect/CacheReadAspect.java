package org.horizon.sdk.library.java.cache.all.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.horizon.sdk.library.java.cache.all.CacheRead;

/**
 * Aspect for {@link CacheRead}
 *
 * @author wjm
 * @since 2023-06-08 10:14
 */
@Aspect
public class CacheReadAspect extends CacheAspect {

    /**
     * the pointcut for {@link CacheRead}
     */
    @Pointcut("@annotation(org.horizon.sdk.library.java.cache.all.CacheRead)")
    public void pointcut() {
    }

    /**
     * the around logic for pointcut
     *
     * @param joinPoint pointcut
     * @return logic return
     */
    @Around("pointcut()")
    public Object aroundPointcut(ProceedingJoinPoint joinPoint) {
        CacheRead annotation = getAnnotationMarkedOnMethod(joinPoint, CacheRead.class);
        CacheAspectContext context = buildCacheReadContext(joinPoint, annotation.namespaces(), annotation.cacheTypes(), annotation.keyGenerator(), annotation.key(), annotation.allowEmptyValue());
        return doRead(joinPoint, context);
    }

}