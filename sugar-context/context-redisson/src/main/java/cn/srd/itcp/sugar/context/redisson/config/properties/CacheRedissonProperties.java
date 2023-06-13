package cn.srd.itcp.sugar.context.redisson.config.properties;

import cn.srd.itcp.sugar.framework.spring.tool.common.core.SpringsUtil;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.DependsOn;

/**
 * properties for cache redisson
 *
 * @author wjm
 * @since 2023-06-13 09:24:19
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "sugar.cache.redisson")
public class CacheRedissonProperties {

    /**
     * instance
     */
    private static CacheRedissonProperties instance = null;

    /**
     * instance init
     */
    @DependsOn("spring.data.redis-org.springframework.boot.autoconfigure.data.redis.RedisProperties")
    @PostConstruct
    public void init() {
        this.baseInfo = (RedisProperties) SpringsUtil.getBean("spring.data.redis-org.springframework.boot.autoconfigure.data.redis.RedisProperties");
        instance = this;
    }

    /**
     * get singleton instance
     *
     * @return instance
     */
    public static CacheRedissonProperties getInstance() {
        return instance;
    }

    /**
     * see {@link RedisProperties}
     */
    // TODO wjm  @NestedConfigurationProperty unable to take effect
    // @NestedConfigurationProperty
    private RedisProperties baseInfo = new RedisProperties();

}
