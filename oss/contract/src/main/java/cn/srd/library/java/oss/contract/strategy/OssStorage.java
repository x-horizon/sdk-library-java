// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.oss.contract.strategy;

import org.dromara.x.file.storage.core.Downloader;
import org.dromara.x.file.storage.core.FileInfo;

/**
 * @author wjm
 * @since 2024-07-17 16:25
 */
public interface OssStorage {

    void registerFileStorageProperties(String bucketName);

    Downloader download(FileInfo fileInfo);

}