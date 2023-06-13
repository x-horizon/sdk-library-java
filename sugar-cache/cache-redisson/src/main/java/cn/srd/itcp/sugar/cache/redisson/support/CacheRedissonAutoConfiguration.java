package cn.srd.itcp.sugar.cache.redisson.support;

import cn.srd.itcp.sugar.context.redisson.config.properties.CacheRedissonProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Sugar Cache Redisson
 *
 * @author wjm
 * @since 2023-06-13 09:24:19
 */
@AutoConfiguration
@EnableConfigurationProperties(CacheRedissonProperties.class)
public class CacheRedissonAutoConfiguration {

}
