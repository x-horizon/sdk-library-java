package cn.srd.itcp.sugar.cache.all.support.aspect;

import cn.srd.itcp.sugar.cache.all.core.CacheWrite;
import cn.srd.itcp.sugar.cache.all.core.CacheWriteBatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Aspect for {@link CacheWrite}
 *
 * @author wjm
 * @since 2023-06-09 15:06:14
 */
@Aspect
public class CacheWriteBatchAspect implements CacheAspect {

    /**
     * the pointcut for {@link CacheWrite}
     */
    @Pointcut("@annotation(cn.srd.itcp.sugar.cache.all.core.CacheWriteBatch)")
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
        CacheWriteBatch annotation = getAnnotationMarkedOnMethod(joinPoint, CacheWriteBatch.class);
        CacheAspectContext context = buildCacheWriteContext(joinPoint, annotation.namespaces(), annotation.cacheTypes(), annotation.cacheMode(), annotation.key(), annotation.allowEmptyValue());
        return doWriteBatch(joinPoint, context, this::doProceed);
    }

}
