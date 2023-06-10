package cn.srd.itcp.sugar.cache.all.support;

import cn.srd.itcp.sugar.cache.all.core.CacheConfig;
import cn.srd.itcp.sugar.component.expression.all.core.Expressions;
import cn.srd.itcp.sugar.framework.spring.tool.common.core.AopCaptor;
import cn.srd.itcp.sugar.framework.spring.tool.common.core.NullValueUtil;
import cn.srd.itcp.sugar.tool.core.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.cache.support.NullValue;

import java.lang.reflect.Parameter;
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

    default String[] parseNamespaces(CacheConfig cacheConfigAnnotation, String[] namespacesOnMethod) {
        if (Objects.isNotEmpty(namespacesOnMethod)) {
            return namespacesOnMethod;
        }
        Objects.requireFalse(() -> "cache system: could not find cache name, please specify at least one!", Objects.isNull(cacheConfigAnnotation) || Objects.isEmpty(cacheConfigAnnotation.namespaces()));
        return cacheConfigAnnotation.namespaces();
    }

    default List<CacheType> parseCacheTypes(CacheConfig cacheConfigAnnotation, CacheType[] cacheTypesOnMethod) {
        if (ArraysUtil.isNotEmpty(cacheTypesOnMethod)) {
            return CollectionsUtil.toList(cacheTypesOnMethod);
        }
        Objects.requireFalse(() -> "cache system: could not find cache type to cache, please specify at least one!", Objects.isNull(cacheConfigAnnotation) || ArraysUtil.isEmpty(cacheConfigAnnotation.cacheTypes()));
        return CollectionsUtil.toList(cacheConfigAnnotation.cacheTypes());
    }

    default String parseKey(CacheConfig cacheConfigAnnotation, Parameter[] parameters, Object[] parameterValues, String keyExpression, Class<? extends CacheKeyGenerator> keyGeneratorOnMethod) {
        String key = Objects.isBlank(keyExpression) ? ReflectsUtil.newInstance(parseKeyGenerator(cacheConfigAnnotation, keyGeneratorOnMethod)).generate(parameters, parameterValues) : Expressions.withSpring().parse(parameters, parameterValues, keyExpression).toString();
        Objects.requireNotBlank(() -> StringsUtil.format("cache system: could not generate key by keyExpression [{}] or keyGenerator [{}], please check!", keyExpression, keyGeneratorOnMethod.getSimpleName()), key);
        return key;
    }

    default Class<? extends CacheKeyGenerator> parseKeyGenerator(CacheConfig cacheConfigAnnotation, Class<? extends CacheKeyGenerator> keyGeneratorOnMethod) {
        Class<? extends CacheKeyGenerator> finalCacheKeyGenerator;
        if (Objects.isNull(cacheConfigAnnotation)) {
            finalCacheKeyGenerator = keyGeneratorOnMethod;
        } else {
            finalCacheKeyGenerator = Objects.equals(keyGeneratorOnMethod, CacheKeyGenerator.DEFAULT_KEY_GENERATOR) ? cacheConfigAnnotation.keyGenerator() : keyGeneratorOnMethod;
        }
        return finalCacheKeyGenerator;
    }

    default boolean parseEnablePreventCachePenetrate(CacheConfig cacheConfigAnnotation, boolean enablePreventCachePenetrateOnMethod) {
        if (enablePreventCachePenetrateOnMethod) {
            return true;
        }
        if (Objects.isNotNull(cacheConfigAnnotation)) {
            return cacheConfigAnnotation.enablePreventCachePenetrate();
        }
        return false;
    }

    default CacheAspectContext buildContext(ProceedingJoinPoint joinPoint, String[] originalNamespaces, CacheType[] originalCacheTypes, String originalKey, Class<? extends CacheKeyGenerator> keyGenerator, Boolean originalEnablePreventCachePenetrate, Boolean needEvictBeforeProceed, Boolean needEvictAllInNamespaces) {
        CacheConfig cacheConfigAnnotation = getCacheConfigAnnotation(joinPoint);
        return CacheAspectContext.builder()
                .cacheConfigAnnotation(getCacheConfigAnnotation(joinPoint))
                .originalNamespaces(CollectionsUtil.toList(originalNamespaces))
                .namespaces(CollectionsUtil.toList(parseNamespaces(cacheConfigAnnotation, originalNamespaces)))
                .originalCacheTypes(CollectionsUtil.toList(originalCacheTypes))
                .cacheTypes(parseCacheTypes(cacheConfigAnnotation, originalCacheTypes))
                .originalKey(originalKey)
                .key(parseKey(cacheConfigAnnotation, getMethodParameters(joinPoint), joinPoint.getArgs(), originalKey, keyGenerator))
                .keyGenerator(keyGenerator)
                .originalEnablePreventCachePenetrate(originalEnablePreventCachePenetrate)
                .enablePreventCachePenetrate(parseEnablePreventCachePenetrate(cacheConfigAnnotation, originalEnablePreventCachePenetrate))
                .needEvictBeforeProceed(needEvictBeforeProceed)
                .needEvictAllInNamespaces(needEvictAllInNamespaces)
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
        if (Objects.isNull(context.getValue())) {
            deleteCacheValue(context);
        } else {
            context.getNamespaces().forEach(namespace -> getCache(context, namespace).set(context.getKey(), context.getValue()));
        }
    }

    default void setCacheNullValue(CacheAspectContext context) {
        context.getNamespaces().forEach(namespace -> getCache(context, namespace).set(context.getKey(), NullValue.INSTANCE));
    }

    default void deleteCacheValue(CacheAspectContext context) {
        if (context.getEnablePreventCachePenetrate()) {
            setCacheNullValue(context);
        } else {
            context.getNamespaces().forEach(namespace -> getCache(context, namespace).delete(context.getKey()));
        }
    }

    default void deleteAllCacheValue(CacheAspectContext context) {
        if (context.getEnablePreventCachePenetrate()) {
            setCacheNullValue(context);
        } else {
            context.getNamespaces().forEach(namespace -> getCache(context, namespace).deleteAll(namespace));
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
        setCacheValue(context.setValue(value));
        return NullValueUtil.convertToNullIfNullValue(value);
    }

    default Object doEvict(ProceedingJoinPoint joinPoint, CacheAspectContext context, Function<ProceedingJoinPoint, Object> proceedPointCutLogic) {
        initCache(context);

        Object value;
        if (context.getNeedEvictBeforeProceed()) {
            if (context.getNeedEvictAllInNamespaces()) {
                deleteAllCacheValue(context);
            } else {
                deleteCacheValue(context);
            }
            value = proceedPointCutLogic.apply(joinPoint);
        } else {
            value = proceedPointCutLogic.apply(joinPoint);
            if (context.getNeedEvictAllInNamespaces()) {
                deleteAllCacheValue(context);
            } else {
                deleteCacheValue(context);
            }
        }

        return NullValueUtil.convertToNullIfNullValue(value);
    }

}
