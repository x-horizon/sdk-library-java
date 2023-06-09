package cn.srd.itcp.sugar.cache.all.support;

import cn.srd.itcp.sugar.cache.all.core.CacheEvict;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author wjm
 * @since 2023-06-09 15:06:14
 */
@Aspect
public class CacheEvictAspect implements CacheAspect {

    @Pointcut("@annotation(cn.srd.itcp.sugar.cache.all.core.CacheWrite)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object aroundPointcut(ProceedingJoinPoint joinPoint) {
        CacheEvict annotation = getAnnotationMarkedOnMethod(joinPoint, CacheEvict.class);
        CacheAspectContext context = buildContext(joinPoint, annotation.namespaces(), annotation.cacheTypes(), annotation.key(), annotation.enablePreventCachePenetrate());
        return doEvict(joinPoint, context);
    }

}
