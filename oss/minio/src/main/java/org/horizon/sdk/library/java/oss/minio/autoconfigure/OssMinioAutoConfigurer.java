package org.horizon.sdk.library.java.oss.minio.autoconfigure;

import org.dromara.x.file.storage.spring.FileStorageAutoConfiguration;
import org.dromara.x.file.storage.spring.SpringFileStorageProperties;
import org.horizon.sdk.library.java.oss.contract.autoconfigure.OssAutoConfigurer;
import org.horizon.sdk.library.java.oss.contract.model.property.OssProperty;
import org.horizon.sdk.library.java.oss.minio.strategy.OssMinioStorage;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Oss Minio
 *
 * @author wjm
 * @since 2024-06-20 00:02
 */
@AutoConfigureBefore({OssAutoConfigurer.class, FileStorageAutoConfiguration.class})
@ConditionalOnBean(OssMinioRegistrar.class)
@EnableConfigurationProperties(OssProperty.class)
public class OssMinioAutoConfigurer {

    @Bean
    public OssMinioStorage ossMinioStorage() {
        return new OssMinioStorage();
    }

    @Bean
    public SpringFileStorageProperties springFileStorageProperties() {
        return new SpringFileStorageProperties();
    }

}