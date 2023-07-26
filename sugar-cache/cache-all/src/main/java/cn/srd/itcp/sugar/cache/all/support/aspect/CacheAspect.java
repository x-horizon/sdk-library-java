package cn.srd.itcp.sugar.cache.all.support.aspect;

import cn.srd.itcp.sugar.cache.all.core.CacheConfig;
import cn.srd.itcp.sugar.cache.all.support.manager.Cache;
import cn.srd.itcp.sugar.cache.all.support.manager.CacheManager;
import cn.srd.itcp.sugar.cache.all.support.manager.CacheMode;
import cn.srd.itcp.sugar.cache.all.support.manager.CacheType;
import cn.srd.itcp.sugar.cache.all.support.strategy.CacheKeyGenerator;
import cn.srd.itcp.sugar.cache.all.support.strategy.CacheModeStrategy;
import cn.srd.itcp.sugar.component.expression.all.core.Expressions;
import cn.srd.itcp.sugar.context.constant.core.ModuleConstant;
import cn.srd.itcp.sugar.framework.spring.tool.common.core.AopCaptor;
import cn.srd.itcp.sugar.framework.spring.tool.common.core.NullValueUtil;
import cn.srd.itcp.sugar.framework.spring.tool.common.core.SpringsUtil;
import cn.srd.itcp.sugar.tool.constant.StringPool;
import cn.srd.itcp.sugar.tool.core.ArraysUtil;
import cn.srd.itcp.sugar.tool.core.CollectionsUtil;
import cn.srd.itcp.sugar.tool.core.ReflectsUtil;
import cn.srd.itcp.sugar.tool.core.StringsUtil;
import cn.srd.itcp.sugar.tool.core.object.Objects;
import io.vavr.control.Option;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.cache.support.NullValue;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * the cache aspect common action
 *
 * @author wjm
 * @since 2023-06-09 15:06:14
 */
public interface CacheAspect extends AopCaptor {

    /**
     * the namespace expression mapping the namespace value map
     */
    Map<String, String> NAMESPACE_EXPRESSION_MAPPING_NAMESPACE_VALUE_MAP = new ConcurrentHashMap<>(256);

    /**
     * get {@link CacheConfig}
     *
     * @param joinPoint pointcut
     * @return see {@link CacheConfig}
     */
    default CacheConfig getCacheConfigAnnotation(ProceedingJoinPoint joinPoint) {
        return getAnnotationMarkedOnClass(joinPoint, CacheConfig.class);
    }

    /**
     * select which namespaces to use
     *
     * @param cacheConfigAnnotation see {@link CacheConfig}
     * @param namespacesOnMethod    the specified namespaces on method annotation
     * @return namespaces to use
     */
    default String[] parseNamespaces(CacheConfig cacheConfigAnnotation, String[] namespacesOnMethod) {
        if (Objects.isNotEmpty(namespacesOnMethod)) {
            return doParseNamespaces(namespacesOnMethod);
        }
        Objects.requireNotNull(() -> ModuleConstant.CACHE_SYSTEM + "could not find namespace on method annotation and unspecified namespace on annotation [{}], please specify at least one!", CacheConfig.class.getSimpleName(), cacheConfigAnnotation);
        return doParseNamespaces(cacheConfigAnnotation.namespaces());
    }

    /**
     * do parse namespace, support find value in config file
     *
     * @param namespaces the specified namespaces
     * @return namespaces after parse
     */
    default String[] doParseNamespaces(String[] namespaces) {
        // do not use the namespaces length as the initial capacity of the list,
        // because it may cause a null element and throw exception when use this null namespace as the key to find this key mapping cache instance from a concurrent hashmap
        List<String> namespacesAfterParse = new ArrayList<>();
        for (String originalNamespace : namespaces) {
            String namespace = originalNamespace;
            if (StringsUtil.startWith(originalNamespace, StringPool.DOLLAR_AND_DELIM_START) && StringsUtil.endWith(originalNamespace, StringPool.DELIM_END)) {
                namespace = NAMESPACE_EXPRESSION_MAPPING_NAMESPACE_VALUE_MAP.get(originalNamespace);
                if (Objects.isBlank(namespace)) {
                    namespace = SpringsUtil.getProperty(StringsUtil.removeIfStartAndEndWith(originalNamespace, StringPool.DOLLAR_AND_DELIM_START, StringPool.DELIM_END));
                    if (Objects.isNotBlank(namespace)) {
                        NAMESPACE_EXPRESSION_MAPPING_NAMESPACE_VALUE_MAP.put(originalNamespace, namespace);
                    }
                }
            }
            if (Objects.isNotBlank(namespace)) {
                namespacesAfterParse.add(namespace);
            }
        }
        Objects.requireNotEmpty(() -> ModuleConstant.CACHE_SYSTEM + "could not find namespace, please specify at least one!", namespacesAfterParse);
        return ArraysUtil.toArray(namespacesAfterParse, String.class);
    }

