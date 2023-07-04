package cn.srd.itcp.sugar.tool.core;

import cn.hutool.core.util.NumberUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.RoundingMode;

/**
 * 数学工具
 *
 * @author wjm
 * @since 2020/8/20 17:42
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NumbersUtil extends NumberUtil {

    /**
     * the scale of decimal places: 0
     */
    private static final int ZERO_SCALE = 0;

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

    /**
     * see {@link #add(String...)}, this function will return an int value after half adjust
     * <pre>
     * example:
     * 10 + 0.1 = 10.1         => 10
     * 10 + 0.5 = 10.5         => 11
     * 10 + 0.6 = 10.6         => 11
     * </pre>
     *
     * @param input1 addend1
     * @param input2 addend2
     * @return an int value after half adjust
     */
    public static int addInt(double input1, double input2) {
        return add(Double.toString(input1), Double.toString(input2)).setScale(ZERO_SCALE, RoundingMode.HALF_UP).intValue();
    }

    /**
     * see {@link #addInt(double, double)}
     * <pre>
     * example:
     * 10 + 0.1 = 10.1         => 10
     * 10 + 0.5 = 10.5         => 11
     * 10 + 0.6 = 10.6         => 11
     * </pre>
     *
     * @param input1 addend1
     * @param input2 addend2
     * @return an long value after half adjust
     */
    public static long addLong(double input1, double input2) {
        return add(Double.toString(input1), Double.toString(input2)).setScale(ZERO_SCALE, RoundingMode.HALF_UP).longValue();
    }

    /**
     * see {@link #sub(String...)}, this function will return an int value after half adjust
     * <pre>
     * example:
     * 10 - 0.1 = 9.9          => 10
     * 10 - 0.5 = 9.5          => 10
     * 10 - 0.6 = 9.4          => 9
     * </pre>
     *
     * @param input1 subtract1
     * @param input2 subtract2
     * @return an int value after half adjust
     */
    public static int subInt(double input1, double input2) {
        return sub(Double.toString(input1), Double.toString(input2)).setScale(ZERO_SCALE, RoundingMode.HALF_UP).intValue();
    }

    /**
     * see {@link #subInt(double, double)}
     *
     * @param input1 subtract1
     * @param input2 subtract2
     * @return an long value after half adjust
     */
    public static long subLong(double input1, double input2) {
        return sub(Double.toString(input1), Double.toString(input2)).setScale(ZERO_SCALE, RoundingMode.HALF_UP).longValue();
    }

    /**
     * see {@link #mul(String, String)}, this function will return an int value after half adjust
     * <pre>
     * example:
     * 10     * 0.8 = 8        => 8
     * 13     * 0.8 = 10.4     => 10
     * 13.125 * 0.8 = 10.5     => 11
     * 11     * 0.8 = 8.8      => 9
     * </pre>
     *
     * @param input1 multiplier1
     * @param input2 multiplier2
     * @return an int value after half adjust
     */
    public static int mulInt(float input1, double input2) {
        return mul(Float.toString(input1), Double.toString(input2)).setScale(ZERO_SCALE, RoundingMode.HALF_UP).intValue();
    }

    /**
     * see {@link #mulInt(float, double)}
     *
     * @param input1 multiplier1
     * @param input2 multiplier2
     * @return a long value after half adjust
     */
    public static long mulLong(float input1, double input2) {
        return mul(Float.toString(input1), Double.toString(input2)).setScale(ZERO_SCALE, RoundingMode.HALF_UP).longValue();
    }

    /**
     * see {@link #div(String, String)}, this function will return an int value after half adjust
     * <pre>
     * example:
     * 100 / 10  = 10          => 10
     * 100 / 12  = 8.33        => 8
     * 100 / 8   = 12.5        => 13
     * 100 / 13  = 7.69        => 8
     * </pre>
     *
     * @param input1 divisor1
     * @param input2 divisor2
     * @return an int value after half adjust
     */
    public static int divInt(double input1, double input2) {
        return div(Double.toString(input1), Double.toString(input2)).setScale(ZERO_SCALE, RoundingMode.HALF_UP).intValue();
    }

    /**
     * see {@link #divInt(double, double)}
     *
     * @param input1 divisor1
     * @param input2 divisor2
     * @return an int value after half adjust
     */
    public static long divLong(double input1, double input2) {
        return div(Double.toString(input1), Double.toString(input2)).setScale(ZERO_SCALE, RoundingMode.HALF_UP).longValue();
    }

}
