package cn.srd.library.java.cache.caffeine;

import cn.srd.library.java.cache.caffeine.property.CaffeineCacheProperties;
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
        Functional.acceptIfNeed(Nil.isNull(caffeineCacheProperties.getExpireAfterAccess()) ? null : Times.wrapper(caffeineCacheProperties.getExpireAfterAccess()).toMillisecond(), Times::isPositive, cacheBuilder::expireAfterAccess);
        Functional.acceptIfNeed(Nil.isNull(caffeineCacheProperties.getExpireAfterWrite()) ? null : Times.wrapper(caffeineCacheProperties.getExpireAfterWrite()).toMillisecond(), Times::isPositive, cacheBuilder::expireAfterWrite);
        Functional.acceptIfNeed(Nil.isNull(caffeineCacheProperties.getRefreshAfterWrite()) ? null : Times.wrapper(caffeineCacheProperties.getRefreshAfterWrite()).toMillisecond(), Times::isPositive, cacheBuilder::refreshAfterWrite);
        Functional.acceptIfNeed(caffeineCacheProperties.getInitialCapacity(), Numbers::isPositive, cacheBuilder::initialCapacity);
        Functional.acceptIfNeed(caffeineCacheProperties.getMaximumSize(), Numbers::isPositive, cacheBuilder::maximumSize);
        Functional.acceptIfNeed(caffeineCacheProperties.getKeyReferenceLevel(), Nil::isNotNull, referenceLevel -> {
            switch (referenceLevel) {
                case SOFT -> throw new UnsupportedOperationException("unsupported [soft] reference type with caffeine key");
                case WEAK -> cacheBuilder.weakKeys();
                case PHANTOM -> throw new UnsupportedOperationException("unsupported [phantom] reference type with caffeine key");
                default -> {
                }
            }
        });
        Functional.acceptIfNeed(caffeineCacheProperties.getValueReferenceLevel(), Nil::isNotNull, referenceLevel -> {
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
