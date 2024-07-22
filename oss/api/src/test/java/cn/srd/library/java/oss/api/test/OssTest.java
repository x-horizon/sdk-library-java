// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.oss.api.test;

import cn.srd.library.java.contract.constant.suppress.SuppressWarningConstant;
import cn.srd.library.java.oss.contract.Oss;
import cn.srd.library.java.oss.contract.model.domain.OssFileDO;
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
@SuppressWarnings(SuppressWarningConstant.PREVIEW)
@EnableOssLocal
@EnableOssMinio
@ExtendWith(SpringExtension.class)
@SpringBootTest
class OssTest {

    @Test
    void test() {
        File file = Files.of("/Users/jimmy/Desktop/场景照片/模型校验样张.jpg");
        String url = "minio:///wjm-test2/wjm10/test4?bucketName=wjm-test";

        OssFileDO ossFileDO = Oss.openUpload()
                .scale()
                .thumbnail()
                .progressListener((alreadyUploadSize, totalSize) -> System.out.println(STR."upload already size：\{alreadyUploadSize}, total size: \{totalSize}"))
                .upload(file, "模型校验样张3.jpg", url);

        byte[] originalBytes = Oss.download(url, ossFileDO)
                .setProgressListener((currentDownSize, totalSize) -> System.out.println(STR."current download original size：\{currentDownSize}, total size: \{totalSize}"))
                .bytes();

        byte[] thumbnailBytes = Oss.downloadThumbnail(url, ossFileDO)
                .setProgressListener((currentDownSize, totalSize) -> System.out.println(STR."current download thumbnail size：\{currentDownSize}, total size: \{totalSize}"))
                .bytes();

        boolean isExistBeforeDelete = Oss.exist(url, ossFileDO);
        boolean isSuccess = Oss.delete(url, ossFileDO);
        boolean isExistAfterDelete = Oss.exist(url, ossFileDO);

        System.out.println();
    }

}