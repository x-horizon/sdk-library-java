package org.horizon.library.java.tool.lang.number;

import org.horizon.library.java.contract.constant.number.NumberConstant;
import org.horizon.library.java.contract.constant.time.TimeUnitConstant;
import org.horizon.library.java.contract.model.throwable.InvalidArgumentException;
import org.horizon.library.java.tool.lang.object.Nil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.dromara.hutool.core.math.NumberUtil;

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
     * return true if the string is {@link Integer}
     *
     * @param input the checked element
     * @return return true if the string is {@link Integer}
     * @see NumberUtil#isInteger(String)
     */
    public static boolean isInteger(String input) {
        return NumberUtil.isInteger(input);
    }

    /**
     * return true if the string is {@link Long}
     *
     * @param input the checked element
     * @return return true if the string is {@link Long}
     * @see NumberUtil#isLong(String)
     */
    public static boolean isLong(String input) {
        return NumberUtil.isLong(input);
    }

    /**
     * return true if the string is number
     *
     * @param input the checked element
     * @return return true if the string is number
     * @see NumberUtil#isNumber(CharSequence)
     */
    public static boolean isNumber(CharSequence input) {
        return NumberUtil.isNumber(input);
    }

    /**
     * return true if the checked element is not {@code null} and {@code > 0}
     *
     * @param input the checked element
     * @return return true if the checked element is not {@code null} and {@code > 0}
     */
    public static boolean isPositive(Number input) {
        return Nil.isNotNull(input) && input.longValue() > NumberConstant.ZERO_LONG_VALUE;
    }

    /**
     * return true if any checked element is not {@code null} and {@code > 0}
     *
     * @param inputs the checked elements
     * @return return true if any checked element is not {@code null} and {@code > 0}
     */
    public static boolean isAnyPositive(Number... inputs) {
        if (Nil.isNull(inputs)) {
            return false;
        }
        for (Number number : inputs) {
            if (isPositive(number)) {
                return true;
            }
        }
        return false;
    }

    /**
     * return true if all checked elements are not {@code null} and {@code > 0}
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are not {@code null} and {@code > 0}
     */
    public static boolean isAllPositive(Number... inputs) {
        if (Nil.isNull(inputs)) {
            return false;
        }
        for (Number number : inputs) {
            if (isNotPositive(number)) {
                return false;
            }
        }
        return true;
    }

    /**
     * return true if any checked element is not {@code null} and {@code <= 0}
     *
     * @param input the checked element
     * @return return true if the checked element is not {@code null} and {@code <= 0}
     */
    public static boolean isNotPositive(Number input) {
        return Nil.isNotNull(input) && input.longValue() <= NumberConstant.ZERO_LONG_VALUE;
    }

    /**
     * return true if all checked elements are not {@code null} and {@code > 0}
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are not {@code null} and {@code > 0}
     */
    public static boolean isAllNotPositive(Number... inputs) {
        return !isAnyPositive(inputs);
    }

    /**
     * kilometer per hour to meter per second
     *
     * @param input kilometer per hour
     * @return meter per second
     */
    public static double kilometersPerHourToMeterPerSecond(long input) {
        return NumberUtil.div(NumberUtil.mul(input, NumberConstant.ONE_THOUSAND), TimeUnitConstant.ONE_HOUR_SECOND).doubleValue();
    }

    /**
     * get percent
     *
     * @param input original number
     * @return number after percent
     */
    public static double getPercent(short input) {
        return NumberUtil.div(input, NumberConstant.ONE_HUNDRED).doubleValue();
    }

    /**
     * see {@link #getPercent(short)}
     *
     * @param input original number
     * @return number after percent
     */
    public static double getPercent(double input) {
        return NumberUtil.div(input, NumberConstant.ONE_HUNDRED).doubleValue();
    }

    /**
     * return the value of adding originalValue and increaseValue together
     *
     * @param originalValue original value
     * @param increaseValue the value to increase
     * @return the value of adding originalValue and increaseValue together
     */
    public static Long add(Long originalValue, Long increaseValue) {
        return originalValue + increaseValue;
    }

    /**
     * add one
     *
     * @param input the value to + 1
     * @return after add one
     */
    public static Number addOne(Number input) {
        return switch (input) {
            case Integer _, Long _, Short _, Byte _ -> input.longValue() + 1;
            case Double _, Float _ -> new BigDecimal(input.toString()).add(BigDecimal.ONE).doubleValue();
            default -> throw new InvalidArgumentException("unsupported number type to self reduce!");
        };
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
        return NumberUtil.add(Double.toString(input1), Double.toString(input2)).setScale(NumberConstant.ZERO_INT_SCALE, RoundingMode.HALF_UP);
    }

    /**
     * return the value of subtracting originalValue and increaseValue together
     *
     * @param originalValue original value
     * @param reduceValue   the value to subtract
     * @return the value of subtracting originalValue and increaseValue together
     */
    public static Long subtract(Long originalValue, Long reduceValue) {
        return originalValue - reduceValue;
    }

    /**
     * return the value of subtracting originalValue and increaseValue together and after {@link #subtract(Long, Long)} value is not less than 0
     *
     * @param originalValue original value
     * @param reduceValue   the value to subtract
     * @return the value of subtracting originalValue and increaseValue together and after {@link #subtract(Long, Long)} value is not less than 0
     */
    public static Long subtractNotLessThanZero(Long originalValue, Long reduceValue) {
        Long output = subtract(originalValue, reduceValue);
        if (isNotPositive(output)) {
            output = NumberConstant.ZERO_LONG_VALUE;
        }
        return output;
    }

    /**
     * subtract one
     *
     * @param input the value to - 1
     * @return after subtract one
     */
    public static Number subtractOne(Number input) {
        return switch (input) {
            case Integer _, Long _, Short _, Byte _ -> input.longValue() - 1;
            case Double _, Float _ -> new BigDecimal(input.toString()).subtract(BigDecimal.ONE).doubleValue();
            default -> throw new InvalidArgumentException("unsupported number type to self reduce!");
        };
    }

    /**
     * subtract one and after {@link #subtractOne(Number)} value is not less than 0
     *
     * @param input the value to - 1
     * @return after subtract one
     */
    public static Number subtractOneNotLessThanZero(Number input) {
        Number output = subtractOne(input);
        if (isNotPositive(output)) {
            output = NumberConstant.ZERO_LONG_VALUE;
        }
        return output;
    }

    /**
     * see {@link #subtractRoundWithScale0ToBigDecimal(double, double)}
     *
     * @param input1 subtract1
     * @param input2 subtract2
     * @return number after half adjust with 0 scale
     */
    public static int subtractRoundWithScale0ToShort(double input1, double input2) {
        return subtractRoundWithScale0ToBigDecimal(input1, input2).shortValue();
    }

    /**
     * see {@link #subtractRoundWithScale0ToBigDecimal(double, double)}
     *
     * @param input1 subtract1
     * @param input2 subtract2
     * @return number after half adjust with 0 scale
     */
    public static int subtractRoundWithScale0ToInt(double input1, double input2) {
        return subtractRoundWithScale0ToBigDecimal(input1, input2).intValue();
    }

    /**
     * see {@link #subtractRoundWithScale0ToBigDecimal(double, double)}
     *
     * @param input1 subtract1
     * @param input2 subtract2
     * @return number after half adjust with 0 scale
     */
    public static long subtractRoundWithScale0ToLong(double input1, double input2) {
        return subtractRoundWithScale0ToBigDecimal(input1, input2).longValue();
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
    public static BigDecimal subtractRoundWithScale0ToBigDecimal(double input1, double input2) {
        return NumberUtil.sub(Double.toString(input1), Double.toString(input2)).setScale(NumberConstant.ZERO_INT_SCALE, RoundingMode.HALF_UP);
    }

    /**
     * see {@link #multiplyRoundWithScale0ToBigDecimal(float, double)}
     *
     * @param input1 multiplier1
     * @param input2 multiplier2
     * @return number after half adjust with 0 scale
     */
    public static int multiplyRoundWithScale0ToShort(float input1, double input2) {
        return multiplyRoundWithScale0ToBigDecimal(input1, input2).shortValue();
    }

    /**
     * see {@link #multiplyRoundWithScale0ToBigDecimal(float, double)}
     *
     * @param input1 multiplier1
     * @param input2 multiplier2
     * @return number after half adjust with 0 scale
     */
    public static int multiplyRoundWithScale0ToInt(float input1, double input2) {
        return multiplyRoundWithScale0ToBigDecimal(input1, input2).intValue();
    }

    /**
     * see {@link #multiplyRoundWithScale0ToBigDecimal(float, double)}
     *
     * @param input1 multiplier1
     * @param input2 multiplier2
     * @return number after half adjust with 0 scale
     */
    public static long multiplyRoundWithScale0ToLong(float input1, double input2) {
        return multiplyRoundWithScale0ToBigDecimal(input1, input2).longValue();
    }

    /**
     * see {@link NumberUtil#mul(String...)}, this function will return number after half adjust with 0 scale
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
    public static BigDecimal multiplyRoundWithScale0ToBigDecimal(float input1, double input2) {
        return NumberUtil.mul(Float.toString(input1), Double.toString(input2)).setScale(NumberConstant.ZERO_INT_SCALE, RoundingMode.HALF_UP);
    }

    /**
     * see {@link #divideRoundWithScale0ToBigDecimal(double, double)}
     *
     * @param input1 divisor1
     * @param input2 divisor2
     * @return number after half adjust with 0 scale
     */
    public static int divideRoundWithScale0ToShort(double input1, double input2) {
        return divideRoundWithScale0ToBigDecimal(input1, input2).shortValue();
    }

    /**
     * see {@link #divideRoundWithScale0ToBigDecimal(double, double)}
     *
     * @param input1 divisor1
     * @param input2 divisor2
     * @return number after half adjust with 0 scale
     */
    public static int divideRoundWithScale0ToInt(double input1, double input2) {
        return divideRoundWithScale0ToBigDecimal(input1, input2).intValue();
    }

    /**
     * see {@link #divideRoundWithScale0ToBigDecimal(double, double)}
     *
     * @param input1 divisor1
     * @param input2 divisor2
     * @return number after half adjust with 0 scale
     */
    public static long divideRoundWithScale0ToLong(double input1, double input2) {
        return divideRoundWithScale0ToBigDecimal(input1, input2).longValue();
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
    public static BigDecimal divideRoundWithScale0ToBigDecimal(double input1, double input2) {
        return NumberUtil.div(Double.toString(input1), Double.toString(input2)).setScale(NumberConstant.ZERO_INT_SCALE, RoundingMode.HALF_UP);
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
        return NumberUtil.round(input, NumberConstant.ZERO_INT_SCALE);
    }

}