package org.horizon.library.java.cache.all.aspect;

import org.horizon.library.java.cache.all.CacheEvict;
import org.horizon.library.java.cache.all.CacheWrite;
import org.horizon.library.java.cache.all.Caching;
import org.horizon.library.java.tool.lang.convert.Converts;
import org.horizon.library.java.tool.spring.contract.support.NullValues;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.List;

/**
 * Aspect for {@link Caching}
 *
 * @author wjm
 * @since 2023-06-09 15:06
 */
@Aspect
public class CachingAspect extends CacheAspect {

    /**
     * the pointcut for {@link Caching}
     */
    @Pointcut("@annotation(org.horizon.library.java.cache.all.Caching)")
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
        Caching cachingAnnotation = getAnnotationMarkedOnMethod(joinPoint, Caching.class);
        // handle read
        List<CacheAspectContext> readContexts = Converts.toList(cachingAnnotation.read(), annotation -> buildCacheReadContext(joinPoint, annotation.namespaces(), annotation.cacheTypes(), annotation.keyGenerator(), annotation.key(), annotation.allowEmptyValue()));
        Object value = doRead(joinPoint, readContexts);
        // handle write
        for (CacheWrite annotation : cachingAnnotation.write()) {
            CacheAspectContext context = buildCacheWriteContext(joinPoint, annotation.namespaces(), annotation.cacheTypes(), annotation.cacheMode(), annotation.key(), annotation.allowEmptyValue());
            doWrite(joinPoint, context, useless -> value);
        }
        // handle evict
        for (CacheEvict annotation : cachingAnnotation.evict()) {
            CacheAspectContext context = buildCacheEvictContext(joinPoint, annotation.namespaces(), annotation.cacheTypes(), annotation.keyGenerator(), annotation.key(), annotation.allowEmptyValue(), annotation.needEvictBeforeProceed(), annotation.needEvictAllInNamespaces());
            doEvict(joinPoint, context, useless -> value);
        }
        return NullValues.convertToNullIfNullValue(value);
    }

}