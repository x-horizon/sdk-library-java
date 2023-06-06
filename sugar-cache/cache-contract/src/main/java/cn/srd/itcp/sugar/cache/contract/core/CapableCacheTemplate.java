package cn.srd.itcp.sugar.cache.contract.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

import java.util.List;

/**
 * Cache Template - More operation
 *
 * @param <K> cache key type
 * @author wjm
 * @since 2023-06-05 16:41:28
 */
public interface CapableCacheTemplate<K> extends CacheTemplate<K> {

    /**
     * update old cache to <code>updateValue</code> if old cache is <code>expectedValue</code> and do nothing in other cases
     *
     * @param key           cache key
     * @param expectedValue the expected value
     * @param updateValue   the new value
     * @param <V>           cache value type
     * @return true if successful, or false if element wasn't set
     */
    @CanIgnoreReturnValue
    <V> boolean compareAndSet(K key, V expectedValue, V updateValue);

    /**
     * get all cache in specified namespace
     *
     * @param namespace the specified namespace
     * @param <V>       cache value type
     * @return cache value
     */
    <V> List<V> getByNamespace(String namespace);

    /**
     * get cache in fuzzy namespace
     *
     * @param pattern fuzzy expression, example: cache:*
     * @param <V>     cache value type
     * @return cache value
     */
    <V> List<V> getByPattern(String pattern);

    /**
     * delete all cache in specified namespace
     *
     * @param namespace the specified namespace
     * @return affected number
     */
    @CanIgnoreReturnValue
    int deleteByNamespace(String namespace);

    /**
     * delete cache in fuzzy namespace
     *
     * @param pattern fuzzy expression, example: cache:*
     * @return affected number
     */
    @CanIgnoreReturnValue
    int deleteByPattern(String pattern);

}
