package cn.srd.library.java.cache.all.property;

import cn.srd.library.java.cache.caffeine.property.CaffeineCacheProperties;
import cn.srd.library.java.cache.redis.property.RedisCacheProperties;
import cn.srd.library.java.tool.spring.contract.Springs;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.DependsOn;

/**
 * properties for cache
 *
 * @author wjm
 * @since 2023-06-07 16:48:52
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "library.java.cache")
public class CacheProperties {

    /**
     * instance
     */
    @Getter private static CacheProperties instance = null;

    /**
     * instance init
     */
    @DependsOn({"caffeineCacheProperties", "redisCacheProperties", "multilevelCacheProperties"})
    @PostConstruct
    public void initialize() {
        // do not use @NestedConfigurationProperty to inject,
        // because it will cause internally calculated fields being null.
        setCaffeine(Springs.getBean(CaffeineCacheProperties.class));
        setRedis(Springs.getBean(RedisCacheProperties.class));
        setMultilevel(Springs.getBean(MultilevelCacheProperties.class));
        instance = this;
    }

    /**
     * see {@link CaffeineCacheProperties}
     */
    private CaffeineCacheProperties caffeine = new CaffeineCacheProperties();

    /**
     * see {@link RedisCacheProperties}
     */
    private RedisCacheProperties redis = new RedisCacheProperties();

    /**
     * see {@link MultilevelCacheProperties}
     */
    private MultilevelCacheProperties multilevel = new MultilevelCacheProperties();

}
