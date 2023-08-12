package cn.srd.library.cache.all.support;

import cn.srd.library.cache.all.config.properties.CacheProperties;
import cn.srd.library.cache.all.config.properties.MultilevelCacheProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * TODO wjm 未完成：
 * TODO wjm 1、多节点更新缓存时的 serverId
 * TODO wjm 2、RedisTopics.addListener
 * <p>
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Cache All
 *
 * @author wjm
 * @since 2023-06-07 16:48:52
 */
@AutoConfiguration
@EnableConfigurationProperties({CacheProperties.class, MultilevelCacheProperties.class})
public class CacheAllAutoConfiguration {

}
