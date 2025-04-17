package org.horizon.sdk.library.java.tool.lang.text;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.dromara.hutool.core.data.PhoneUtil;

/**
 * @author wjm
 * @since 2025-04-17 17:42
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Phones {

    public static boolean isValid(String phone) {
        return PhoneUtil.isPhone(phone);
    }

}