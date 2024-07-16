// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.oss.all.test;

import cn.srd.library.java.oss.minio.autoconfigure.EnableOssMinio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author wjm
 * @since 2024-07-16 18:58
 */
// @EnableFileStorage
@EnableOssMinio
@ExtendWith(SpringExtension.class)
@SpringBootTest
class OssTest {

    @Test
    void test() {
    }

}