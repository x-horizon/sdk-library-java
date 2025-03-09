package org.horizon.sdk.library.java.oss.contract.model.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.dromara.x.file.storage.core.FileInfo;
import org.horizon.sdk.library.java.contract.constant.text.SymbolConstant;
import org.horizon.sdk.library.java.contract.model.base.DO;
import org.horizon.sdk.library.java.tool.lang.file.Files;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.text.Strings;

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
                .directoryPath(Nil.isBlank(fileInfo.getPath()) ? SymbolConstant.EMPTY : SymbolConstant.SLASH + Strings.removeHeadTailSlash(fileInfo.getPath()))
                .fullPath(Nil.isBlank(fileInfo.getUrl()) ? SymbolConstant.EMPTY : SymbolConstant.SLASH + Strings.removeHeadSlash(fileInfo.getUrl()))
                .extension(Nil.isBlank(fileInfo.getExt()) ? Files.getExtension(fileInfo.getFilename()) : fileInfo.getExt())
                .mime(fileInfo.getContentType())
                .size(fileInfo.getSize())
                .thumbnailName(fileInfo.getThFilename())
                .thumbnailFullPath(Nil.isBlank(fileInfo.getThUrl()) ? null : SymbolConstant.SLASH + Strings.removeHeadSlash(fileInfo.getThUrl()))
                .thumbnailMime(fileInfo.getThContentType())
                .thumbnailSize(fileInfo.getThSize())
                .build();
    }

    public FileInfo toFileInfo() {
        return new FileInfo()
                .setPlatform(getPlatform())
                .setFilename(getName())
                .setOriginalFilename(getOriginalName())
                .setPath(Nil.isBlank(getDirectoryPath()) ? SymbolConstant.EMPTY : Strings.removeHeadTailSlash(getDirectoryPath()) + SymbolConstant.SLASH)
                .setUrl(Nil.isBlank(getFullPath()) ? SymbolConstant.EMPTY : Strings.removeHeadSlash(getFullPath()))
                .setExt(getExtension())
                .setContentType(getMime())
                .setSize(getSize())
                .setThFilename(getThumbnailName())
                .setThUrl(getThumbnailFullPath())
                .setThContentType(getThumbnailMime())
                .setThSize(getThumbnailSize());
    }

}