    /**
     * select which cache types to use
     *
     * @param cacheConfigAnnotation see {@link CacheConfig}
     * @param cacheTypesOnMethod    the specified cache types on method annotation
     * @return cache types to use
     */
    default List<CacheType> parseCacheTypes(CacheConfig cacheConfigAnnotation, CacheType[] cacheTypesOnMethod) {
        if (ArraysUtil.isNotEmpty(cacheTypesOnMethod)) {
            return CollectionsUtil.toList(cacheTypesOnMethod);
        }
        Objects.requireFalse(() -> ModuleConstant.CACHE_SYSTEM + "could not find cache type to cache, please specify at least one!", Objects.isNull(cacheConfigAnnotation) || ArraysUtil.isEmpty(cacheConfigAnnotation.cacheTypes()));
        return CollectionsUtil.toList(cacheConfigAnnotation.cacheTypes());
    }

    /**
     * select which cache key to use
     *
     * @param cacheConfigAnnotation    see {@link CacheConfig}
     * @param parameters               the pointcut method parameters
     * @param parameterValues          the pointcut method parameter values
     * @param keyExpression            the spring expression language (SpEL) expression
     * @param keyGeneratorOnMethod     the specified cache key generator on method annotation
     * @param needEvictAllInNamespaces need or not to evict all data in specified namespaces
     * @return cache key to use
     */
    default String parseKey(CacheConfig cacheConfigAnnotation, Parameter[] parameters, Object[] parameterValues, String keyExpression, Class<? extends CacheKeyGenerator> keyGeneratorOnMethod, Boolean needEvictAllInNamespaces) {
        if (Boolean.TRUE.equals(needEvictAllInNamespaces)) {
            return StringPool.EMPTY;
        }
        String key;
        if (Objects.isBlank(keyExpression)) {
            key = ReflectsUtil.newInstance(parseKeyGenerator(cacheConfigAnnotation, keyGeneratorOnMethod)).generate(parameters, parameterValues);
            Objects.requireNotBlank(() -> StringsUtil.format("{}could not generate cache key by keyGenerator [{}], please check!", ModuleConstant.CACHE_SYSTEM, keyGeneratorOnMethod.getSimpleName()), key);
        } else {
            key = Option.of(Expressions.withSpring().parse(parameters, parameterValues, keyExpression)).map(Object::toString).getOrNull();
            Objects.requireNotBlank(() -> StringsUtil.format("{}could not generate cache key by keyExpression [{}], please check!", ModuleConstant.CACHE_SYSTEM, keyExpression), key);
        }
        return key;
    }

    /**
     * select which key generator to use
     * TODO wjm optimize to singleton instance, do not initialize every time
     *
     * @param cacheConfigAnnotation see {@link CacheConfig}
     * @param keyGeneratorOnMethod  the specified cache key generator on method annotation
     * @return cache key generator to use
     */
    default Class<? extends CacheKeyGenerator> parseKeyGenerator(CacheConfig cacheConfigAnnotation, Class<? extends CacheKeyGenerator> keyGeneratorOnMethod) {
        Class<? extends CacheKeyGenerator> finalCacheKeyGenerator;
        if (Objects.isNull(cacheConfigAnnotation)) {
            finalCacheKeyGenerator = keyGeneratorOnMethod;
        } else {
            finalCacheKeyGenerator = Objects.equals(keyGeneratorOnMethod, CacheKeyGenerator.DEFAULT_KEY_GENERATOR) ? cacheConfigAnnotation.keyGenerator() : keyGeneratorOnMethod;
        }
        return finalCacheKeyGenerator;
    }

