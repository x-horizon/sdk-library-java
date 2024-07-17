// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.oss.contract.model.property;

import cn.srd.library.java.oss.minio.model.property.MinioProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author wjm
 * @since 2024-06-19 23:51
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "library-java.oss")
public class OssProperty {

    private List<MinioProperty> minio;

}