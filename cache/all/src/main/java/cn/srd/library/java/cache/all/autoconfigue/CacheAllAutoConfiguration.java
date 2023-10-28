package cn.srd.library.java.cache.all.autoconfigue;

import cn.srd.library.java.cache.all.property.CacheProperties;
import cn.srd.library.java.cache.all.property.MultilevelCacheProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * TODO wjm 未完成：
 * TODO wjm 1、多节点更新缓存时的 serverId
 * TODO wjm 2、RedisTopics.addListener
 * TODO wjm 3、超时时间的实现
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
