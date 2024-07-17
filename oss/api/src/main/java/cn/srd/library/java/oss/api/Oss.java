// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.oss.api;

import cn.srd.library.java.oss.minio.OssMinio;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author wjm
 * @since 2024-07-17 11:47
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Oss {

    public static OssMinio onMinio() {
        return new OssMinio();
    }

}