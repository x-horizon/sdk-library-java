package cn.srd.itcp.sugar.cache.all.support.aspect;

import cn.srd.itcp.sugar.cache.all.core.CacheWrite;
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
public class CacheWriteAspect implements CacheAspect {

    /**
     * the pointcut for {@link CacheWrite}
     */
    @Pointcut("@annotation(cn.srd.itcp.sugar.cache.all.core.CacheWrite)")
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
        CacheAspectContext context = buildContext(joinPoint, annotation.namespaces(), annotation.cacheTypes(), annotation.key(), annotation.keyGenerator(), annotation.allowNullValue(), null, null);
        return doWrite(joinPoint, context, this::doProceed);
    }

}
