package org.horizon.sdk.library.java.oss.api.test;

import org.horizon.sdk.library.java.oss.contract.Oss;
import org.horizon.sdk.library.java.oss.contract.model.domain.OssFileDO;
import org.horizon.sdk.library.java.oss.local.autoconfigure.EnableOssLocal;
import org.horizon.sdk.library.java.oss.minio.autoconfigure.EnableOssMinio;
import org.horizon.sdk.library.java.tool.lang.file.Files;
import org.horizon.sdk.library.java.tool.lang.text.Strings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;

/**
 * @author wjm
 * @since 2024-07-16 18:58
 */
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
                .progressListener((alreadyUploadSize, totalSize) -> System.out.println(Strings.format("upload already size：{}, total size: {}", alreadyUploadSize, totalSize)))
                .upload(file, "模型校验样张3.jpg", url);

        byte[] originalBytes = Oss.download(url, ossFileDO)
                .setProgressListener((currentDownSize, totalSize) -> System.out.println(Strings.format("current download original size：{}, total size: {}", currentDownSize, totalSize)))
                .bytes();

        byte[] thumbnailBytes = Oss.downloadThumbnail(url, ossFileDO)
                .setProgressListener((currentDownSize, totalSize) -> System.out.println(Strings.format("current download thumbnail size：{}, total size: {}", currentDownSize, totalSize)))
                .bytes();

        boolean isExistBeforeDelete = Oss.exist(url, ossFileDO);
        boolean isSuccess = Oss.delete(url, ossFileDO);
        boolean isExistAfterDelete = Oss.exist(url, ossFileDO);

        System.out.println();
    }

}