    /**
     * select which cache mode to use
     *
     * @param cacheConfigAnnotation see {@link CacheConfig}
     * @param cacheModeOnMethod     the specified cache mode on method annotation
     * @return cache mode to use
     */
    default CacheMode parseCacheMode(CacheConfig cacheConfigAnnotation, CacheMode cacheModeOnMethod) {
        CacheMode finalCacheMode;
        if (Objects.isNull(cacheConfigAnnotation)) {
            finalCacheMode = cacheModeOnMethod;
        } else {
            finalCacheMode = Objects.equals(cacheModeOnMethod, CacheModeStrategy.DEFAULT_CACHE_MODE) ? cacheConfigAnnotation.cacheMode() : cacheModeOnMethod;
        }
        return finalCacheMode;
    }

    /**
     * select which allow or not to set a {@link NullValue} in cache to use
     *
     * @param cacheConfigAnnotation   see {@link CacheConfig}
     * @param allowEmptyValueOnMethod the specified allow or not to set a {@link NullValue} in cache on method annotation
     * @return allow or not to set a {@link NullValue} in cache to use
     */
    default boolean parseAllowEmptyValue(CacheConfig cacheConfigAnnotation, boolean allowEmptyValueOnMethod) {
        if (allowEmptyValueOnMethod) {
            return true;
        }
        if (Objects.isNotNull(cacheConfigAnnotation)) {
            return cacheConfigAnnotation.allowEmptyValue();
        }
        return false;
    }

    /**
     * build {@link CacheAspectContext}
     *
     * @param joinPoint                pointcut
     * @param originalNamespaces       the original namespaces
     * @param originalCacheTypes       the original cache type
     * @param cacheMode                the cache mode
     * @param keyGenerator             the key generator
     * @param originalKey              the original cache key
     * @param needParseKey             need to parse key or not
     * @param originalAllowEmptyValue  the original allow or not to set {@link NullValue} to cache
     * @param needEvictBeforeProceed   need or not evict before execute method
     * @param needEvictAllInNamespaces need or not to evict all data in specified namespaces
     * @return {@link CacheAspectContext} instance
     */
    default CacheAspectContext buildContext(
            ProceedingJoinPoint joinPoint,
            String[] originalNamespaces,
            CacheType[] originalCacheTypes,
            CacheMode cacheMode,
            Class<? extends CacheKeyGenerator> keyGenerator,
            String originalKey,
            boolean needParseKey,
            Boolean originalAllowEmptyValue,
            Boolean needEvictBeforeProceed,
            Boolean needEvictAllInNamespaces
    ) {
        CacheConfig cacheConfigAnnotation = getCacheConfigAnnotation(joinPoint);
        return CacheAspectContext.builder()
                .cacheConfigAnnotation(getCacheConfigAnnotation(joinPoint))
                .originalNamespaces(CollectionsUtil.toList(originalNamespaces))
                .namespaces(CollectionsUtil.toList(parseNamespaces(cacheConfigAnnotation, originalNamespaces)))
                .originalCacheTypes(CollectionsUtil.toList(originalCacheTypes))
                .cacheTypes(parseCacheTypes(cacheConfigAnnotation, originalCacheTypes))
                .originalCacheMode(cacheMode)
                .cacheMode(parseCacheMode(cacheConfigAnnotation, cacheMode))
                .originalKey(originalKey)
                .key(needParseKey ? parseKey(cacheConfigAnnotation, getMethodParameters(joinPoint), joinPoint.getArgs(), originalKey, keyGenerator, needEvictAllInNamespaces) : null)
                .keyGenerator(keyGenerator)
                .originalAllowEmptyValue(originalAllowEmptyValue)
                .allowEmptyValue(parseAllowEmptyValue(cacheConfigAnnotation, originalAllowEmptyValue))
                .needEvictBeforeProceed(needEvictBeforeProceed)
                .needEvictAllInNamespaces(needEvictAllInNamespaces)
                .build();
    }

