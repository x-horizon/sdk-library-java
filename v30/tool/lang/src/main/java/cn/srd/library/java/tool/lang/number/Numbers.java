// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.number;

import cn.hutool.core.util.NumberUtil;
import cn.srd.library.java.contract.constant.number.NumberConstant;
import cn.srd.library.java.contract.constant.time.TimeUnitConstant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * toolkit for number
 *
 * @author wjm
 * @since 2020-08-20 17:42
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Numbers {

    private static final int DEFAULT_DIV_SCALE = 10;

    /**
     * return true if the checked element is odd
     *
     * @param input the checked element
     * @return return true if the checked element is odd
     */
    public static boolean isOdd(int input) {
        return NumberUtil.isOdd(input);
    }

    /**
     * return true if the checked element is even
     *
     * @param input the checked element
     * @return return true if the checked element is even
     */
    public static boolean isEven(int input) {
        return !isOdd(input);
    }

    /**
     * kilometer per hour to meter per second
     *
     * @param input kilometer per hour
     * @return meter per second
     */
    public static double kilometersPerHourToMeterPerSecond(long input) {
        return NumberUtil.div(NumberUtil.mul(input, NumberConstant.ONE_THOUSAND), TimeUnitConstant.ONE_HOUR_SECOND);
    }

    /**
     * get percent
     *
     * @param input original number
     * @return number after percent
     */
    public static double getPercent(short input) {
        return NumberUtil.div(input, NumberConstant.ONE_HUNDRED);
    }

    /**
     * see {@link #getPercent(short)}
     *
     * @param input original number
     * @return number after percent
     */
    public static double getPercent(double input) {
        return NumberUtil.div(input, NumberConstant.ONE_HUNDRED);
    }

    /**
     * see {@link #roundWithScale0ToBigDecimal(double)}
     *
     * @param input input number
     * @return number after half adjust with 0 scale
     */
    public static short roundWithScale0ToShort(double input) {
        return roundWithScale0ToBigDecimal(input).shortValue();
    }

    /**
     * see {@link #roundWithScale0ToBigDecimal(double)}
     *
     * @param input input number
     * @return number after half adjust with 0 scale
     */
    public static int roundWithScale0ToInt(double input) {
        return roundWithScale0ToBigDecimal(input).intValue();
    }

    /**
     * see {@link #roundWithScale0ToBigDecimal(double)}
     *
     * @param input input number
     * @return number after half adjust with 0 scale
     */
    public static long roundWithScale0ToLong(double input) {
        return roundWithScale0ToBigDecimal(input).longValue();
    }

    /**
     * this function will return number after half adjust with 0 scale
     * <pre>
     * example:
     * 10           => 10
     * 10.1         => 10
     * 10.5         => 11
     * 10.6         => 11
     * </pre>
     *
     * @param input input number
     * @return number after half adjust with 0 scale
     */
    public static BigDecimal roundWithScale0ToBigDecimal(double input) {
        return NumberUtil.round(input, NumberConstant.ZERO_SCALE);
    }

    /**
     * see {@link #addRoundWithScale0ToBigDecimal(double, double)}
     *
     * @param input1 addend1
     * @param input2 addend2
     * @return number after half adjust with 0 scale
     */
    public static int addRoundWithScale0ToShort(double input1, double input2) {
        return addRoundWithScale0ToBigDecimal(input1, input2).shortValue();
    }

    /**
     * see {@link #addRoundWithScale0ToBigDecimal(double, double)}
     *
     * @param input1 addend1
     * @param input2 addend2
     * @return number after half adjust with 0 scale
     */
    public static int addRoundWithScale0ToInt(double input1, double input2) {
        return addRoundWithScale0ToBigDecimal(input1, input2).intValue();
    }

    /**
     * see {@link #addRoundWithScale0ToBigDecimal(double, double)}
     * <pre>
     * example:
     * 10 + 0.1 = 10.1         => 10
     * 10 + 0.5 = 10.5         => 11
     * 10 + 0.6 = 10.6         => 11
     * </pre>
     *
     * @param input1 addend1
     * @param input2 addend2
     * @return number after half adjust with 0 scale
     */
    public static long addRoundWithScale0ToLong(double input1, double input2) {
        return addRoundWithScale0ToBigDecimal(input1, input2).longValue();
    }

    /**
     * see {@link NumberUtil#add(String...)}, this function will return number after half adjust with 0 scale
     * <pre>
     * example:
     * 10 + 0.1 = 10.1         => 10
     * 10 + 0.5 = 10.5         => 11
     * 10 + 0.6 = 10.6         => 11
     * </pre>
     *
     * @param input1 addend1
     * @param input2 addend2
     * @return number after half adjust with 0 scale
     */
    public static BigDecimal addRoundWithScale0ToBigDecimal(double input1, double input2) {
        return NumberUtil.add(Double.toString(input1), Double.toString(input2)).setScale(NumberConstant.ZERO_SCALE, RoundingMode.HALF_UP);
    }

    /**
     * see {@link #subRoundWithScale0ToBigDecimal(double, double)}
     *
     * @param input1 subtract1
     * @param input2 subtract2
     * @return number after half adjust with 0 scale
     */
    public static int subRoundWithScale0ToShort(double input1, double input2) {
        return subRoundWithScale0ToBigDecimal(input1, input2).shortValue();
    }

    /**
     * see {@link #subRoundWithScale0ToBigDecimal(double, double)}
     *
     * @param input1 subtract1
     * @param input2 subtract2
     * @return number after half adjust with 0 scale
     */
    public static int subRoundWithScale0ToInt(double input1, double input2) {
        return subRoundWithScale0ToBigDecimal(input1, input2).intValue();
    }

    /**
     * see {@link #subRoundWithScale0ToBigDecimal(double, double)}
     *
     * @param input1 subtract1
     * @param input2 subtract2
     * @return number after half adjust with 0 scale
     */
    public static long subRoundWithScale0ToLong(double input1, double input2) {
        return subRoundWithScale0ToBigDecimal(input1, input2).longValue();
    }

    /**
     * see {@link NumberUtil#sub(String...)}, this function will return number after half adjust with 0 scale
     * <pre>
     * example:
     * 10 - 0.1 = 9.9          => 10
     * 10 - 0.5 = 9.5          => 10
     * 10 - 0.6 = 9.4          => 9
     * </pre>
     *
     * @param input1 subtract1
     * @param input2 subtract2
     * @return number after half adjust with 0 scale
     */
    public static BigDecimal subRoundWithScale0ToBigDecimal(double input1, double input2) {
        return NumberUtil.sub(Double.toString(input1), Double.toString(input2)).setScale(NumberConstant.ZERO_SCALE, RoundingMode.HALF_UP);
    }

    /**
     * see {@link #mulRoundWithScale0ToBigDecimal(float, double)}
     *
     * @param input1 multiplier1
     * @param input2 multiplier2
     * @return number after half adjust with 0 scale
     */
    public static int mulRoundWithScale0ToShort(float input1, double input2) {
        return mulRoundWithScale0ToBigDecimal(input1, input2).shortValue();
    }

    /**
     * see {@link #mulRoundWithScale0ToBigDecimal(float, double)}
     *
     * @param input1 multiplier1
     * @param input2 multiplier2
     * @return number after half adjust with 0 scale
     */
    public static int mulRoundWithScale0ToInt(float input1, double input2) {
        return mulRoundWithScale0ToBigDecimal(input1, input2).intValue();
    }

    /**
     * see {@link #mulRoundWithScale0ToBigDecimal(float, double)}
     *
     * @param input1 multiplier1
     * @param input2 multiplier2
     * @return number after half adjust with 0 scale
     */
    public static long mulRoundWithScale0ToLong(float input1, double input2) {
        return mulRoundWithScale0ToBigDecimal(input1, input2).longValue();
    }

    /**
     * see {@link NumberUtil#mul(String, String)}, this function will return number after half adjust with 0 scale
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
     * @return number after half adjust with 0 scale
     */
    public static BigDecimal mulRoundWithScale0ToBigDecimal(float input1, double input2) {
        return NumberUtil.mul(Float.toString(input1), Double.toString(input2)).setScale(NumberConstant.ZERO_SCALE, RoundingMode.HALF_UP);
    }

    /**
     * see {@link #divRoundWithScale0ToBigDecimal(double, double)}
     *
     * @param input1 divisor1
     * @param input2 divisor2
     * @return number after half adjust with 0 scale
     */
    public static int divRoundWithScale0ToShort(double input1, double input2) {
        return divRoundWithScale0ToBigDecimal(input1, input2).shortValue();
    }

    /**
     * see {@link #divRoundWithScale0ToBigDecimal(double, double)}
     *
     * @param input1 divisor1
     * @param input2 divisor2
     * @return number after half adjust with 0 scale
     */
    public static int divRoundWithScale0ToInt(double input1, double input2) {
        return divRoundWithScale0ToBigDecimal(input1, input2).intValue();
    }

    /**
     * see {@link #divRoundWithScale0ToBigDecimal(double, double)}
     *
     * @param input1 divisor1
     * @param input2 divisor2
     * @return number after half adjust with 0 scale
     */
    public static long divRoundWithScale0ToLong(double input1, double input2) {
        return divRoundWithScale0ToBigDecimal(input1, input2).longValue();
    }

    /**
     * see {@link NumberUtil#div(String, String)}, this function will return number after half adjust with 0 scale
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
     * @return number after half adjust with 0 scale
     */
    public static BigDecimal divRoundWithScale0ToBigDecimal(double input1, double input2) {
        return NumberUtil.div(Double.toString(input1), Double.toString(input2)).setScale(NumberConstant.ZERO_SCALE, RoundingMode.HALF_UP);
    }

}
