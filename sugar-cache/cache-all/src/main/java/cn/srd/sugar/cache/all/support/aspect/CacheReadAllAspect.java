package cn.srd.sugar.cache.all.support.aspect;

import cn.srd.sugar.cache.all.core.CacheReadAll;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.List;

/**
 * Aspect for {@link CacheReadAll}
 *
 * @author wjm
 * @since 2023-06-18 02:20:54
 */
@Aspect
public class CacheReadAllAspect implements CacheAspect {

    /**
     * the pointcut for {@link CacheReadAll}
     */
    @Pointcut("@annotation(cn.srd.sugar.cache.all.core.CacheReadAll)")
    public void pointcut() {
    }

    /**
     * the around logic for pointcut
     *
     * @param joinPoint pointcut
     * @param <V>       the cache value type
     * @return logic return
     */
    @Around("pointcut()")
    public <V> List<V> aroundPointcut(ProceedingJoinPoint joinPoint) {
        CacheReadAll annotation = getAnnotationMarkedOnMethod(joinPoint, CacheReadAll.class);
        CacheAspectContext context = buildCacheReadAllContext(joinPoint, annotation.namespaces(), annotation.cacheTypes(), annotation.key(), annotation.allowEmptyValue());
        return doReadAll(joinPoint, context);
    }

}
