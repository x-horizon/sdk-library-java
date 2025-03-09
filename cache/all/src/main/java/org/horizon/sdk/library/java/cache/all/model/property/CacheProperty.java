package org.horizon.sdk.library.java.cache.all.model.property;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.horizon.sdk.library.java.cache.caffeine.model.property.CaffeineCacheProperty;
import org.horizon.sdk.library.java.cache.redis.model.property.RedisCacheProperty;
import org.horizon.sdk.library.java.tool.spring.contract.support.Springs;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.DependsOn;

/**
 * property for cache
 *
 * @author wjm
 * @since 2023-06-07 16:48
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "library-java.cache")
public class CacheProperty {

    /**
     * instance
     */
    @Getter private static CacheProperty instance = null;

    /**
     * instance init
     */
    @DependsOn({"caffeineCacheProperty", "redisCacheProperty", "multilevelCacheProperty"})
    @PostConstruct
    public void initialize() {
        // do not use @NestedConfigurationProperty to inject,
        // because it will cause internally calculated fields being null.
        setCaffeine(Springs.getBean(CaffeineCacheProperty.class));
        setRedis(Springs.getBean(RedisCacheProperty.class));
        setMultilevel(Springs.getBean(MultilevelCacheProperty.class));
        instance = this;
    }

    /**
     * see {@link CaffeineCacheProperty}
     */
    private CaffeineCacheProperty caffeine = new CaffeineCacheProperty();

    /**
     * see {@link RedisCacheProperty}
     */
    private RedisCacheProperty redis = new RedisCacheProperty();

    /**
     * see {@link MultilevelCacheProperty}
     */
    private MultilevelCacheProperty multilevel = new MultilevelCacheProperty();

}