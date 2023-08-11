package cn.srd.sugar.cache.caffeine.support;

import cn.srd.sugar.context.caffeine.config.properties.CaffeineCacheProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Sugar Cache Caffeine
 *
 * @author wjm
 * @since 2023-06-05 17:01:12
 */
@AutoConfiguration
@EnableConfigurationProperties(CaffeineCacheProperties.class)
public class CaffeineCacheAutoConfiguration {

}
