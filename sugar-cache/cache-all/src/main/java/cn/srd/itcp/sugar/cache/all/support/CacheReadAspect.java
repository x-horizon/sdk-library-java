package cn.srd.itcp.sugar.cache.all.support;

import cn.srd.itcp.sugar.cache.all.core.CacheConfig;
import cn.srd.itcp.sugar.cache.all.core.CacheRead;
import cn.srd.itcp.sugar.component.expression.all.core.Expressions;
import cn.srd.itcp.sugar.framework.spring.tool.common.core.NullValueUtil;
import cn.srd.itcp.sugar.tool.core.CollectionsUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author wjm
 * @since 2023-06-08 10:14:52
 */
@Aspect
public class CacheReadAspect {

    @Pointcut("@annotation(cn.srd.itcp.sugar.cache.all.core.CacheRead)")
    public void pointcut() {
    }

    @SneakyThrows
    @Around("pointcut()")
    public Object aroundPointcut(ProceedingJoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        CacheRead cacheReadAnnotation = method.getAnnotation(CacheRead.class);
        CacheConfig cacheConfigAnnotation = joinPoint.getTarget().getClass().getAnnotation(CacheConfig.class);
        String[] namespaces = getNamespaces(cacheConfigAnnotation, cacheReadAnnotation.namespaces());
        boolean enablePreventCachePenetrate = getEnablePreventCachePenetrate(cacheConfigAnnotation, cacheReadAnnotation.enablePreventCachePenetrate());
        List<CacheType> cacheTypes = getCacheTypes(cacheConfigAnnotation, cacheReadAnnotation.cacheTypes());
        String key = Expressions.withSpring().parse(method.getParameters(), joinPoint.getArgs(), cacheReadAnnotation.key()).toString();
        Object value = null;
        for (String namespace : namespaces) {
            value = CacheManager.getInstance().getCache(namespace, cacheTypes, enablePreventCachePenetrate).get(key);
        }
        if (Objects.isNull(value)) {
            value = joinPoint.proceed();
            for (String namespace : namespaces) {
                CacheManager.getInstance().getCache(namespace, cacheTypes, enablePreventCachePenetrate).set(key, value);
            }
        }
        return NullValueUtil.convertNullValueToNullIfNeed(value);
    }

    public String[] getNamespaces(CacheConfig cacheConfigAnnotation, String[] highestPriorityNamespaces) {
        if (Objects.isNotEmpty(highestPriorityNamespaces)) {
            return highestPriorityNamespaces;
        }
        Objects.requireFalse(() -> "cache system: could not find cache name, please specify at least one", Objects.isNull(cacheConfigAnnotation) || Objects.isEmpty(cacheConfigAnnotation.namespaces()));
        return cacheConfigAnnotation.namespaces();
    }

    public List<CacheType> getCacheTypes(CacheConfig cacheConfigAnnotation, CacheType[] lowPriorityCacheTypes) {
        if (Objects.isNotNull(cacheConfigAnnotation)) {
            return CollectionsUtil.toList(cacheConfigAnnotation.cacheTypes());
        }
        return CollectionsUtil.toList(lowPriorityCacheTypes);
    }

    public boolean getEnablePreventCachePenetrate(CacheConfig cacheConfigAnnotation, boolean highestPriorityEnablePreventCachePenetrate) {
        if (highestPriorityEnablePreventCachePenetrate) {
            return true;
        }
        if (Objects.isNotNull(cacheConfigAnnotation)) {
            return cacheConfigAnnotation.enablePreventCachePenetrate();
        }
        return false;
    }

}
