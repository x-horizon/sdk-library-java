package cn.library.java.cache.all.aspect;

import cn.library.java.cache.all.CacheEvict;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Aspect for {@link CacheEvict}
 *
 * @author wjm
 * @since 2023-06-09 15:06
 */
@Aspect
public class CacheEvictAspect extends CacheAspect {

    /**
     * the pointcut for {@link CacheEvict}
     */
    @Pointcut("@annotation(cn.library.java.cache.all.CacheEvict)")
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
        CacheEvict annotation = getAnnotationMarkedOnMethod(joinPoint, CacheEvict.class);
        CacheAspectContext context = buildCacheEvictContext(joinPoint, annotation.namespaces(), annotation.cacheTypes(), annotation.keyGenerator(), annotation.key(), annotation.allowEmptyValue(), annotation.needEvictBeforeProceed(), annotation.needEvictAllInNamespaces());
        return doEvict(joinPoint, context, this::doProceed);
    }

}