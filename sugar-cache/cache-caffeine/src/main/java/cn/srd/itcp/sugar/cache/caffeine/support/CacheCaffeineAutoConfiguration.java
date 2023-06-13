package cn.srd.itcp.sugar.cache.caffeine.support;

import cn.srd.itcp.sugar.context.caffeine.config.properties.CacheCaffeineProperties;
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
@EnableConfigurationProperties(CacheCaffeineProperties.class)
public class CacheCaffeineAutoConfiguration {

}
