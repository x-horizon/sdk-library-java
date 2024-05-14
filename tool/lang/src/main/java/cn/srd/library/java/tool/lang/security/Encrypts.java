// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.security;

import cn.hutool.crypto.SecureUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * encrypt toolkit
 *
 * @author wjm
 * @since 2024-05-14 22:36
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Encrypts {

    /**
     * see {@link SecureUtil#md5(String)}
     *
     * @param content the content to encrypt
     * @return after encrypt
     */
    public static String md5(String content) {
        return SecureUtil.md5(content);
    }

}