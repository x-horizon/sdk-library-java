package cn.srd.itcp.sugar.cache.all.test;

import cn.srd.itcp.sugar.cache.all.config.properties.CacheProperties;
import cn.srd.itcp.sugar.cache.all.test.properties.CacheConfigProperties;
import cn.srd.itcp.sugar.cache.all.test.support.*;
import cn.srd.itcp.sugar.topic.redisson.core.RedissonTopics;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

@AutoConfiguration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableConfigurationProperties({CacheConfigProperties.class, CacheProperties.class})
public class MultilevelCacheAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(RedisTemplate.class)
    public CacheManager cacheManager(
            CacheConfigProperties cacheConfigProperties,
            ObjectProvider<RedisCaffeineCacheManagerCustomizer> cacheManagerCustomizers,
            ObjectProvider<ServerIdGenerator> serverIdGenerators
    ) {
        Object serverId = cacheConfigProperties.getServerId();
        if (serverId == null || "".equals(serverId)) {
            serverIdGenerators.ifAvailable(serverIdGenerator -> cacheConfigProperties.setServerId(serverIdGenerator.get()));
        }
        RedisCaffeineCacheManager cacheManager = new RedisCaffeineCacheManager(cacheConfigProperties);
        cacheManagerCustomizers.orderedStream().forEach(customizer -> customizer.customize(cacheManager));

        // TODO wjm 该行代码要放到适合的位置
        RedissonTopics.addListener(cacheConfigProperties.getRedis().getTopic(), CacheMessage.class, new CacheMessageListener(cacheManager));

        return cacheManager;
    }

}
