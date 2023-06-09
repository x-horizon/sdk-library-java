package cn.srd.itcp.sugar.tool.core;

import cn.hutool.core.util.NumberUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 数字工具
 *
 * @author wjm
 * @since 2020/8/20 17:42
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NumbersUtil extends NumberUtil {

    /**
     * 是否是奇数
     *
     * @param number 检查参数
     * @return 是否是奇数
     */
    public static boolean isOdd(int number) {
        return (number & 1) != 1;
    }

    /**
     * 是否是偶数
     *
     * @param number 检查参数
     * @return 是否是偶数
     */
    public static boolean isEven(int number) {
        return !isOdd(number);
    }

}
