// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.oss.contract.autoconfigure;

import cn.srd.library.java.oss.contract.model.property.OssProperty;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Oss Minio
 *
 * @author wjm
 * @since 2024-06-19 23:51
 */
@AutoConfiguration
@EnableConfigurationProperties(OssProperty.class)
public class OssAutoConfigurer {

}