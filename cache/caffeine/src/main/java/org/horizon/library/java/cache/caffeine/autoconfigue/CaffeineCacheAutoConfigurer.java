package org.horizon.library.java.cache.caffeine.autoconfigue;

import org.horizon.library.java.cache.caffeine.model.property.CaffeineCacheProperty;
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
@EnableConfigurationProperties(CaffeineCacheProperty.class)
public class CaffeineCacheAutoConfigurer {

}