    /**
     * build {@link CacheAspectContext} for {@link CacheReadAspect}
     *
     * @param joinPoint               pointcut
     * @param originalNamespaces      the original namespaces
     * @param originalCacheTypes      the original cache type
     * @param keyGenerator            the key generator
     * @param originalKey             the original cache key
     * @param originalAllowEmptyValue the original allow or not to set {@link NullValue} to cache
     * @return {@link CacheAspectContext} instance
     */
    default CacheAspectContext buildCacheReadContext(ProceedingJoinPoint joinPoint, String[] originalNamespaces, CacheType[] originalCacheTypes, Class<? extends CacheKeyGenerator> keyGenerator, String originalKey, Boolean originalAllowEmptyValue) {
        return buildContext(joinPoint, originalNamespaces, originalCacheTypes, null, keyGenerator, originalKey, true, originalAllowEmptyValue, null, null);
    }

    /**
     * build {@link CacheAspectContext} for {@link CacheReadAllAspect}
     *
     * @param joinPoint               pointcut
     * @param originalNamespaces      the original namespaces
     * @param originalCacheTypes      the original cache type
     * @param originalKey             the original cache key
     * @param originalAllowEmptyValue the original allow or not to set {@link NullValue} to cache
     * @return {@link CacheAspectContext} instance
     */
    default CacheAspectContext buildCacheReadAllContext(ProceedingJoinPoint joinPoint, String[] originalNamespaces, CacheType[] originalCacheTypes, String originalKey, Boolean originalAllowEmptyValue) {
        return buildContext(joinPoint, originalNamespaces, originalCacheTypes, null, null, originalKey, false, originalAllowEmptyValue, null, null);
    }

    /**
     * build {@link CacheAspectContext} for {@link CacheWriteAspect}
     *
     * @param joinPoint               pointcut
     * @param originalNamespaces      the original namespaces
     * @param originalCacheTypes      the original cache type
     * @param cacheMode               the cache mode
     * @param originalKey             the original cache key
     * @param originalAllowEmptyValue the original allow or not to set {@link NullValue} to cache
     * @return {@link CacheAspectContext} instance
     */
    default CacheAspectContext buildCacheWriteContext(ProceedingJoinPoint joinPoint, String[] originalNamespaces, CacheType[] originalCacheTypes, CacheMode cacheMode, String originalKey, Boolean originalAllowEmptyValue) {
        return buildContext(joinPoint, originalNamespaces, originalCacheTypes, cacheMode, null, originalKey, false, originalAllowEmptyValue, null, null);
    }

    /**
     * build {@link CacheAspectContext} for {@link CacheEvictAspect}
     *
     * @param joinPoint                pointcut
     * @param originalNamespaces       the original namespaces
     * @param originalCacheTypes       the original cache type
     * @param keyGenerator             the key generator
     * @param originalKey              the original cache key
     * @param originalAllowEmptyValue  the original allow or not to set {@link NullValue} to cache
     * @param needEvictBeforeProceed   need or not evict before execute method
     * @param needEvictAllInNamespaces need or not to evict all data in specified namespaces
     * @return {@link CacheAspectContext} instance
     */
    default CacheAspectContext buildCacheEvictContext(ProceedingJoinPoint joinPoint, String[] originalNamespaces, CacheType[] originalCacheTypes, Class<? extends CacheKeyGenerator> keyGenerator, String originalKey, Boolean originalAllowEmptyValue, Boolean needEvictBeforeProceed, Boolean needEvictAllInNamespaces) {
        return buildContext(joinPoint, originalNamespaces, originalCacheTypes, null, keyGenerator, originalKey, true, originalAllowEmptyValue, needEvictBeforeProceed, needEvictAllInNamespaces);
    }

    /**
     * get cache value
     *
     * @param context see {@link CacheAspectContext}
     * @return cache value
     */
    default Object getCacheValue(CacheAspectContext context) {
        Object value = null;
        for (String namespace : context.getNamespaces()) {
            value = getCache(context, namespace).get(context.getKey());
            // lazy write cache:
            // there may be different data in different namespace,
            // for example: get nonnull data from some namespace, the all cache in this namespace will sync, but other namespace cache may refresh,
            // it is unnecessary to make all namespace sync cache data,
            // just need namespace that hit a nonnull data and sync all cache in this namespace.
            if (Objects.isNotNull(value)) {
                break;
            }
        }
        return value;
    }

