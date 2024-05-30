// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.id.uuid.support;

import cn.hutool.core.lang.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author wjm
 * @since 2024-05-30 11:05
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UUIDs {

    public static String get() {
        return UUID.randomUUID().toString();
    }

    public static String fastGet() {
        return UUID.fastUUID().toString();
    }

}