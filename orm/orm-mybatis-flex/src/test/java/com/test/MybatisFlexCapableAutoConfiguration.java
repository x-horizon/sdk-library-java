package com.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

@Slf4j
// @Order(Ordered.HIGHEST_PRECEDENCE)
@AutoConfiguration
// @Import({PostgresqlMetadataInjector.class})
// @EnableConfigurationProperties(OrmMybatisPlusProperties.class)
// @AutoConfigureBefore(MybatisFlexAutoConfiguration.class)
// @EnableConfigurationProperties(RedisCacheProperties.class)
public class MybatisFlexCapableAutoConfiguration {

    @Bean
    @ConditionalOnBean(EnableMybatisFlexCapableCustomizerFlag.class)
    public MybatisFlexCapableCustomizer mybatisFlexCapableCustomizer() {
        return new MybatisFlexCapableCustomizer();
    }

}
