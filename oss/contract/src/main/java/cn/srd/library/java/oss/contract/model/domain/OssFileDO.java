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

    private String directoryPath;

    private String fullPath;

    private String name;

    private String originalName;

    private String extensionType;

    private String contentType;

    private Long size;

    public static OssFileDO from(FileInfo fileInfo) {
        return OssFileDO.builder()
                .platform(fileInfo.getPlatform())
                .directoryPath(SymbolConstant.SLASH + Strings.removeHeadTailSlash(fileInfo.getPath()))
                .fullPath(SymbolConstant.SLASH + Strings.removeHeadSlash(fileInfo.getUrl()))
                .name(fileInfo.getFilename())
                .originalName(fileInfo.getOriginalFilename())
                .extensionType(fileInfo.getExt())
                .contentType(fileInfo.getContentType())
                .size(fileInfo.getSize())
                .build();
    }

    public FileInfo toFileInfo() {
        return new FileInfo()
                .setPlatform(getPlatform())
                .setPath(Strings.removeHeadTailSlash(getDirectoryPath()) + SymbolConstant.SLASH)
                .setUrl(Strings.removeHeadSlash(getFullPath()))
                .setFilename(getName())
                .setOriginalFilename(getOriginalName())
                .setExt(getExtensionType())
                .setContentType(getContentType())
                .setSize(getSize());
    }

}