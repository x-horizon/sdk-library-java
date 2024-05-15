package cn.srd.library.java.cache.caffeine.autoconfigue;

import cn.srd.library.java.contract.properties.CacheCaffeineProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Cache Caffeine
 *
 * @author wjm
 * @since 2023-06-05 17:01
 */
@AutoConfiguration
@EnableConfigurationProperties(CacheCaffeineProperties.class)
public class CaffeineCacheAutoConfigurer {

}