package cn.srd.itcp.sugar.cache.all.support;

import cn.srd.itcp.sugar.cache.all.config.properties.CacheProperties;
import cn.srd.itcp.sugar.cache.caffeine.config.properties.CaffeineCacheProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * TODO wjm 未完成：
 * TODO wjm 1、多节点更新缓存时的 serverId
 * TODO wjm 2、RedissonTopics.addListener
 * TODO wjm 3、考虑 key value 为空的问题
 * TODO wjm 4、考虑 线程安全问题
 * TODO wjm 5、时间有问题：DurationWrapper result25 = cache.getExpirationTime(CACHE_NAME1); DurationWrapper result26 = cache.getTimeToLive(CACHE_NAME1);
 * <p>
 * {@link EnableAutoConfiguration AutoConfiguration} for Sugar Cache All
 *
 * @author wjm
 * @since 2023-06-07 16:48:52
 */
@AutoConfiguration
@EnableConfigurationProperties({CaffeineCacheProperties.class, CacheProperties.class})
public class CacheAllAutoConfiguration {

}
