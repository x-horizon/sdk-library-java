package org.horizon.sdk.library.java.cache.all.aspect;

import org.horizon.sdk.library.java.cache.all.CacheWrite;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Aspect for {@link CacheWrite}
 *
 * @author wjm
 * @since 2023-06-09 15:06
 */
@Aspect
public class CacheWriteAspect extends CacheAspect {

    /**
     * the pointcut for {@link CacheWrite}
     */
    @Pointcut("@annotation(org.horizon.sdk.library.java.cache.all.CacheWrite)")
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
        CacheWrite annotation = getAnnotationMarkedOnMethod(joinPoint, CacheWrite.class);
        CacheAspectContext context = buildCacheWriteContext(joinPoint, annotation.namespaces(), annotation.cacheTypes(), annotation.cacheMode(), annotation.key(), annotation.allowEmptyValue());
        return doWrite(joinPoint, context, this::doProceed);
    }

}