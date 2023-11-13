// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.properties;

import cn.srd.library.java.tool.spring.contract.Springs;
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
 * @since 2023-06-13 09:24
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "library.java.cache.redis")
public class CacheRedisProperties {

    /**
     * TODO wjm need to be removed
     */
    public static final String SPRING_REDIS_PROPERTIES_REFERENCE_PATH = "spring.data.redis-org.springframework.boot.autoconfigure.data.redis.RedisProperties";

    /**
     * instance
     */
    @Getter private static CacheRedisProperties instance = null;

    /**
     * instance init
     */
    @DependsOn(SPRING_REDIS_PROPERTIES_REFERENCE_PATH)
    @PostConstruct
    public void initialize() {
        this.baseInfo = (RedisProperties) Springs.getBean(SPRING_REDIS_PROPERTIES_REFERENCE_PATH);
        instance = this;
    }

    /**
     * see {@link RedisProperties}
     */
    // TODO wjm  @NestedConfigurationProperty unable to take effect
    // @NestedConfigurationProperty
    private RedisProperties baseInfo = new RedisProperties();

}
