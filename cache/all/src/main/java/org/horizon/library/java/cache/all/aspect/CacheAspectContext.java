package org.horizon.library.java.cache.all.aspect;

import org.horizon.library.java.cache.all.CacheConfig;
import org.horizon.library.java.cache.all.model.enums.CacheMode;
import org.horizon.library.java.cache.all.model.enums.CacheType;
import org.horizon.library.java.cache.all.strategy.CacheKeyGenerator;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.cache.support.NullValue;

import java.util.List;

/**
 * cache context
 *
 * @author wjm
 * @since 2023-06-09 15:06
 */
@Data
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public class CacheAspectContext {

    /**
     * see {@link CacheConfig}
     */
    private CacheConfig cacheConfigAnnotation;

    /**
     * the original namespaces
     */
    private List<String> originalNamespaces;

    /**
     * the namespaces to be used
     */
    private List<String> namespaces;

    /**
     * the original cache type
     */
    private List<CacheType> originalCacheTypes;

    /**
     * the cache type to be used
     */
    private List<CacheType> cacheTypes;

    /**
     * the original cache mode
     */
    private CacheMode originalCacheMode;

    /**
     * the cache mode
     */
    private CacheMode cacheMode;

    /**
     * the original cache key
     */
    private String originalKey;

    /**
     * the cache key to be used
     */
    private String key;

    /**
     * the key generator
     */
    Class<? extends CacheKeyGenerator> keyGenerator;

    /**
     * the cache value
     */
    private Object value;

    /**
     * the original allow or not to set {@link NullValue} to cache
     */
    private Boolean originalAllowEmptyValue;

    /**
     * allow or not to set {@link NullValue} to cache to be used
     */
    private Boolean allowEmptyValue;

    /**
     * need or not evict before execute method
     */
    private Boolean needEvictBeforeProceed;

    /**
     * need or not to evict all data in specified namespaces
     */
    private Boolean needEvictAllInNamespaces;

}