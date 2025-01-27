package cn.library.java.tool.id.uuid.support;

import cn.hutool.core.lang.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author wjm
 * @since 2024-05-30 11:05
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UUIDs {

    public static java.util.UUID get() {
        return java.util.UUID.randomUUID();
    }

    public static String getString() {
        return UUID.randomUUID().toString();
    }

    public static String fastGetString() {
        return UUID.fastUUID().toString();
    }

}