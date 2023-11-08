// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.cache.redis.property;

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
    public void initialize() {
        this.baseInfo = (RedisProperties) Springs.getBean("spring.data.redis-org.springframework.boot.autoconfigure.data.redis.RedisProperties");
        instance = this;
    }

    /**
     * see {@link RedisProperties}
     */
    // TODO wjm  @NestedConfigurationProperty unable to take effect
    // @NestedConfigurationProperty
    private RedisProperties baseInfo = new RedisProperties();

}
