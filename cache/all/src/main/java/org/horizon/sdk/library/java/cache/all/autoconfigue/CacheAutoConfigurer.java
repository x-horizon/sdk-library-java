package org.horizon.sdk.library.java.cache.all.autoconfigue;

import org.horizon.sdk.library.java.cache.all.aspect.*;
import org.horizon.sdk.library.java.cache.all.aspect.*;
import org.horizon.sdk.library.java.cache.all.model.property.CacheProperty;
import org.horizon.sdk.library.java.cache.all.model.property.MultilevelCacheProperty;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * TODO wjm 未完成：
 * TODO wjm 1、多节点更新缓存时的 serverId
 * TODO wjm 2、RedisTopics.addListener
 * TODO wjm 3、超时时间的实现
 * <p>
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Cache All
 *
 * @author wjm
 * @since 2023-06-07 16:48
 */
@AutoConfiguration
@EnableConfigurationProperties({CacheProperty.class, MultilevelCacheProperty.class})
public class CacheAutoConfigurer {

    @Bean
    @ConditionalOnBean(CacheRegistrar.class)
    public CacheReadAspect cacheReadAspect() {
        return new CacheReadAspect();
    }

    @Bean
    @ConditionalOnBean(CacheRegistrar.class)
    public CacheReadAllAspect cacheReadAllAspect() {
        return new CacheReadAllAspect();
    }

    @Bean
    @ConditionalOnBean(CacheRegistrar.class)
    public CacheWriteAspect cacheWriteAspect() {
        return new CacheWriteAspect();
    }

    @Bean
    @ConditionalOnBean(CacheRegistrar.class)
    public CacheWriteBatchAspect cacheWriteBatchAspect() {
        return new CacheWriteBatchAspect();
    }

    @Bean
    @ConditionalOnBean(CacheRegistrar.class)
    public CacheEvictAspect cacheEvictAspect() {
        return new CacheEvictAspect();
    }

    @Bean
    @ConditionalOnBean(CacheRegistrar.class)
    public CachingAspect cachingAspect() {
        return new CachingAspect();
    }

}