package cn.srd.library.java.cache.all.model.properties;

import cn.srd.library.java.cache.caffeine.model.properties.CacheCaffeineProperties;
import cn.srd.library.java.cache.redis.model.properties.CacheRedisProperties;
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
 * @since 2023-06-07 16:48
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "library-java.cache")
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
        setCaffeine(Springs.getBean(CacheCaffeineProperties.class));
        setRedis(Springs.getBean(CacheRedisProperties.class));
        setMultilevel(Springs.getBean(CacheMultilevelProperties.class));
        instance = this;
    }

    /**
     * see {@link CacheCaffeineProperties}
     */
    private CacheCaffeineProperties caffeine = new CacheCaffeineProperties();

    /**
     * see {@link CacheRedisProperties}
     */
    private CacheRedisProperties redis = new CacheRedisProperties();

    /**
     * see {@link CacheMultilevelProperties}
     */
    private CacheMultilevelProperties multilevel = new CacheMultilevelProperties();

}