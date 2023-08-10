package cn.srd.itcp.sugar.context.redis.config.properties;

import cn.srd.sugar.tool.spring.common.core.SpringsUtil;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.DependsOn;

/**
 * properties for cache redis
 *
 * @author wjm
 * @since 2023-06-13 09:24:19
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "sugar.cache.redis")
public class RedisCacheProperties {

    /**
     * instance
     */
    @Getter private static RedisCacheProperties instance = null;

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
     * see {@link RedisProperties}
     */
    // TODO wjm  @NestedConfigurationProperty unable to take effect
    // @NestedConfigurationProperty
    private RedisProperties baseInfo = new RedisProperties();

}
