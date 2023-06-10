package cn.srd.itcp.sugar.cache.all.support;

import cn.srd.itcp.sugar.cache.all.core.CacheWrite;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author wjm
 * @since 2023-06-09 15:06:14
 */
@Aspect
public class CacheWriteAspect implements CacheAspect {

    @Pointcut("@annotation(cn.srd.itcp.sugar.cache.all.core.CacheWrite)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object aroundPointcut(ProceedingJoinPoint joinPoint) {
        CacheWrite annotation = getAnnotationMarkedOnMethod(joinPoint, CacheWrite.class);
        CacheAspectContext context = buildContext(joinPoint, annotation.namespaces(), annotation.cacheTypes(), annotation.key(), annotation.keyGenerator(), annotation.enablePreventCachePenetrate(), null, null);
        return doWrite(joinPoint, context, this::doProceed);
    }

}
