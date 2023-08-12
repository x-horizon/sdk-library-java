package cn.srd.library.cache.all.config.properties;

import cn.srd.library.context.caffeine.config.properties.CaffeineCacheProperties;
import cn.srd.library.context.redis.config.properties.RedisCacheProperties;
import cn.srd.library.tool.spring.common.core.SpringsUtil;
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
@ConfigurationProperties(prefix = "library.cache")
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
    public void init() {
        // do not use @NestedConfigurationProperty to inject,
        // because it will cause internally calculated fields being null.
        setCaffeine(SpringsUtil.getBean(CaffeineCacheProperties.class));
        setRedis(SpringsUtil.getBean(RedisCacheProperties.class));
        setMultilevel(SpringsUtil.getBean(MultilevelCacheProperties.class));
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
