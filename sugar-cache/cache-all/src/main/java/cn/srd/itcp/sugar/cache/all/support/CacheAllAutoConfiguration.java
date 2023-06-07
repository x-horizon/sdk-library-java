package cn.srd.itcp.sugar.cache.all.support;

import cn.srd.itcp.sugar.cache.all.config.properties.CacheProperties;
import cn.srd.itcp.sugar.cache.all.core.EnableMultilevelCaching;
import cn.srd.itcp.sugar.framework.spring.tool.common.core.SpringsUtil;
import cn.srd.itcp.sugar.tool.core.AnnotationsUtil;
import cn.srd.itcp.sugar.tool.core.CollectionsUtil;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;

/**
 * TODO wjm 未完成：
 * TODO wjm 1、多节点更新缓存时的 serverId
 * TODO wjm 2、RedissonTopics.addListener
 *
 * @author wjm
 * @since 2023-06-07 16:48:52
 */
@AutoConfiguration
@AutoConfigureAfter(RedissonAutoConfiguration.class)
@EnableConfigurationProperties(CacheProperties.class)
public class CacheAllAutoConfiguration {

    @Bean
    @ConditionOnEnableMultilevelCaching
    public CacheManager cacheManager() {
        Class<?> classesWithEnableMultilevelCaching = CollectionsUtil.getFirst(SpringsUtil.scanPackageByAnnotation(EnableMultilevelCaching.class));
        EnableMultilevelCaching enableMultilevelCaching = AnnotationsUtil.getAnnotation(classesWithEnableMultilevelCaching, EnableMultilevelCaching.class);
        return new MultilevelCacheManager(CollectionsUtil.toList(enableMultilevelCaching.cacheTypes()), enableMultilevelCaching.preventCachePenetrate());
    }

}
