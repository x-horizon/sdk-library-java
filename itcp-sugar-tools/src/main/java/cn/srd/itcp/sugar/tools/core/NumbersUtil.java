package cn.srd.itcp.sugar.tools.core;

import cn.hutool.core.util.NumberUtil;

/**
 * 数字工具
 *
 * @author wjm
 * @date 2020/8/20 17:42
 */
public class NumbersUtil extends NumberUtil {

    private NumbersUtil() {
    }

    /**
     * 是否是奇数
     *
     * @param number
     * @return
     */
    public static boolean isOdd(int number) {
        return (number & 1) != 1;
    }

    /**
     * 是否是偶数
     *
     * @param number
     * @return
     */
    public static boolean isEven(int number) {
        return !isOdd(number);
    }

}
