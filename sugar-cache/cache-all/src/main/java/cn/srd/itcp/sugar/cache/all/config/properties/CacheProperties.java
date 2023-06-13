package cn.srd.itcp.sugar.cache.all.config.properties;

import cn.srd.itcp.sugar.context.caffeine.config.properties.CacheCaffeineProperties;
import cn.srd.itcp.sugar.context.redisson.config.properties.CacheRedissonProperties;
import cn.srd.itcp.sugar.framework.spring.tool.common.core.SpringsUtil;
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
@ConfigurationProperties(prefix = "sugar.cache")
public class CacheProperties {

    /**
     * instance
     */
    private static CacheProperties instance = null;

    /**
     * instance init
     */
    @DependsOn({"cacheCaffeineProperties", "cacheRedissonProperties", "cacheMultilevelProperties"})
    @PostConstruct
    public void init() {
        // do not use @NestedConfigurationProperty to inject,
        // because it will cause internally calculated fields being null.
        setCaffeine(SpringsUtil.getBean(CacheCaffeineProperties.class));
        setRedisson(SpringsUtil.getBean(CacheRedissonProperties.class));
        setMultilevel(SpringsUtil.getBean(CacheMultilevelProperties.class));
        instance = this;
    }

    /**
     * get singleton instance
     *
     * @return instance
     */
    public static CacheProperties getInstance() {
        return instance;
    }

    /**
     * see {@link CacheCaffeineProperties}
     */
    private CacheCaffeineProperties caffeine = new CacheCaffeineProperties();

    /**
     * see {@link CacheRedissonProperties}
     */
    private CacheRedissonProperties redisson = new CacheRedissonProperties();

    /**
     * see {@link CacheMultilevelProperties}
     */
    private CacheMultilevelProperties multilevel = new CacheMultilevelProperties();

}
