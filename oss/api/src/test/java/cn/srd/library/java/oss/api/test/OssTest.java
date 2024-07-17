// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.oss.api.test;

import cn.srd.library.java.oss.api.Oss;
import cn.srd.library.java.oss.minio.autoconfigure.EnableOssMinio;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.FileWriter;

/**
 * @author wjm
 * @since 2024-07-16 18:58
 */
// @EnableFileStorage
@EnableOssMinio
@ExtendWith(SpringExtension.class)
@SpringBootTest
class OssTest {

    @Autowired private FileStorageService fileStorageService;

    @SneakyThrows
    @Test
    void test() {
        File file = new File("newFile.txt");
        @Cleanup FileWriter writer = new FileWriter(file);
        writer.write("test");

        FileInfo fileInfo = Oss.onMinio().setFile(file).setFilename("newFile23123123.txt").setPath("/wjm/test/upload").upload();

        System.out.println();
    }

}