package cn.srd.library.java.cache.all.support.aspect;

import cn.srd.library.java.cache.all.core.CacheEvict;
import cn.srd.library.java.cache.all.core.CacheWrite;
import cn.srd.library.java.cache.all.core.Caching;
import cn.srd.library.java.tool.lang.core.CollectionsUtil;
import cn.srd.library.java.tool.spring.common.core.NullValueUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.List;

/**
 * Aspect for {@link Caching}
 *
 * @author wjm
 * @since 2023-06-09 15:06:14
 */
@Aspect
public class CachingAspect implements CacheAspect {

    /**
     * the pointcut for {@link Caching}
     */
    @Pointcut("@annotation(cn.srd.library.java.cache.all.core.Caching)")
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
        List<CacheAspectContext> readContexts = CollectionsUtil.toList(cachingAnnotation.read(), annotation -> buildCacheReadContext(joinPoint, annotation.namespaces(), annotation.cacheTypes(), annotation.keyGenerator(), annotation.key(), annotation.allowEmptyValue()));
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
        return NullValueUtil.convertToNullIfNullValue(value);
    }

}
