// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.oss.minio.autoconfigure;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.oss.contract.autoconfigure.OssAutoConfigurer;
import cn.srd.library.java.oss.contract.model.enums.OssType;
import cn.srd.library.java.oss.contract.model.property.OssProperty;
import cn.srd.library.java.oss.minio.model.property.MinioProperty;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.spring.contract.support.Springs;
import org.dromara.x.file.storage.spring.FileStorageAutoConfiguration;
import org.dromara.x.file.storage.spring.SpringFileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.List;

/**
 * @author wjm
 * @since 2024-06-20 00:02
 */
@AutoConfigureBefore(FileStorageAutoConfiguration.class)
@AutoConfigureAfter(OssAutoConfigurer.class)
@ConditionalOnBean(OssMinioRegistrar.class)
public class MinioAutoConfigurer {

    @Autowired private OssProperty ossProperty;

    @Bean
    public SpringFileStorageProperties springFileStorageProperties() {
        SpringFileStorageProperties fileStorageProperties = getFileStorageProperties();
        List<SpringFileStorageProperties.SpringMinioConfig> fileStorageMinioConfigs = Collections.newArrayList();
        ossProperty.getMinio().stream()
                .map(minioProperty -> Collections.ofPair(minioProperty, minioProperty.getConfigs()))
                .map(minioPropertyMappingConfigsMap -> minioPropertyMappingConfigsMap.getValue().stream()
                        .map(config -> Collections.ofPair(config, config.getBuckets()))
                        .map(configMappingBucketsMap -> {
                            MinioProperty.ConfigProperty config = configMappingBucketsMap.getKey();
                            List<MinioProperty.ConfigProperty.BucketProperty> buckets = configMappingBucketsMap.getValue();
                            buckets.forEach(bucket -> {
                                SpringFileStorageProperties.SpringMinioConfig fileStorageMinioConfig = new SpringFileStorageProperties.SpringMinioConfig();
                                fileStorageMinioConfig.setEnableStorage(true)
                                        .setEndPoint(minioPropertyMappingConfigsMap.getKey().getServerUrl())
                                        .setAccessKey(config.getAccessKey())
                                        .setSecretKey(config.getSecretKey())
                                        .setBasePath(bucket.getBasePath())
                                        .setBucketName(bucket.getName())
                                        .setPlatform(getPlatform(OssType.MINIO, bucket.getName()));
                                fileStorageMinioConfigs.add(fileStorageMinioConfig);
                            });
                            return buckets;
                        })
                        .flatMap(Collection::stream)
                        .toList()
                )
                .flatMap(Collection::stream)
                .toList()
                .stream()
                .filter(MinioProperty.ConfigProperty.BucketProperty::getDefaultIs)
                .findFirst()
                .ifPresent(bucket -> fileStorageProperties.setDefaultPlatform(getPlatform(OssType.MINIO, bucket.getName())));
        return fileStorageProperties.setMinio(fileStorageMinioConfigs);
    }

    private SpringFileStorageProperties getFileStorageProperties() {
        SpringFileStorageProperties fileStorageProperties = Springs.getBean(SpringFileStorageProperties.class);
        return Nil.isNull(fileStorageProperties) ? new SpringFileStorageProperties() : fileStorageProperties;
    }

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    private String getPlatform(OssType ossType, String bucketName) {
        return STR."\{ossType.name()}-\{bucketName}";
    }

}