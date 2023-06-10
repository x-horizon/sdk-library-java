package cn.srd.itcp.sugar.cache.all.support.aspect;

import cn.srd.itcp.sugar.cache.all.core.CacheRead;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author wjm
 * @since 2023-06-08 10:14:52
 */
@Aspect
public class CacheReadAspect implements CacheAspect {

    @Pointcut("@annotation(cn.srd.itcp.sugar.cache.all.core.CacheRead)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object aroundPointcut(ProceedingJoinPoint joinPoint) {
        CacheRead annotation = getAnnotationMarkedOnMethod(joinPoint, CacheRead.class);
        CacheAspectContext context = buildContext(joinPoint, annotation.namespaces(), annotation.cacheTypes(), annotation.key(), annotation.keyGenerator(), annotation.enablePreventCachePenetrate(), null, null);
        return doRead(joinPoint, context);
    }

}
