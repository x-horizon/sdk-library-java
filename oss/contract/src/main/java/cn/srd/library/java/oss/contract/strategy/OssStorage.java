// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.oss.contract.strategy;

import cn.srd.library.java.oss.contract.model.domain.OssFileDO;
import org.dromara.x.file.storage.core.Downloader;
import org.dromara.x.file.storage.core.FileInfo;

/**
 * @author wjm
 * @since 2024-07-17 16:25
 */
public interface OssStorage {

    OssFileDO put(Object file, String path, String filename);

    default OssFileDO put(Object file) {
        return put(file, null);
    }

    default OssFileDO put(Object file, String path) {
        return put(file, path, null);
    }

    Downloader get(FileInfo fileInfo);

}