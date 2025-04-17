package org.horizon.sdk.library.java.tool.lang.text;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.dromara.hutool.core.data.IdcardUtil;

/**
 * @author wjm
 * @since 2025-04-17 17:40
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdentityCards {

    public static boolean isValid(String identityCard) {
        return IdcardUtil.isValidCard(identityCard);
    }

}