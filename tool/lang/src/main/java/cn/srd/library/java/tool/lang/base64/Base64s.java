package cn.srd.library.java.tool.lang.base64;

import cn.srd.library.java.tool.lang.object.Nil;
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
        return Nil.isBlank(base64) ? null : Base64.getDecoder().decode(base64);
    }

    public static String toString(byte[] data) {
        return Nil.isNull(data) ? null : Base64.getEncoder().encodeToString(data);
    }

}