package cn.srd.library.java.cache.caffeine;

import cn.srd.library.java.cache.caffeine.model.properties.CacheCaffeineProperties;
import cn.srd.library.java.contract.model.throwable.UnsupportedException;
import cn.srd.library.java.tool.lang.functional.Functional;
import cn.srd.library.java.tool.lang.number.Numbers;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.time.Times;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * {@link Cache} Builder
 *
 * @author wjm
 * @since 2023-06-05 17:01
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CaffeineCacheBuilder {

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
        Functional.acceptIfNeed(Nil.isNull(cacheCaffeineProperties.getExpireAfterAccess()) ? null : Times.wrapper(cacheCaffeineProperties.getExpireAfterAccess()).toMillisecond(), Times::isPositive, cacheBuilder::expireAfterAccess);
        Functional.acceptIfNeed(Nil.isNull(cacheCaffeineProperties.getExpireAfterWrite()) ? null : Times.wrapper(cacheCaffeineProperties.getExpireAfterWrite()).toMillisecond(), Times::isPositive, cacheBuilder::expireAfterWrite);
        Functional.acceptIfNeed(Nil.isNull(cacheCaffeineProperties.getRefreshAfterWrite()) ? null : Times.wrapper(cacheCaffeineProperties.getRefreshAfterWrite()).toMillisecond(), Times::isPositive, cacheBuilder::refreshAfterWrite);
        Functional.acceptIfNeed(cacheCaffeineProperties.getInitialCapacity(), Numbers::isPositive, cacheBuilder::initialCapacity);
        Functional.acceptIfNeed(cacheCaffeineProperties.getMaximumSize(), Numbers::isPositive, cacheBuilder::maximumSize);
        Functional.acceptIfNeed(cacheCaffeineProperties.getKeyReferenceLevel(), Nil::isNotNull, referenceLevel -> {
            switch (referenceLevel) {
                case SOFT -> throw new UnsupportedException("unsupported [soft] reference type with caffeine key");
                case WEAK -> cacheBuilder.weakKeys();
                case PHANTOM -> throw new UnsupportedException("unsupported [phantom] reference type with caffeine key");
                default -> {
                }
            }
        });
        Functional.acceptIfNeed(cacheCaffeineProperties.getValueReferenceLevel(), Nil::isNotNull, referenceLevel -> {
            switch (referenceLevel) {
                case SOFT -> cacheBuilder.softValues();
                case WEAK -> cacheBuilder.weakValues();
                case PHANTOM -> throw new UnsupportedException("unsupported [phantom] reference type with caffeine value");
                default -> {
                }
            }
        });
        return cacheBuilder.build();
    }

}