// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.oss.contract.strategy;

import cn.srd.library.java.oss.contract.model.domain.OssFileDO;
import cn.srd.library.java.oss.contract.model.property.OssProperty;
import org.dromara.x.file.storage.core.Downloader;
import org.dromara.x.file.storage.core.FileInfo;

/**
 * @author wjm
 * @since 2024-07-17 16:25
 */
public interface OssStorage {

    OssProperty.Config getOssConfigProperty();

    void registerFileStorageProperties(String bucketName);

    OssFileDO upload(Object file, String path, String filename);

    Downloader download(FileInfo fileInfo);

    default OssFileDO upload(Object file) {
        return upload(file, null);
    }

    default OssFileDO upload(Object file, String path) {
        return upload(file, path, null);
    }

}