    /**
     * get all cache values
     *
     * @param context see {@link CacheAspectContext}
     * @param <V>     the cache value type
     * @return cache value
     */
    default <V> List<V> getAllCacheValues(CacheAspectContext context) {
        List<V> values = new ArrayList<>();
        for (String namespace : context.getNamespaces()) {
            values = Boolean.TRUE.equals(context.getAllowEmptyValue()) ?
                    getCache(context, namespace).getByNamespace(namespace) :
                    getCache(context, namespace).getByNamespaceWithoutNullValue(namespace);
            // lazy write cache:
            // there may be different data in different namespace,
            // for example: get nonnull data from some namespace, the all cache in this namespace will sync, but other namespace cache may refresh,
            // it is unnecessary to make all namespace sync cache data,
            // just need namespace that hit a nonnull data and sync all cache in this namespace.
            if (Objects.isNotEmpty(values)) {
                break;
            }
        }
        return values;
    }

    /**
     * set cache value
     *
     * @param context see {@link CacheAspectContext}
     */
    default void setCacheValue(CacheAspectContext context) {
        context.getNamespaces().forEach(namespace -> getCache(context, namespace).set(context.getKey(), context.getValue()));
    }

    /**
     * delete cache value
     *
     * @param context see {@link CacheAspectContext}
     */
    default void deleteCacheValue(CacheAspectContext context) {
        if (CollectionsUtil.isJsonArray(context.getKey())) {
            CollectionsUtil.toList(context.getKey()).forEach(key -> context.getNamespaces().forEach(namespace -> getCache(context, namespace).delete(key)));
        } else {
            context.getNamespaces().forEach(namespace -> getCache(context, namespace).delete(context.getKey()));
        }
    }

    /**
     * delete all cache
     *
     * @param context see {@link CacheAspectContext}
     */
    default void deleteAllCacheValue(CacheAspectContext context) {
        context.getNamespaces().forEach(namespace -> getCache(context, namespace).deleteAll(namespace));
    }

    /**
     * init {@link Cache}
     *
     * @param context see {@link CacheAspectContext}
     */
    default void initCache(CacheAspectContext context) {
        for (String namespace : context.getNamespaces()) {
            getCache(context, namespace);
        }
    }

    /**
     * get {@link Cache}
     *
     * @param context   see {@link CacheAspectContext}
     * @param namespace see {@link CacheConfig#namespaces()}
     * @return {@link Cache} instance
     */
    default Cache getCache(CacheAspectContext context, String namespace) {
        return CacheManager.getInstance().getCache(namespace, context.getCacheTypes(), context.getAllowEmptyValue());
    }

    /**
     * read cache
     *
     * @param joinPoint pointcut
     * @param context   see {@link CacheAspectContext}
     * @return the cache value
     */
    default Object doRead(ProceedingJoinPoint joinPoint, CacheAspectContext context) {
        Object value = getCacheValue(context);
        if (Objects.isNull(value)) {
            value = doProceed(joinPoint);
            if (Objects.isNotNull(value) || Boolean.TRUE.equals(context.getAllowEmptyValue())) {
                setCacheValue(context.setValue(value));
            }
        }
        return NullValueUtil.convertToNullIfNullValue(value);
    }

    /**
     * read cache
     *
     * @param joinPoint pointcut
     * @param contexts  see {@link CacheAspectContext}
     * @return the cache value
     */
    default Object doRead(ProceedingJoinPoint joinPoint, List<CacheAspectContext> contexts) {
        Object value;
        for (CacheAspectContext context : contexts) {
            value = getCacheValue(context);
            // lazy write cache:
            // there may be different data in different namespace,
            // for example: get nonnull data from some namespace, the all cache in this namespace will sync, but other namespace cache may refresh,
            // it is unnecessary to make all namespace sync cache data,
            // just need namespace that hit a nonnull data and sync all cache in this namespace.
            if (Objects.isNotNull(value)) {
                return NullValueUtil.convertToNullIfNullValue(value);
            }
        }

        value = doProceed(joinPoint);
        for (CacheAspectContext context : contexts) {
            if (Objects.isNotNull(value) || Boolean.TRUE.equals(context.getAllowEmptyValue())) {
                setCacheValue(context.setValue(value));
            }
        }
        return NullValueUtil.convertToNullIfNullValue(value);
    }

