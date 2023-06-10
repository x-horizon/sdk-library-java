package cn.srd.itcp.sugar.cache.all.support;

import cn.srd.itcp.sugar.cache.all.core.CacheConfig;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author wjm
 * @since 2023-06-09 15:06:14
 */
@Data
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public class CacheAspectContext {

    private CacheConfig cacheConfigAnnotation;

    private List<String> originalNamespaces;

    private List<String> namespaces;

    private List<CacheType> originalCacheTypes;

    private List<CacheType> cacheTypes;

    private String originalKey;

    private String key;

    Class<? extends CacheKeyGenerator> keyGenerator;

    private Object value;

    private Boolean originalEnablePreventCachePenetrate;

    private Boolean enablePreventCachePenetrate;

    private Boolean needEvictBeforeProceed;

    private Boolean needEvictAllInNamespaces;

}
