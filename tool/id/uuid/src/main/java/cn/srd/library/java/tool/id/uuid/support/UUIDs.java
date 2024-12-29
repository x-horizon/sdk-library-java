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