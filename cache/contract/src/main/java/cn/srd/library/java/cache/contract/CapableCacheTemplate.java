// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.cache.contract;

import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.convert.Converts;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.springframework.cache.support.NullValue;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Cache Template - More operation
 *
 * @param <K> cache key type
 * @author wjm
 * @since 2023-06-05 16:41
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
     * get cache in fuzzy expression, example:
     * <pre>
     *     h?llo subscribes to hello, hallo and hxllo
     *     h*llo subscribes to hllo and heeeello
     *     h[ae]llo subscribes to hello and hallo, but not hillo
     * </pre>
     *
     * @param pattern fuzzy expression
     * @param <V>     cache value type
     * @return cache value
     */
    default <V> List<V> getByPattern(String pattern) {
        return Converts.toMapValues(getMapByPattern(pattern));
    }

    /**
     * see {@link #getByPattern(String)}
     *
     * @param patterns fuzzy expression
     * @param <V>      cache value type
     * @return cache value
     */
    default <V> List<V> getByPattern(String... patterns) {
        return getByPattern(List.of(patterns));
    }

    /**
     * see {@link #getByPattern(String)}
     *
     * @param patterns fuzzy expression
     * @param <V>      cache value type
     * @return cache value
     */
    default <V> List<V> getByPattern(Collection<String> patterns) {
        List<V> output = new ArrayList<>();
        for (String pattern : patterns) {
            output.addAll(getByPattern(pattern));
        }
        return output;
    }

    /**
     * see {@link #getByPattern(String)}
     *
     * @param pattern fuzzy expression
     * @param <V>     cache value type
     * @return cache value
     */
    <V> Map<K, V> getMapByPattern(String pattern);

    /**
     * see {@link #getByPattern(String)}
     *
     * @param patterns fuzzy expression
     * @param <V>      cache value type
     * @return cache value
     */
    default <V> Map<K, V> getMapByPattern(String... patterns) {
        return getMapByPattern(List.of(patterns));
    }

    /**
     * see {@link #getByPattern(String)}
     *
     * @param patterns fuzzy expression
     * @param <V>      cache value type
     * @return cache value
     */
    default <V> Map<K, V> getMapByPattern(Collection<String> patterns) {
        Map<K, V> output = new HashMap<>();
        for (String pattern : patterns) {
            output.putAll(getMapByPattern(pattern));
        }
        return output;
    }

    /**
     * filter {@link #getByPattern(String)} if it is {@link NullValue}
     *
     * @param pattern fuzzy expression
     * @param <V>     cache value type
     * @return cache value
     */
    default <V> List<V> getByPatternWithoutNullValue(String pattern) {
        return Converts.toMapValues(getMapByPatternWithoutNullValue(pattern));
    }

    /**
     * filter {@link #getByPattern(String...)} if it is {@link NullValue}
     *
     * @param patterns fuzzy expression
     * @param <V>      cache value type
     * @return cache value
     */
    default <V> List<V> getByPatternWithoutNullValue(String... patterns) {
        return getByPatternWithoutNullValue(List.of(patterns));
    }

    /**
     * filter {@link #getByPattern(Collection)} if it is {@link NullValue}
     *
     * @param patterns fuzzy expression
     * @param <V>      cache value type
     * @return cache value
     */
    default <V> List<V> getByPatternWithoutNullValue(Collection<String> patterns) {
        List<V> output = new ArrayList<>();
        for (String pattern : patterns) {
            output.addAll(getByPatternWithoutNullValue(pattern));
        }
        return output;
    }

    /**
     * filter {@link #getByPattern(String)} if it is {@link NullValue}
     *
     * @param pattern fuzzy expression
     * @param <V>     cache value type
     * @return cache value
     */
    @SuppressWarnings("unchecked")
    default <V> Map<K, V> getMapByPatternWithoutNullValue(String pattern) {
        return getMapByPattern(pattern)
                .entrySet()
                .stream()
                .filter(entry -> Comparators.notEquals(NullValue.INSTANCE, entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> (V) entry.getValue()));
    }

    /**
     * filter {@link #getByPattern(String...)} if it is {@link NullValue}
     *
     * @param patterns fuzzy expression
     * @param <V>      cache value type
     * @return cache value
     */
    default <V> Map<K, V> getMapByPatternWithoutNullValue(String... patterns) {
        return getMapByPatternWithoutNullValue(List.of(patterns));
    }

    /**
     * filter {@link #getByPattern(Collection)} if it is {@link NullValue}
     *
     * @param patterns fuzzy expression
     * @param <V>      cache value type
     * @return cache value
     */
    default <V> Map<K, V> getMapByPatternWithoutNullValue(Collection<String> patterns) {
        Map<K, V> output = new HashMap<>();
        for (String pattern : patterns) {
            output.putAll(getMapByPatternWithoutNullValue(pattern));
        }
        return output;
    }

    /**
     * delete all cache in specified namespace, it is the same as {@link #deleteAll(String)}
     *
     * @param namespace the specified namespace, example: cache
     * @return affected number
     */
    @CanIgnoreReturnValue
    long deleteByNamespace(String namespace);

    /**
     * see {@link #deleteByNamespace(String)}
     *
     * @param namespaces the specified namespaces, example: cache1、cache2
     * @return affected number
     */
    @CanIgnoreReturnValue
    default long deleteByNamespace(String... namespaces) {
        return deleteByNamespace(List.of(namespaces));
    }

    /**
     * see {@link #deleteByNamespace(String)}
     *
     * @param namespaces the specified namespaces, example: cache1、cache2
     * @return affected number
     */
    @CanIgnoreReturnValue
    default long deleteByNamespace(Collection<String> namespaces) {
        long affectedNumber = 0;
        for (String namespace : namespaces) {
            affectedNumber = affectedNumber + deleteByNamespace(namespace);
        }
        return affectedNumber;
    }

    /**
     * delete cache in fuzzy expression
     * <pre>
     *     h?llo subscribes to hello, hallo and hxllo
     *     h*llo subscribes to hllo and heeeello
     *     h[ae]llo subscribes to hello and hallo, but not hillo
     * </pre>
     *
     * @param pattern fuzzy expression
     * @return affected number
     */
    @CanIgnoreReturnValue
    long deleteByPattern(String pattern);

    /**
     * see {@link #deleteByPattern(String)}
     *
     * @param patterns fuzzy expression
     * @return affected number
     */
    @CanIgnoreReturnValue
    default long deleteByPattern(String... patterns) {
        return deleteByPattern(List.of(patterns));
    }

    /**
     * see {@link #deleteByPattern(String)}
     *
     * @param patterns fuzzy expression
     * @return affected number
     */
    @CanIgnoreReturnValue
    default long deleteByPattern(Collection<String> patterns) {
        long affectedNumber = 0;
        for (String pattern : patterns) {
            affectedNumber = affectedNumber + deleteByPattern(pattern);
        }
        return affectedNumber;
    }

}