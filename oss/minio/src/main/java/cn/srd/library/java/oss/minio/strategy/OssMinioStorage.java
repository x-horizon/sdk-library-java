// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.oss.minio.strategy;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.text.SymbolConstant;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.oss.contract.Oss;
import cn.srd.library.java.oss.contract.model.domain.OssFileDO;
import cn.srd.library.java.oss.contract.model.enums.OssType;
import cn.srd.library.java.oss.contract.model.property.OssProperty;
import cn.srd.library.java.oss.contract.strategy.OssStorage;
import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.text.Strings;
import org.dromara.x.file.storage.core.Downloader;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.dromara.x.file.storage.core.FileStorageServiceBuilder;
import org.dromara.x.file.storage.spring.SpringFileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wjm
 * @since 2024-07-17 16:29
 */
public class OssMinioStorage implements OssStorage {

    @Autowired private OssProperty ossProperty;

    @Autowired private FileStorageService fileStorageService;

    @Override
    public OssProperty.Config getOssConfigProperty() {
        return ossProperty.getConfigs().stream()
                .filter(ossConfig -> Comparators.equals(OssType.MINIO, ossConfig.getType()))
                .findFirst()
                .orElseThrow(() -> new LibraryJavaInternalException(Strings.format("{}could not find the oss config, please provide the configuration for oss [{}].", ModuleView.OSS_SYSTEM, OssType.MINIO.getDescription())));
    }

    @Override
    public void registerFileStorageProperties(String bucketName) {
        OssProperty.Config ossConfigProperty = getOssConfigProperty();
        SpringFileStorageProperties.SpringMinioConfig fileStorageMinioConfig = new SpringFileStorageProperties.SpringMinioConfig();
        fileStorageMinioConfig.setEnableStorage(true)
                .setEndPoint(ossConfigProperty.getServerUrl())
                .setAccessKey(ossConfigProperty.getAccessKey())
                .setSecretKey(ossConfigProperty.getSecretKey())
                .setBucketName(bucketName)
                .setPlatform(Oss.getPlatform(OssType.MINIO, bucketName));
        fileStorageService.getFileStorageList().addAll(FileStorageServiceBuilder.buildMinioFileStorage(List.of(fileStorageMinioConfig), null));
    }

    @Override
    public OssFileDO upload(Object file, String path, String filename) {
        Assert.of(LibraryJavaInternalException.class, "{}the upload object is null, please check!", ModuleView.OSS_SYSTEM).throwsIfNull(file);
        return OssFileDO.from(fileStorageService.of(file)
                .setPath(Nil.isBlank(path) ? SymbolConstant.EMPTY : Strings.removeHeadTailSlash(path) + SymbolConstant.SLASH)
                .setSaveFilename(filename)
                .upload()
        );
    }

    @Override
    public Downloader download(FileInfo fileInfo) {
        return fileStorageService.download(fileInfo);
    }

}