// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.oss.minio.model.property;

import cn.srd.library.java.oss.contract.model.property.OssProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wjm
 * @since 2024-06-19 23:58
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "library-java.oss.minio")
public class MinioProperty extends OssProperty {

}