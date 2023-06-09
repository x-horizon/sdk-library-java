package cn.srd.itcp.sugar.cache.all.support;

import cn.srd.itcp.sugar.tool.exceptions.UnsupportedOperationException;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author wjm
 * @since 2023-06-09 15:06:14
 */
@Aspect
public class CachingAspect implements CacheAspect {

    @Pointcut("@annotation(cn.srd.itcp.sugar.cache.all.core.Caching)")
    public void pointcut() {
    }

    @SneakyThrows
    @Around("pointcut()")
    public Object aroundPointcut(ProceedingJoinPoint joinPoint) {
        throw new UnsupportedOperationException();
        // Caching annotation = getAnnotationMarkedOnMethod(joinPoint, Caching.class);
        // for (CacheRead cacheReadAnnotation : annotation.read()) {
        //     CacheAspectContext context = buildContext(joinPoint, cacheReadAnnotation.namespaces(), cacheReadAnnotation.cacheTypes(), cacheReadAnnotation.key(), cacheReadAnnotation.enablePreventCachePenetrate());
        //     doRead(joinPoint,context);
        // }
    }

}