    /**
     * read caches
     *
     * @param joinPoint pointcut
     * @param context   see {@link CacheAspectContext}
     * @param <T>       the cache value type
     * @return the cache values
     */
    @SuppressWarnings("unchecked")
    default <T> List<T> doReadAll(ProceedingJoinPoint joinPoint, CacheAspectContext context) {
        List<T> values = getAllCacheValues(context);
        if (Objects.isEmpty(values)) {
            values = (List<T>) doProceed(joinPoint);
            if (Objects.isEmpty(values) && Boolean.TRUE.equals(context.getAllowEmptyValue())) {
                setCacheValue(context.setKey(NullValueUtil.getName()).setValue(null));
            } else {
                for (T value : values) {
                    String key = Option.of(Expressions.withSpring().parse(value, context.getOriginalKey())).map(Object::toString).getOrNull();
                    Objects.requireNotBlank(() -> ModuleConstant.CACHE_SYSTEM + "could not parse the cache key when read all cache, please check!", key);
                    setCacheValue(context.setKey(key).setValue(value));
                }
            }
        }
        return NullValueUtil.filterNullValue(values);
    }

    /**
     * write cache
     *
     * @param joinPoint            pointcut
     * @param context              see {@link CacheAspectContext}
     * @param proceedPointCutLogic the proceed pointcut logic
     * @return the cache value
     */
    default Object doWrite(ProceedingJoinPoint joinPoint, CacheAspectContext context, Function<ProceedingJoinPoint, Object> proceedPointCutLogic) {
        initCache(context);
        Object value = proceedPointCutLogic.apply(joinPoint);
        if (Objects.isNotNull(value)) {
            doWrite(context, value);
        }
        return value;
    }

    /**
     * write cache batch
     *
     * @param joinPoint            pointcut
     * @param context              see {@link CacheAspectContext}
     * @param proceedPointCutLogic the proceed pointcut logic
     * @param <T>                  the cache value element type
     * @return the cache value
     */
    @SuppressWarnings("unchecked")
    default <T> List<T> doWriteBatch(ProceedingJoinPoint joinPoint, CacheAspectContext context, Function<ProceedingJoinPoint, Object> proceedPointCutLogic) {
        initCache(context);
        List<T> values = (List<T>) proceedPointCutLogic.apply(joinPoint);
        if (Objects.isNotEmpty(values)) {
            values.forEach(value -> doWrite(context, value));
        }
        return values;
    }

    /**
     * the actual handle write cache
     *
     * @param context see {@link CacheAspectContext}
     * @param value   the cache value
     */
    default void doWrite(CacheAspectContext context, Object value) {
        String key = Option.of(Expressions.withSpring().parse(value, context.getOriginalKey())).map(Object::toString).getOrNull();
        Objects.requireNotBlank(() -> ModuleConstant.CACHE_SYSTEM + "could not parse the cache key when write cache, please check!", key);
        /**
         * TODO wjm need optimize to use strategy
         */
        if (Objects.equals(CacheMode.READ_ONLY, context.getCacheMode())) {
            deleteCacheValue(context.setKey(key));
        } else if (Objects.equals(CacheMode.READ_WRITE, context.getCacheMode())) {
            setCacheValue(context.setKey(key).setValue(value));
        }
    }

    /**
     * evict cache
     *
     * @param joinPoint            pointcut
     * @param context              see {@link CacheAspectContext}
     * @param proceedPointCutLogic the proceed pointcut logic
     * @return the cache value
     */
    default Object doEvict(ProceedingJoinPoint joinPoint, CacheAspectContext context, Function<ProceedingJoinPoint, Object> proceedPointCutLogic) {
        Object value;
        if (Boolean.TRUE.equals(context.getNeedEvictBeforeProceed())) {
            if (Boolean.TRUE.equals(context.getNeedEvictAllInNamespaces())) {
                deleteAllCacheValue(context);
            } else {
                deleteCacheValue(context);
            }
            value = proceedPointCutLogic.apply(joinPoint);
        } else {
            initCache(context);
            value = proceedPointCutLogic.apply(joinPoint);
            if (Boolean.TRUE.equals(context.getNeedEvictAllInNamespaces())) {
                deleteAllCacheValue(context);
            } else {
                deleteCacheValue(context);
            }
        }
        return NullValueUtil.convertToNullIfNullValue(value);
    }

}
