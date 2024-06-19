// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.oss.minio.autoconfigure;

import cn.srd.library.java.oss.minio.model.property.MinioProperty;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author wjm
 * @since 2024-06-20 00:02
 */
@AutoConfiguration
@EnableConfigurationProperties(MinioProperty.class)
public class MinioAutoConfigurer {

}