// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.oss.api.test;

import cn.srd.library.java.oss.contract.model.domain.OssFileDO;
import cn.srd.library.java.oss.contract.model.enums.OssType;
import cn.srd.library.java.oss.local.autoconfigure.EnableOssLocal;
import cn.srd.library.java.oss.minio.autoconfigure.EnableOssMinio;
import cn.srd.library.java.tool.lang.file.Files;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;

/**
 * @author wjm
 * @since 2024-07-16 18:58
 */
// @EnableFileStorage
@EnableOssLocal
@EnableOssMinio
@ExtendWith(SpringExtension.class)
@SpringBootTest
class OssTest {

    @Test
    void test() {
        File file = Files.of("/Users/jimmy/Desktop/v2.6-refactor/bit-land-se5-video-resource-verify-v1.0（deprecated）.zip");

        OssFileDO ossFileDO = OssType.MINIO.getStorage().put(file, "/wjm/test/upload", file.getName());

        byte[] bytes = OssType.MINIO.getStorage().get(ossFileDO.toFileInfo())
                .setProgressListener(progressSize ->
                        System.out.println("download progress：" + progressSize)
                )
                .bytes();

        System.out.println();
    }

}