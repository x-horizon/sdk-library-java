package cn.srd.itcp.sugar.cache.all.support;

import cn.srd.itcp.sugar.cache.all.config.properties.CacheProperties;
import cn.srd.itcp.sugar.cache.caffeine.config.properties.CaffeineCacheProperties;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * TODO wjm 未完成：
 * TODO wjm 1、多节点更新缓存时的 serverId
 * TODO wjm 2、RedissonTopics.addListener
 * TODO wjm 3、考虑 key value 为空的问题
 * TODO wjm 4、考虑 线程安全问题
 * TODO wjm 5、时间有问题：DurationWrapper result25 = cache.getExpirationTime(CACHE_NAME1); DurationWrapper result26 = cache.getTimeToLive(CACHE_NAME1);
 *
 * @author wjm
 * @since 2023-06-07 16:48:52
 */
@AutoConfiguration
@AutoConfigureAfter(RedissonAutoConfiguration.class)
@EnableConfigurationProperties({CaffeineCacheProperties.class, CacheProperties.class})
// @EnableMultilevelCaching(cacheTypes = {CacheType.MAP, CacheType.CAFFEINE, CacheType.REDIS}, enablePreventCachePenetrate = true)
public class CacheAllAutoConfiguration {

    // @Bean
    // @ConditionOnEnableMultilevelCaching
    // public CacheManager cacheManager() {
    //     Class<?> classesWithEnableMultilevelCaching = CollectionsUtil.getFirst(SpringsUtil.scanPackageByAnnotation(EnableMultilevelCaching.class));
    //     EnableMultilevelCaching enableMultilevelCaching = AnnotationsUtil.getAnnotation(classesWithEnableMultilevelCaching, EnableMultilevelCaching.class);
    //     return new MultilevelCacheManager(CollectionsUtil.toList(enableMultilevelCaching.cacheTypes()), enableMultilevelCaching.enablePreventCachePenetrate());
    // }

    @Bean
    public CacheReadAspect cacheReadAspect() {
        return new CacheReadAspect();
    }

}
