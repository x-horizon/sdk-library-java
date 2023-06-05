package cn.srd.itcp.sugar.cache.all.config.properties;

import cn.srd.itcp.sugar.cache.caffeine.config.properties.CacheCaffeineProperties;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Getter
@Setter
@ConfigurationProperties(prefix = "sugar.component.cache")
public class CacheAllProperties {

    /**
     * instance
     */
    private static CacheAllProperties instance = null;

    /**
     * instance init
     */
    @PostConstruct
    public void init() {
        instance = this;
    }

    /**
     * get singleton instance
     *
     * @return instance
     */
    public static CacheAllProperties getInstance() {
        return instance;
    }

    @NestedConfigurationProperty
    private CacheCaffeineProperties caffeine = new CacheCaffeineProperties();

}
