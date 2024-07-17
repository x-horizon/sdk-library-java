// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.oss.minio.strategy;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.text.SymbolConstant;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.oss.contract.model.domain.OssFileDO;
import cn.srd.library.java.oss.contract.strategy.OssStorage;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.text.Strings;
import org.dromara.x.file.storage.core.Downloader;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wjm
 * @since 2024-07-17 16:29
 */
public class OssMinioStorage implements OssStorage {

    @Autowired private FileStorageService fileStorageService;

    @Override
    public OssFileDO put(Object file, String path, String filename) {
        Assert.of(LibraryJavaInternalException.class, "{}the upload object is null, please check!", ModuleView.OSS_SYSTEM).throwsIfNull(file);
        return OssFileDO.from(fileStorageService.of(file)
                .setPath(Nil.isBlank(path) ? SymbolConstant.EMPTY : Strings.removeHeadTailSlash(path) + SymbolConstant.SLASH)
                .setSaveFilename(filename)
                .upload()
        );
    }

    @Override
    public Downloader get(FileInfo fileInfo) {
        return fileStorageService.download(fileInfo);
    }

}