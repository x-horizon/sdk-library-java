package cn.srd.library.java.cache.caffeine;

import cn.srd.library.java.cache.caffeine.model.property.CaffeineCacheProperty;
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
     * build {@link Cache} by {@link CaffeineCacheProperty}
     *
     * @param <K> key 类型
     * @param <V> value 类型
     * @return {@link Cache}
     */
    public static <K, V> Cache<K, V> build() {
        return build(CaffeineCacheProperty.getInstance());
    }

    /**
     * build {@link Cache} by {@link CaffeineCacheProperty}
     *
     * @param caffeineCacheProperty {@link CaffeineCacheProperty}
     * @param <K>                   key 类型
     * @param <V>                   value 类型
     * @return {@link Cache}
     */
    public static <K, V> Cache<K, V> build(CaffeineCacheProperty caffeineCacheProperty) {
        Caffeine<Object, Object> cacheBuilder = Caffeine.newBuilder();
        Functional.acceptIfNeed(Nil.isNull(caffeineCacheProperty.getExpireAfterAccess()) ? null : Times.wrapper(caffeineCacheProperty.getExpireAfterAccess()).toMillisecond(), Times::isPositive, cacheBuilder::expireAfterAccess);
        Functional.acceptIfNeed(Nil.isNull(caffeineCacheProperty.getExpireAfterWrite()) ? null : Times.wrapper(caffeineCacheProperty.getExpireAfterWrite()).toMillisecond(), Times::isPositive, cacheBuilder::expireAfterWrite);
        Functional.acceptIfNeed(Nil.isNull(caffeineCacheProperty.getRefreshAfterWrite()) ? null : Times.wrapper(caffeineCacheProperty.getRefreshAfterWrite()).toMillisecond(), Times::isPositive, cacheBuilder::refreshAfterWrite);
        Functional.acceptIfNeed(caffeineCacheProperty.getInitialCapacity(), Numbers::isPositive, cacheBuilder::initialCapacity);
        Functional.acceptIfNeed(caffeineCacheProperty.getMaximumSize(), Numbers::isPositive, cacheBuilder::maximumSize);
        Functional.acceptIfNeed(caffeineCacheProperty.getKeyReferenceLevel(), Nil::isNotNull, referenceLevel -> {
            switch (referenceLevel) {
                case SOFT -> throw new UnsupportedException("unsupported [soft] reference type with caffeine key");
                case WEAK -> cacheBuilder.weakKeys();
                case PHANTOM -> throw new UnsupportedException("unsupported [phantom] reference type with caffeine key");
                default -> {
                }
            }
        });
        Functional.acceptIfNeed(caffeineCacheProperty.getValueReferenceLevel(), Nil::isNotNull, referenceLevel -> {
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