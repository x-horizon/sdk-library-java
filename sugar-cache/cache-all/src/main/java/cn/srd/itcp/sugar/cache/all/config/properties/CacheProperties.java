package cn.srd.itcp.sugar.cache.all.config.properties;

import cn.srd.itcp.sugar.cache.caffeine.config.properties.CaffeineCacheProperties;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

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
    @PostConstruct
    public void init() {
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

    @NestedConfigurationProperty
    private CaffeineCacheProperties caffeine = new CaffeineCacheProperties();

}
