package org.horizon.library.java.tool.lang.random;

import org.dromara.hutool.core.util.RandomUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * random toolkit
 *
 * @author wjm
 * @since 2021-04-26 16:48
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Randoms {

    /**
     * see {@link RandomUtil#randomNumber()}
     *
     * @return random number
     */
    public static char getNumber() {
        return RandomUtil.randomNumber();
    }

    /**
     * see {@link RandomUtil#randomString(int)}
     *
     * @return random string
     */
    public static String getString(int length) {
        return RandomUtil.randomString(length);
    }

}