// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.oss.minio;

import cn.srd.library.java.contract.constant.text.SymbolConstant;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.text.Strings;
import cn.srd.library.java.tool.spring.contract.support.Springs;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;

/**
 * @author wjm
 * @since 2024-07-17 11:55
 */
@Setter
@Accessors(chain = true)
public class OssMinio {

    private String path;

    private String filename;

    private Object file;

    public FileInfo upload() {
        return Springs.getBean(FileStorageService.class).of(this.file)
                .setPath(Nil.isBlank(this.path) ? SymbolConstant.EMPTY : Strings.removeHeadTailSlash(this.path) + SymbolConstant.SLASH)
                .setSaveFilename(this.filename)
                .upload();
    }

}