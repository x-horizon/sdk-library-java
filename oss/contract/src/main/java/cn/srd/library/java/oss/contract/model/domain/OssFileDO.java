// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.oss.contract.model.domain;

import cn.srd.library.java.contract.constant.text.SymbolConstant;
import cn.srd.library.java.contract.model.base.DO;
import cn.srd.library.java.tool.lang.text.Strings;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.dromara.x.file.storage.core.FileInfo;

import java.io.Serial;

/**
 * @author wjm
 * @since 2024-07-17 15:44
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class OssFileDO implements DO {

    @Serial private static final long serialVersionUID = 4837913881555826085L;

    private String platform;

    private String name;

    private String originalName;

    private String directoryPath;

    private String fullPath;

    private String extension;

    private String mime;

    private Long size;

    private String thumbnailName;

    private String thumbnailFullPath;

    private String thumbnailMime;

    private Long thumbnailSize;

    public static OssFileDO from(FileInfo fileInfo) {
        return OssFileDO.builder()
                .platform(fileInfo.getPlatform())
                .name(fileInfo.getFilename())
                .originalName(fileInfo.getOriginalFilename())
                .directoryPath(SymbolConstant.SLASH + Strings.removeHeadTailSlash(fileInfo.getPath()))
                .fullPath(SymbolConstant.SLASH + Strings.removeHeadSlash(fileInfo.getUrl()))
                .extension(fileInfo.getExt())
                .mime(fileInfo.getContentType())
                .size(fileInfo.getSize())
                .thumbnailName(fileInfo.getThFilename())
                .thumbnailFullPath(SymbolConstant.SLASH + Strings.removeHeadSlash(fileInfo.getThUrl()))
                .thumbnailMime(fileInfo.getThContentType())
                .thumbnailSize(fileInfo.getThSize())
                .build();
    }

    public FileInfo toFileInfo() {
        return new FileInfo()
                .setPlatform(getPlatform())
                .setFilename(getName())
                .setOriginalFilename(getOriginalName())
                .setPath(Strings.removeHeadTailSlash(getDirectoryPath()) + SymbolConstant.SLASH)
                .setUrl(Strings.removeHeadSlash(getFullPath()))
                .setExt(getExtension())
                .setContentType(getMime())
                .setSize(getSize())
                .setThFilename(getThumbnailName())
                .setThUrl(getThumbnailFullPath())
                .setThContentType(getThumbnailMime())
                .setThSize(getThumbnailSize());
    }

}