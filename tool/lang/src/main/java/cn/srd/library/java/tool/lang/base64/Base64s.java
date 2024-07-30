// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.base64;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Base64;

/**
 * @author wjm
 * @since 2024-07-30 14:22
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Base64s {

    public static byte[] toBytes(String base64) {
        return Base64.getDecoder().decode(base64);
    }

}