package cn.srd.itcp.sugar.tool.core;

import cn.hutool.core.util.NumberUtil;
import cn.srd.itcp.sugar.tool.constant.TimePool;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
     * one hundred
     */
    private static final int ONE_HUNDRED = 100;

    /**
     * one thousand
     */
    private static final int ONE_THOUSAND = 1000;

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
     * kilometer per hour to meter per second
     *
     * @param input kilometer per hour
     * @return meter per second
     */
    public static double kilometersPerHourToMeterPerSecond(long input) {
        return div(mul(input, ONE_THOUSAND), TimePool.ONE_HOUR_SECOND);
    }

    /**
     * get percent
     *
     * @param input original number
     * @return number after percent
     */
    public static double getPercent(short input) {
        return NumbersUtil.div(input, ONE_HUNDRED);
    }

    /**
     * see {@link #getPercent(short)}
     *
     * @param input original number
     * @return number after percent
     */
    public static double getPercent(double input) {
        return NumbersUtil.div(input, ONE_HUNDRED);
    }

    /**
     * see {@link #addHalfToBigDecimal(double, double)}
     *
     * @param input1 addend1
     * @param input2 addend2
     * @return number after half adjust
     */
    public static int addHalfToShort(double input1, double input2) {
        return addHalfToBigDecimal(input1, input2).shortValue();
    }

    /**
     * see {@link #addHalfToBigDecimal(double, double)}
     *
     * @param input1 addend1
     * @param input2 addend2
     * @return number after half adjust
     */
    public static int addHalfToInt(double input1, double input2) {
        return addHalfToBigDecimal(input1, input2).intValue();
    }

    /**
     * see {@link #addHalfToBigDecimal(double, double)}
     * <pre>
     * example:
     * 10 + 0.1 = 10.1         => 10
     * 10 + 0.5 = 10.5         => 11
     * 10 + 0.6 = 10.6         => 11
     * </pre>
     *
     * @param input1 addend1
     * @param input2 addend2
     * @return number after half adjust
     */
    public static long addHalfToLong(double input1, double input2) {
        return addHalfToBigDecimal(input1, input2).longValue();
    }

    /**
     * see {@link #add(String...)}, this function will return number after half adjust
     * <pre>
     * example:
     * 10 + 0.1 = 10.1         => 10
     * 10 + 0.5 = 10.5         => 11
     * 10 + 0.6 = 10.6         => 11
     * </pre>
     *
     * @param input1 addend1
     * @param input2 addend2
     * @return number after half adjust
     */
    public static BigDecimal addHalfToBigDecimal(double input1, double input2) {
        return add(Double.toString(input1), Double.toString(input2)).setScale(ZERO_SCALE, RoundingMode.HALF_UP);
    }

    /**
     * see {@link #subHalfToBigDecimal(double, double)}
     *
     * @param input1 subtract1
     * @param input2 subtract2
     * @return number after half adjust
     */
    public static int subHalfToShort(double input1, double input2) {
        return subHalfToBigDecimal(input1, input2).shortValue();
    }

    /**
     * see {@link #subHalfToBigDecimal(double, double)}
     *
     * @param input1 subtract1
     * @param input2 subtract2
     * @return number after half adjust
     */
    public static int subHalfToInt(double input1, double input2) {
        return subHalfToBigDecimal(input1, input2).intValue();
    }

    /**
     * see {@link #subHalfToBigDecimal(double, double)}
     *
     * @param input1 subtract1
     * @param input2 subtract2
     * @return number after half adjust
     */
    public static long subHalfToLong(double input1, double input2) {
        return subHalfToBigDecimal(input1, input2).longValue();
    }

    /**
     * see {@link #sub(String...)}, this function will return number after half adjust
     * <pre>
     * example:
     * 10 - 0.1 = 9.9          => 10
     * 10 - 0.5 = 9.5          => 10
     * 10 - 0.6 = 9.4          => 9
     * </pre>
     *
     * @param input1 subtract1
     * @param input2 subtract2
     * @return number after half adjust
     */
    public static BigDecimal subHalfToBigDecimal(double input1, double input2) {
        return sub(Double.toString(input1), Double.toString(input2)).setScale(ZERO_SCALE, RoundingMode.HALF_UP);
    }

    /**
     * see {@link #mulHalfToBigDecimal(float, double)}
     *
     * @param input1 multiplier1
     * @param input2 multiplier2
     * @return number after half adjust
     */
    public static int mulHalfToShort(float input1, double input2) {
        return mulHalfToBigDecimal(input1, input2).shortValue();
    }

    /**
     * see {@link #mulHalfToBigDecimal(float, double)}
     *
     * @param input1 multiplier1
     * @param input2 multiplier2
     * @return number after half adjust
     */
    public static int mulHalfToInt(float input1, double input2) {
        return mulHalfToBigDecimal(input1, input2).intValue();
    }

    /**
     * see {@link #mulHalfToBigDecimal(float, double)}
     *
     * @param input1 multiplier1
     * @param input2 multiplier2
     * @return number after half adjust
     */
    public static long mulHalfToLong(float input1, double input2) {
        return mulHalfToBigDecimal(input1, input2).longValue();
    }

    /**
     * see {@link #mul(String, String)}, this function will return number after half adjust
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
     * @return number after half adjust
     */
    public static BigDecimal mulHalfToBigDecimal(float input1, double input2) {
        return mul(Float.toString(input1), Double.toString(input2)).setScale(ZERO_SCALE, RoundingMode.HALF_UP);
    }

    /**
     * see {@link #divHalfToBigDecimal(double, double)}
     *
     * @param input1 divisor1
     * @param input2 divisor2
     * @return number after half adjust
     */
    public static int divHalfToShort(double input1, double input2) {
        return divHalfToBigDecimal(input1, input2).shortValue();
    }

    /**
     * see {@link #divHalfToBigDecimal(double, double)}
     *
     * @param input1 divisor1
     * @param input2 divisor2
     * @return number after half adjust
     */
    public static int divHalfToInt(double input1, double input2) {
        return divHalfToBigDecimal(input1, input2).intValue();
    }

    /**
     * see {@link #divHalfToBigDecimal(double, double)}
     *
     * @param input1 divisor1
     * @param input2 divisor2
     * @return number after half adjust
     */
    public static long divHalfToLong(double input1, double input2) {
        return divHalfToBigDecimal(input1, input2).longValue();
    }

    /**
     * see {@link #div(String, String)}, this function will return number after half adjust
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
     * @return number after half adjust
     */
    public static BigDecimal divHalfToBigDecimal(double input1, double input2) {
        return div(Double.toString(input1), Double.toString(input2)).setScale(ZERO_SCALE, RoundingMode.HALF_UP);
    }

}
