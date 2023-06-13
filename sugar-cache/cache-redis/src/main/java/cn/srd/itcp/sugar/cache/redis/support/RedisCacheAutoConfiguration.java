package cn.srd.itcp.sugar.cache.redis.support;

import cn.srd.itcp.sugar.context.redis.config.properties.RedisCacheProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Sugar Cache Redis
 *
 * @author wjm
 * @since 2023-06-13 09:24:19
 */
@AutoConfiguration
@EnableConfigurationProperties(RedisCacheProperties.class)
public class RedisCacheAutoConfiguration {

}
