package cn.srd.itcp.sugar.cache.all.support;

import cn.srd.itcp.sugar.cache.all.core.CacheConfig;
import cn.srd.itcp.sugar.component.expression.all.core.Expressions;
import cn.srd.itcp.sugar.framework.spring.tool.common.core.AopCaptor;
import cn.srd.itcp.sugar.framework.spring.tool.common.core.NullValueUtil;
import cn.srd.itcp.sugar.tool.core.ArraysUtil;
import cn.srd.itcp.sugar.tool.core.CollectionsUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.cache.support.NullValue;

import java.util.List;
import java.util.function.Function;

/**
 * @author wjm
 * @since 2023-06-09 15:06:14
 */
public interface CacheAspect extends AopCaptor {

    default CacheConfig getCacheConfigAnnotation(ProceedingJoinPoint joinPoint) {
        return getAnnotationMarkedOnClass(joinPoint, CacheConfig.class);
    }

    default String[] getNamespaces(CacheConfig cacheConfigAnnotation, String[] highestPriorityNamespaces) {
        if (Objects.isNotEmpty(highestPriorityNamespaces)) {
            return highestPriorityNamespaces;
        }
        Objects.requireFalse(() -> "cache system: could not find cache name, please specify at least one", Objects.isNull(cacheConfigAnnotation) || Objects.isEmpty(cacheConfigAnnotation.namespaces()));
        return cacheConfigAnnotation.namespaces();
    }

    default List<CacheType> getCacheTypes(CacheConfig cacheConfigAnnotation, CacheType[] highestPriorityCacheTypes) {
        if (ArraysUtil.isNotEmpty(highestPriorityCacheTypes)) {
            return CollectionsUtil.toList(highestPriorityCacheTypes);
        }
        Objects.requireFalse(() -> "cache system: could not find cache type to cache, please specify at least one", Objects.isNull(cacheConfigAnnotation) || ArraysUtil.isEmpty(cacheConfigAnnotation.cacheTypes()));
        return CollectionsUtil.toList(cacheConfigAnnotation.cacheTypes());
    }

    default boolean getEnablePreventCachePenetrate(CacheConfig cacheConfigAnnotation, boolean highestPriorityEnablePreventCachePenetrate) {
        if (highestPriorityEnablePreventCachePenetrate) {
            return true;
        }
        if (Objects.isNotNull(cacheConfigAnnotation)) {
            return cacheConfigAnnotation.enablePreventCachePenetrate();
        }
        return false;
    }

    default CacheAspectContext buildContext(ProceedingJoinPoint joinPoint, String[] originalNamespaces, CacheType[] originalCacheTypes, String originalKey, boolean originalEnablePreventCachePenetrate) {
        CacheConfig cacheConfigAnnotation = getCacheConfigAnnotation(joinPoint);
        return CacheAspectContext.builder()
                .cacheConfigAnnotation(getCacheConfigAnnotation(joinPoint))
                .originalNamespaces(originalNamespaces)
                .namespaces(getNamespaces(cacheConfigAnnotation, originalNamespaces))
                .originalCacheTypes(CollectionsUtil.toList(originalCacheTypes))
                .cacheTypes(getCacheTypes(cacheConfigAnnotation, originalCacheTypes))
                .originalKey(originalKey)
                .key(Expressions.withSpring().parse(getMethodParameters(joinPoint), joinPoint.getArgs(), originalKey).toString())
                .originalEnablePreventCachePenetrate(originalEnablePreventCachePenetrate)
                .enablePreventCachePenetrate(getEnablePreventCachePenetrate(cacheConfigAnnotation, originalEnablePreventCachePenetrate))
                .build();
    }

    default Object getCacheValue(CacheAspectContext context) {
        Object value = null;
        for (String namespace : context.getNamespaces()) {
            value = getCache(context, namespace).get(context.getKey());
        }
        return value;
    }

    default void setCacheValue(CacheAspectContext context) {
        for (String namespace : context.getNamespaces()) {
            getCache(context, namespace).set(context.getKey(), context.getValue());
        }
    }

    default void deleteCacheValue(CacheAspectContext context) {
        for (String namespace : context.getNamespaces()) {
            getCache(context, namespace).delete(context.getKey());
        }
    }

    default void initCache(CacheAspectContext context) {
        for (String namespace : context.getNamespaces()) {
            getCache(context, namespace);
        }
    }

    default Cache getCache(CacheAspectContext context, String namespace) {
        return CacheManager.getInstance().getCache(namespace, context.getCacheTypes(), context.getEnablePreventCachePenetrate());
    }

    default Object doRead(ProceedingJoinPoint joinPoint, CacheAspectContext context) {
        Object value = getCacheValue(context);
        if (Objects.isNull(value)) {
            value = doProceed(joinPoint);
        }
        if (Objects.isNotNull(value)) {
            setCacheValue(context.setValue(value));
        }
        return NullValueUtil.convertToNullIfNullValue(value);
    }

    default Object doRead(ProceedingJoinPoint joinPoint, List<CacheAspectContext> contexts) {
        Object value = null;
        for (CacheAspectContext context : contexts) {
            value = getCacheValue(context);
        }
        if (Objects.isNull(value)) {
            value = doProceed(joinPoint);
        }
        if (Objects.isNotNull(value)) {
            for (CacheAspectContext context : contexts) {
                setCacheValue(context.setValue(value));
            }
        }
        return NullValueUtil.convertToNullIfNullValue(value);
    }

    default Object doWrite(ProceedingJoinPoint joinPoint, CacheAspectContext context, Function<ProceedingJoinPoint, Object> proceedPointCutLogic) {
        initCache(context);
        Object value = proceedPointCutLogic.apply(joinPoint);
        if (Objects.isNull(value)) {
            if (context.getEnablePreventCachePenetrate()) {
                setCacheValue(context.setValue(NullValue.INSTANCE));
            } else {
                deleteCacheValue(context);
            }
        } else {
            setCacheValue(context.setValue(value));
        }
        return NullValueUtil.convertToNullIfNullValue(value);
    }

    default Object doEvict(ProceedingJoinPoint joinPoint, CacheAspectContext context, Function<ProceedingJoinPoint, Object> proceedPointCutLogic) {
        initCache(context);
        Object value = proceedPointCutLogic.apply(joinPoint);
        if (context.getEnablePreventCachePenetrate()) {
            setCacheValue(context.setValue(NullValue.INSTANCE));
        } else {
            deleteCacheValue(context);
        }
        return NullValueUtil.convertToNullIfNullValue(value);
    }

}
