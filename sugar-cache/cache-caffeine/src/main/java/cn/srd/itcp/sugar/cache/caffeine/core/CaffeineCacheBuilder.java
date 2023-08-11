package cn.srd.itcp.sugar.cache.caffeine.core;

import cn.srd.sugar.context.caffeine.config.properties.CaffeineCacheProperties;
import cn.srd.sugar.contract.throwable.core.UnsupportedOperationException;
import cn.srd.sugar.tool.lang.core.LambdasUtil;
import cn.srd.sugar.tool.lang.core.object.Objects;
import cn.srd.sugar.tool.lang.core.time.TimeUtil;
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
public class CaffeineCacheBuilder {

    /**
     * build {@link Cache} by {@link CaffeineCacheProperties}
     *
     * @param <K> key 类型
     * @param <V> value 类型
     * @return {@link Cache}
     */
    public static <K, V> Cache<K, V> build() {
        return build(CaffeineCacheProperties.getInstance());
    }

    /**
     * build {@link Cache} by {@link CaffeineCacheProperties}
     *
     * @param caffeineCacheProperties {@link CaffeineCacheProperties}
     * @param <K>                     key 类型
     * @param <V>                     value 类型
     * @return {@link Cache}
     */
    public static <K, V> Cache<K, V> build(CaffeineCacheProperties caffeineCacheProperties) {
        Caffeine<Object, Object> cacheBuilder = Caffeine.newBuilder();
        LambdasUtil.acceptIfNeed(Objects.isNull(caffeineCacheProperties.getExpireAfterAccess()) ? null : TimeUtil.wrapper(caffeineCacheProperties.getExpireAfterAccess()).toMillisecond(), TimeUtil::isPositive, cacheBuilder::expireAfterAccess);
        LambdasUtil.acceptIfNeed(Objects.isNull(caffeineCacheProperties.getExpireAfterWrite()) ? null : TimeUtil.wrapper(caffeineCacheProperties.getExpireAfterWrite()).toMillisecond(), TimeUtil::isPositive, cacheBuilder::expireAfterWrite);
        LambdasUtil.acceptIfNeed(Objects.isNull(caffeineCacheProperties.getRefreshAfterWrite()) ? null : TimeUtil.wrapper(caffeineCacheProperties.getRefreshAfterWrite()).toMillisecond(), TimeUtil::isPositive, cacheBuilder::refreshAfterWrite);
        LambdasUtil.acceptIfNeed(caffeineCacheProperties.getInitialCapacity(), Objects::isPositive, cacheBuilder::initialCapacity);
        LambdasUtil.acceptIfNeed(caffeineCacheProperties.getMaximumSize(), Objects::isPositive, cacheBuilder::maximumSize);
        LambdasUtil.acceptIfNeed(caffeineCacheProperties.getKeyReferenceLevel(), Objects::isNotNull, referenceLevel -> {
            switch (referenceLevel) {
                case SOFT -> throw new UnsupportedOperationException("unsupported [soft] reference type with caffeine key");
                case WEAK -> cacheBuilder.weakKeys();
                case PHANTOM -> throw new UnsupportedOperationException("unsupported [phantom] reference type with caffeine key");
                default -> {
                }
            }
        });
        LambdasUtil.acceptIfNeed(caffeineCacheProperties.getValueReferenceLevel(), Objects::isNotNull, referenceLevel -> {
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
