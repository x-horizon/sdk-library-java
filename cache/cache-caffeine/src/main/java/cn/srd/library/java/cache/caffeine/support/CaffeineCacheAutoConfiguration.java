package cn.srd.library.java.cache.caffeine.support;

import cn.srd.library.java.contract.cache.caffeine.config.properties.CaffeineCacheProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Cache Caffeine
 *
 * @author wjm
 * @since 2023-06-05 17:01:12
 */
@AutoConfiguration
@EnableConfigurationProperties(CaffeineCacheProperties.class)
public class CaffeineCacheAutoConfiguration {

}
