// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.file;

import cn.hutool.core.io.FileUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;

/**
 * toolkit for file
 *
 * @author wjm
 * @since 2020-04-02 17:52
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Files {

    /**
     * see {@link FileUtil#exist(File)}
     *
     * @param input the specified file
     * @return return true if {@link FileUtil#exist(File)}
     */
    public static boolean exist(File input) {
        return FileUtil.exist(input);
    }

    /**
     * see {@link FileUtil#del(File)}
     *
     * @param input the specified file
     * @return return true if {@link FileUtil#del(File)}
     */
    public static boolean delete(File input) {
        return FileUtil.del(input);

    }

}
