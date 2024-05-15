package cn.srd.library.java.cache.all.support.aspect;

import cn.srd.library.java.cache.all.CacheConfig;
import cn.srd.library.java.cache.all.support.manager.Cache;
import cn.srd.library.java.cache.all.support.manager.CacheManager;
import cn.srd.library.java.cache.all.support.manager.CacheMode;
import cn.srd.library.java.cache.all.support.manager.CacheType;
import cn.srd.library.java.cache.all.support.strategy.CacheKeyGenerator;
import cn.srd.library.java.cache.all.support.strategy.CacheModeStrategy;
import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.text.SymbolConstant;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.tool.lang.booleans.Booleans;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.convert.Converts;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.reflect.Reflects;
import cn.srd.library.java.tool.lang.text.Strings;
import cn.srd.library.java.tool.spring.contract.AopCaptor;
import cn.srd.library.java.tool.spring.contract.Expressions;
import cn.srd.library.java.tool.spring.contract.NullValues;
import cn.srd.library.java.tool.spring.contract.Springs;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.cache.support.NullValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * the cache aspect common action
 *
 * @author wjm
 * @since 2023-06-09 15:06
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
        if (Nil.isNotEmpty(namespacesOnMethod)) {
            return doParseNamespaces(namespacesOnMethod);
        }
        Assert.of().setMessage("{}could not find namespace on method annotation and unspecified namespace on annotation [{}], please specify at least one!", ModuleView.CACHE_SYSTEM, CacheConfig.class.getSimpleName())
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfNull(cacheConfigAnnotation);
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
            if (Strings.startWith(originalNamespace, SymbolConstant.DOLLAR_AND_DELIM_START) && Strings.endWith(originalNamespace, SymbolConstant.DELIM_END)) {
                namespace = NAMESPACE_EXPRESSION_MAPPING_NAMESPACE_VALUE_MAP.get(originalNamespace);
                if (Nil.isBlank(namespace)) {
                    namespace = Springs.getProperty(Strings.removeIfStartAndEndWith(originalNamespace, SymbolConstant.DOLLAR_AND_DELIM_START, SymbolConstant.DELIM_END));
                    if (Nil.isNotBlank(namespace)) {
                        NAMESPACE_EXPRESSION_MAPPING_NAMESPACE_VALUE_MAP.put(originalNamespace, namespace);
                    }
                }
            }
            if (Nil.isNotBlank(namespace)) {
                namespacesAfterParse.add(namespace);
            }
        }
        Assert.of().setMessage("{}could not find namespace, please specify at least one!", ModuleView.CACHE_SYSTEM)
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfEmpty(namespacesAfterParse);
        return Converts.toArray(namespacesAfterParse, String.class);
    }

    /**
     * select which cache types to use
     *
     * @param cacheConfigAnnotation see {@link CacheConfig}
     * @param cacheTypesOnMethod    the specified cache types on method annotation
     * @return cache types to use
     */
    default List<CacheType> parseCacheTypes(CacheConfig cacheConfigAnnotation, CacheType[] cacheTypesOnMethod) {
        if (Nil.isNotEmpty(cacheTypesOnMethod)) {
            return Collections.ofImmutableList(cacheTypesOnMethod);
        }
        Assert.of().setMessage("{}could not find cache type to cache, please specify at least one!", ModuleView.CACHE_SYSTEM)
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfTrue(Nil.isNull(cacheConfigAnnotation) || Nil.isEmpty(cacheConfigAnnotation.cacheTypes()));
        return Collections.ofImmutableList(cacheConfigAnnotation.cacheTypes());
    }

    /**
     * select which cache key to use
     *
     * @param cacheConfigAnnotation    see {@link CacheConfig}
     * @param parameterNames           the pointcut method parameter names
     * @param parameterValues          the pointcut method parameter values
     * @param keyExpression            the spring expression language (SpEL) expression
     * @param keyGeneratorOnMethod     the specified cache key generator on method annotation
     * @param needEvictAllInNamespaces need or not to evict all data in specified namespaces
     * @return cache key to use
     */
    default String parseKey(CacheConfig cacheConfigAnnotation, String[] parameterNames, Object[] parameterValues, String keyExpression, Class<? extends CacheKeyGenerator> keyGeneratorOnMethod, Boolean needEvictAllInNamespaces) {
        if (Booleans.isTrue(needEvictAllInNamespaces)) {
            return SymbolConstant.EMPTY;
        }
        String key;
        if (Nil.isBlank(keyExpression)) {
            key = Reflects.newInstance(parseKeyGenerator(cacheConfigAnnotation, keyGeneratorOnMethod)).generate(parameterNames, parameterValues);
            Assert.of().setMessage("{}could not generate cache key by keyGenerator [{}], please check!", ModuleView.CACHE_SYSTEM, keyGeneratorOnMethod.getSimpleName())
                    .setThrowable(LibraryJavaInternalException.class)
                    .throwsIfBlank(key);
        } else {
            key = Optional.ofNullable(Expressions.getInstance().parse(parameterNames, parameterValues, keyExpression)).map(Object::toString).orElse(null);
            Assert.of().setMessage("{}could not generate cache key by keyExpression [{}], please check!", ModuleView.CACHE_SYSTEM, keyExpression)
                    .setThrowable(LibraryJavaInternalException.class)
                    .throwsIfBlank(key);
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
        if (Nil.isNull(cacheConfigAnnotation)) {
            finalCacheKeyGenerator = keyGeneratorOnMethod;
        } else {
            finalCacheKeyGenerator = Comparators.equals(keyGeneratorOnMethod, CacheKeyGenerator.DEFAULT_KEY_GENERATOR) ? cacheConfigAnnotation.keyGenerator() : keyGeneratorOnMethod;
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
        if (Nil.isNull(cacheConfigAnnotation)) {
            finalCacheMode = cacheModeOnMethod;
        } else {
            finalCacheMode = Comparators.equals(cacheModeOnMethod, CacheModeStrategy.DEFAULT_CACHE_MODE) ? cacheConfigAnnotation.cacheMode() : cacheModeOnMethod;
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
        if (Nil.isNotNull(cacheConfigAnnotation)) {
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
                .originalNamespaces(Collections.ofImmutableList(originalNamespaces))
                .namespaces(Collections.ofImmutableList(parseNamespaces(cacheConfigAnnotation, originalNamespaces)))
                .originalCacheTypes(Collections.ofImmutableList(originalCacheTypes))
                .cacheTypes(parseCacheTypes(cacheConfigAnnotation, originalCacheTypes))
                .originalCacheMode(cacheMode)
                .cacheMode(parseCacheMode(cacheConfigAnnotation, cacheMode))
                .originalKey(originalKey)
                .key(needParseKey ? parseKey(cacheConfigAnnotation, getMethodParameterNames(joinPoint), joinPoint.getArgs(), originalKey, keyGenerator, needEvictAllInNamespaces) : null)
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
            if (Nil.isNotNull(value)) {
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
            values = Booleans.isTrue(context.getAllowEmptyValue()) ?
                    getCache(context, namespace).getByNamespace(namespace) :
                    getCache(context, namespace).getByNamespaceWithoutNullValue(namespace);
            // lazy write cache:
            // there may be different data in different namespace,
            // for example: get nonnull data from some namespace, the all cache in this namespace will sync, but other namespace cache may refresh,
            // it is unnecessary to make all namespace sync cache data,
            // just need namespace that hit a nonnull data and sync all cache in this namespace.
            if (Nil.isNotEmpty(values)) {
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
        if (Collections.isEmptyArrayString(context.getKey())) {
            Collections.ofImmutableList(context.getKey()).forEach(key -> context.getNamespaces().forEach(namespace -> getCache(context, namespace).delete(key)));
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
        if (Nil.isNull(value)) {
            value = doProceed(joinPoint);
            if (Nil.isNotNull(value) || Booleans.isTrue(context.getAllowEmptyValue())) {
                setCacheValue(context.setValue(value));
            }
        }
        return NullValues.convertToNullIfNullValue(value);
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
            if (Nil.isNotNull(value)) {
                return NullValues.convertToNullIfNullValue(value);
            }
        }

        value = doProceed(joinPoint);
        for (CacheAspectContext context : contexts) {
            if (Nil.isNotNull(value) || Booleans.isTrue(context.getAllowEmptyValue())) {
                setCacheValue(context.setValue(value));
            }
        }
        return NullValues.convertToNullIfNullValue(value);
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
        if (Nil.isEmpty(values)) {
            values = (List<T>) doProceed(joinPoint);
            if (Nil.isEmpty(values) && Booleans.isTrue(context.getAllowEmptyValue())) {
                setCacheValue(context.setKey(NullValues.getName()).setValue(null));
            } else {
                for (T value : values) {
                    String key = Optional.ofNullable(Expressions.getInstance().parse(value, context.getOriginalKey())).map(Object::toString).orElse(null);
                    Assert.of().setMessage("{}could not parse the cache key when read all cache, please check!", ModuleView.CACHE_SYSTEM)
                            .setThrowable(LibraryJavaInternalException.class)
                            .throwsIfBlank(key);
                    setCacheValue(context.setKey(key).setValue(value));
                }
            }
        }
        return NullValues.removeNullValue(values);
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
        if (Nil.isNotNull(value)) {
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
        if (Nil.isNotEmpty(values)) {
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
        String key = Optional.ofNullable(Expressions.getInstance().parse(value, context.getOriginalKey())).map(Object::toString).orElse(null);
        Assert.of().setMessage("{}could not parse the cache key when write cache, please check!", ModuleView.CACHE_SYSTEM)
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfBlank(key);
        /**
         * TODO wjm need optimize to use strategy
         */
        if (Comparators.equals(CacheMode.READ_ONLY, context.getCacheMode())) {
            deleteCacheValue(context.setKey(key));
        } else if (Comparators.equals(CacheMode.READ_WRITE, context.getCacheMode())) {
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
        if (Booleans.isTrue(context.getNeedEvictBeforeProceed())) {
            if (Booleans.isTrue(context.getNeedEvictAllInNamespaces())) {
                deleteAllCacheValue(context);
            } else {
                deleteCacheValue(context);
            }
            value = proceedPointCutLogic.apply(joinPoint);
        } else {
            initCache(context);
            value = proceedPointCutLogic.apply(joinPoint);
            if (Booleans.isTrue(context.getNeedEvictAllInNamespaces())) {
                deleteAllCacheValue(context);
            } else {
                deleteCacheValue(context);
            }
        }
        return NullValues.convertToNullIfNullValue(value);
    }

}