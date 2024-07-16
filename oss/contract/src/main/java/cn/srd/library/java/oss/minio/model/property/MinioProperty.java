// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.oss.minio.model.property;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author wjm
 * @since 2024-06-19 23:58
 */
@Getter
@Setter
public class MinioProperty {

    private String serverUrl;

    private List<ConfigProperty> configs;

    @Getter
    @Setter
    public static class ConfigProperty {

        private String accessKey;

        private String secretKey;

        private List<BucketProperty> buckets;

        @Getter
        @Setter
        public static class BucketProperty {

            private Boolean defaultIs = false;

            private String name;

            private String basePath;

        }

    }

}