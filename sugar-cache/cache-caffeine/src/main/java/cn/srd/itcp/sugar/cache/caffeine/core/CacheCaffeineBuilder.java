package cn.srd.itcp.sugar.cache.caffeine.core;

import cn.srd.itcp.sugar.context.caffeine.config.properties.CacheCaffeineProperties;
import cn.srd.itcp.sugar.tool.core.LambdasUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import cn.srd.itcp.sugar.tool.core.time.TimeUtil;
import cn.srd.itcp.sugar.tool.exceptions.UnsupportedOperationException;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * {@link Cache} Builder
 *
 * @author wjm
 * @since 2023-06-05 17:01:12
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CacheCaffeineBuilder {

    /**
     * build {@link Cache} by {@link CacheCaffeineProperties}
     *
     * @param <K> key 类型
     * @param <V> value 类型
     * @return {@link Cache}
     */
    public static <K, V> Cache<K, V> build() {
        return build(CacheCaffeineProperties.getInstance());
    }

    /**
     * build {@link Cache} by {@link CacheCaffeineProperties}
     *
     * @param cacheCaffeineProperties {@link CacheCaffeineProperties}
     * @param <K>                     key 类型
     * @param <V>                     value 类型
     * @return {@link Cache}
     */
    public static <K, V> Cache<K, V> build(CacheCaffeineProperties cacheCaffeineProperties) {
        Caffeine<Object, Object> cacheBuilder = Caffeine.newBuilder();
        LambdasUtil.acceptIfNeed(Objects.isNull(cacheCaffeineProperties.getExpireAfterAccess()) ? null : TimeUtil.wrapper(cacheCaffeineProperties.getExpireAfterAccess()).toMillisecond(), TimeUtil::isPositive, cacheBuilder::expireAfterAccess);
        LambdasUtil.acceptIfNeed(Objects.isNull(cacheCaffeineProperties.getExpireAfterWrite()) ? null : TimeUtil.wrapper(cacheCaffeineProperties.getExpireAfterWrite()).toMillisecond(), TimeUtil::isPositive, cacheBuilder::expireAfterWrite);
        LambdasUtil.acceptIfNeed(Objects.isNull(cacheCaffeineProperties.getRefreshAfterWrite()) ? null : TimeUtil.wrapper(cacheCaffeineProperties.getRefreshAfterWrite()).toMillisecond(), TimeUtil::isPositive, cacheBuilder::refreshAfterWrite);
        LambdasUtil.acceptIfNeed(cacheCaffeineProperties.getInitialCapacity(), Objects::isPositive, cacheBuilder::initialCapacity);
        LambdasUtil.acceptIfNeed(cacheCaffeineProperties.getMaximumSize(), Objects::isPositive, cacheBuilder::maximumSize);
        LambdasUtil.acceptIfNeed(cacheCaffeineProperties.getKeyReferenceLevel(), Objects::isNotNull, referenceLevel -> {
            switch (referenceLevel) {
                case SOFT -> throw new UnsupportedOperationException("unsupported [soft] reference type with caffeine key");
                case WEAK -> cacheBuilder.weakKeys();
                case PHANTOM -> throw new UnsupportedOperationException("unsupported [phantom] reference type with caffeine key");
                default -> {
                }
            }
        });
        LambdasUtil.acceptIfNeed(cacheCaffeineProperties.getValueReferenceLevel(), Objects::isNotNull, referenceLevel -> {
            switch (referenceLevel) {
                case SOFT -> cacheBuilder.softValues();
                case WEAK -> cacheBuilder.weakValues();
                case PHANTOM -> throw new UnsupportedOperationException("unsupported [phantom] reference type with caffeine value");
                default -> {
                }
            }
        });
        return cacheBuilder.build();
    }

}
