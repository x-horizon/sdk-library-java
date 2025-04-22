package org.horizon.sdk.library.java.tool.lang.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.dromara.hutool.crypto.digest.BCrypt;

/**
 * @author wjm
 * @since 2025-04-22 16:31
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BCrypts {

    public static String encrypt(String content) {
        return BCrypt.hashpw(content, BCrypt.gensalt());
    }

    public static String encrypt(String content, int numberOfRound) {
        return BCrypt.hashpw(content, BCrypt.gensalt(numberOfRound));
    }

    public static boolean check(String rawContent, String afterEncryptContent) {
        return BCrypt.checkpw(rawContent, afterEncryptContent);
    }

}