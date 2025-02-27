package org.horizon.sdk.library.java.tool.lang.security;

import org.dromara.hutool.crypto.SecureUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * encrypt toolkit
 *
 * @author wjm
 * @since 2024-05-14 16:23
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Encrypts {

    /**
     * see {@link SecureUtil#md5(String)}
     *
     * @param content the content need to encrypt
     * @return after encrypt
     */
    public static String md5(String content) {
        return SecureUtil.md5(content);
    }

}