// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.functional;

import cn.hutool.core.text.CharSequenceUtil;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.tool.lang.booleans.Booleans;
import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.number.Numbers;
import cn.srd.library.java.tool.lang.object.Nil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * the action to do something when infer success
 *
 * @param <V> the value after infer action
 * @author wjm
 * @since 2023-09-23 10:45
 */
@SuppressWarnings(SuppressWarningConstant.UNUSED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Action<V> {

    /**
     * infer result
     */
    private boolean isSuccess;

    /**
     * the infer value
     */
    private V value;

    /**
     * construct instance
     *
     * @param isSuccess success or not
     */
    private Action(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    /**
     * return ture if infer success
     *
     * @return return ture if infer success
     */
    public boolean isSuccess() {
        return isSuccess;
    }

    /**
     * return ture if infer failed
     *
     * @return return ture if infer failed
     */
    public boolean isFailed() {
        return !isSuccess();
    }

    /**
     * do something if infer success
     *
     * @param action to do action
     * @return self
     */
    public Action<V> then(Supplier<V> action) {
        if (isSuccess()) {
            this.isSuccess = true;
            this.value = action.get();
        }
        return this;
    }

    /**
     * do something if infer failed
     *
     * @param action to do action
     * @return self
     */
    public Action<V> otherwise(Supplier<V> action) {
        if (isFailed()) {
            this.isSuccess = false;
            this.value = action.get();
        }
        return this;
    }

    /**
     * return the infer result
     *
     * @return return the infer value
     */
    public V get() {
        return value;
    }

    /**
     * infer the checked element is null
     *
     * @param input the checked element
     * @param <T>   the infer value type
     * @return after infer action
     * @see Nil#isNull(Object)
     */
    public static <T> Action<T> ifNull(Object input) {
        return infer(Nil.isNull(input));
    }

    /**
     * infer the checked element is zero value, the zero value is {@code null} or {@code false}
     *
     * @param input the checked element
     * @param <T>   the infer value type
     * @return after infer action
     * @see Nil#isZeroValue(Boolean)
     */
    public static <T> Action<T> ifZeroValue(Boolean input) {
        return infer(Nil.isZeroValue(input));
    }

    /**
     * infer the checked element is zero value, the zero value is {@code null} or {@code 0}
     *
     * @param input the checked element
     * @param <T>   the infer value type
     * @return after infer action
     * @see Nil#isZeroValue(Number)
     */
    public static <T> Action<T> ifZeroValue(Number input) {
        return infer(Nil.isZeroValue(input));
    }

    /**
     * infer the checked element is zero value, the zero value is {@code null} or {@code ""}
     *
     * @param input the checked element
     * @param <T>   the infer value type
     * @return after infer action
     * @see Nil#isZeroValue(CharSequence)
     */
    public static <T> Action<T> ifZeroValue(CharSequence input) {
        return infer(Nil.isZeroValue(input));
    }

    /**
     * infer the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isEmpty(byte[])
     */
    public static <T> Action<T> ifEmpty(byte[] inputs) {
        return infer(Nil.isEmpty(inputs));
    }

    /**
     * infer the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isEmpty(short[])
     */
    public static <T> Action<T> ifEmpty(short[] inputs) {
        return infer(Nil.isEmpty(inputs));
    }

    /**
     * infer the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isEmpty(int[])
     */
    public static <T> Action<T> ifEmpty(int[] inputs) {
        return infer(Nil.isEmpty(inputs));
    }

    /**
     * infer the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isEmpty(long[])
     */
    public static <T> Action<T> ifEmpty(long[] inputs) {
        return infer(Nil.isEmpty(inputs));
    }

    /**
     * infer the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isEmpty(float[])
     */
    public static <T> Action<T> ifEmpty(float[] inputs) {
        return infer(Nil.isEmpty(inputs));
    }

    /**
     * infer the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isEmpty(double[])
     */
    public static <T> Action<T> ifEmpty(double[] inputs) {
        return infer(Nil.isEmpty(inputs));
    }

    /**
     * infer the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isEmpty(boolean[])
     */
    public static <T> Action<T> ifEmpty(boolean[] inputs) {
        return infer(Nil.isEmpty(inputs));
    }

    /**
     * infer the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isEmpty(char[])
     */
    public static <T> Action<T> ifEmpty(char[] inputs) {
        return infer(Nil.isEmpty(inputs));
    }

    /**
     * infer the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isEmpty(Object[])
     */
    public static <T> Action<T> ifEmpty(T[] inputs) {
        return infer(Nil.isEmpty(inputs));
    }

    /**
     * infer the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isEmpty(Iterator)
     */
    public static <T> Action<T> ifEmpty(Iterator<?> inputs) {
        return infer(Nil.isEmpty(inputs));
    }

    /**
     * infer the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isEmpty(Iterable)
     */
    public static <T> Action<T> ifEmpty(Iterable<?> inputs) {
        return infer(Nil.isEmpty(inputs));
    }

    /**
     * infer the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isEmpty(Enumeration)
     */
    public static <T> Action<T> ifEmpty(Enumeration<?> inputs) {
        return infer(Nil.isEmpty(inputs));
    }

    /**
     * infer the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isEmpty(Map)
     */
    public static <T> Action<T> ifEmpty(Map<?, ?> inputs) {
        return infer(Nil.isEmpty(inputs));
    }

    /**
     * see {@link CharSequenceUtil#isEmpty(CharSequence)}
     *
     * @param input the checked element
     * @param <T>   the infer value type
     * @return after infer action
     * @see Nil#isEmpty(CharSequence)
     */
    public static <T> Action<T> ifEmpty(CharSequence input) {
        return infer(Nil.isEmpty(input));
    }

    /**
     * see {@link CharSequenceUtil#isBlank(CharSequence)}
     *
     * @param input the checked element
     * @param <T>   the infer value type
     * @return after infer action
     * @see Nil#isBlank(CharSequence)
     */
    public static <T> Action<T> ifBlank(CharSequence input) {
        return infer(Nil.isBlank(input));
    }

    /**
     * infer the checked element is true
     *
     * @param input the checked element
     * @param <T>   the infer value type
     * @return after infer action
     * @see Booleans#isTrue(Boolean)
     */
    public static <T> Action<T> ifTrue(Boolean input) {
        return infer(Booleans.isTrue(input));
    }

    /**
     * infer the checked element is false
     *
     * @param input the checked element
     * @param <T>   the infer value type
     * @return after infer action
     * @see Booleans#isFalse(Boolean)
     */
    public static <T> Action<T> ifFalse(Boolean input) {
        return infer(Booleans.isFalse(input));
    }

    /**
     * infer the checked element > 0
     *
     * @param input the checked element
     * @param <T>   the infer value type
     * @return after infer action
     * @see Numbers#isPositive(Number)
     */
    public static <T> Action<T> ifPositive(Number input) {
        return infer(Numbers.isPositive(input));
    }

    /**
     * infer any checked element is null
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAnyNull(Number...)
     */
    public static <T> Action<T> ifAnyNull(Number... inputs) {
        return infer(Nil.isAnyNull(inputs));
    }

    /**
     * infer any checked element is null
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAnyNull(Enum[])
     */
    public static <T> Action<T> ifAnyNull(Enum<?>... inputs) {
        return infer(Nil.isAnyNull(inputs));
    }

    /**
     * infer any checked element is null
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAnyNull(Boolean...)
     */
    public static <T> Action<T> ifAnyNull(Boolean... inputs) {
        return infer(Nil.isAnyNull(inputs));
    }

    /**
     * infer any checked element is null
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAnyNull(Object...)
     */
    public static <T> Action<T> ifAnyNull(Object... inputs) {
        return infer(Nil.isAnyNull(inputs));
    }

    /**
     * infer any checked element is null or zero size
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAnyEmpty(Iterator[])
     */
    public static <T> Action<T> ifAnyEmpty(Iterator<?>... inputs) {
        return infer(Nil.isAnyEmpty(inputs));
    }

    /**
     * infer any checked element is null or zero size
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAnyEmpty(Iterable[])
     */
    public static <T> Action<T> ifAnyEmpty(Iterable<?>... inputs) {
        return infer(Nil.isAnyEmpty(inputs));
    }

    /**
     * infer any checked element is null or zero size
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAnyEmpty(Enumeration[])
     */
    public static <T> Action<T> ifAnyEmpty(Enumeration<?>... inputs) {
        return infer(Nil.isAnyEmpty(inputs));
    }

    /**
     * infer any checked element is null or zero size
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAnyEmpty(Map[])
     */
    public static <T> Action<T> ifAnyEmpty(Map<?, ?>... inputs) {
        return infer(Nil.isAnyEmpty(inputs));
    }

    /**
     * infer any checked element is empty
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return infer any checked element is empty
     * @see Nil#isAnyEmpty(CharSequence...)
     */
    public static <T> Action<T> ifAnyEmpty(CharSequence... inputs) {
        return infer(Nil.isAnyEmpty(inputs));
    }

    /**
     * infer any checked element is blank
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAnyBlank(CharSequence...)
     */
    public static <T> Action<T> ifAnyBlank(CharSequence... inputs) {
        return infer(Nil.isAnyBlank(inputs));
    }

    /**
     * infer any checked element is true
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Booleans#isAnyTrue(Boolean...)
     */
    public static <T> Action<T> ifAnyTrue(Boolean... inputs) {
        return infer(Booleans.isAnyTrue(inputs));
    }

    /**
     * infer any checked element is false
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Booleans#isAnyFalse(Boolean...)
     */
    public static <T> Action<T> ifAnyFalse(Boolean... inputs) {
        return infer(Booleans.isAnyFalse(inputs));
    }

    /**
     * infer any checked element > 0
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Numbers#isAnyPositive(Number...)
     */
    public static <T> Action<T> ifAnyPositive(Number... inputs) {
        return infer(Numbers.isAnyPositive(inputs));
    }

    /**
     * infer all checked elements are null
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAllNull(Number...)
     */
    public static <T> Action<T> ifAllNull(Number... inputs) {
        return infer(Nil.isAllNull(inputs));
    }

    /**
     * infer all checked elements are null
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAllNull(Enum[])
     */
    public static <T> Action<T> ifAllNull(Enum<?>... inputs) {
        return infer(Nil.isAllNull(inputs));
    }

    /**
     * infer all checked elements are null
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAllNull(Boolean...)
     */
    public static <T> Action<T> ifAllNull(Boolean... inputs) {
        return infer(Nil.isAllNull(inputs));
    }

    /**
     * infer all checked elements are null
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAllNull(Object...)
     */
    public static <T> Action<T> ifAllNull(Object... inputs) {
        return infer(Nil.isAllNull(inputs));
    }

    /**
     * infer all checked elements are null or zero size
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAllEmpty(Iterator[])
     */
    public static <T> Action<T> ifAllEmpty(Iterator<?>... inputs) {
        return infer(Nil.isAllEmpty(inputs));
    }

    /**
     * infer all checked elements are null or zero size
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAllEmpty(Iterable[])
     */
    public static <T> Action<T> ifAllEmpty(Iterable<?>... inputs) {
        return infer(Nil.isAllEmpty(inputs));
    }

    /**
     * infer all checked elements are null or zero size
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAllEmpty(Enumeration[])
     */
    public static <T> Action<T> ifAllEmpty(Enumeration<?>... inputs) {
        return infer(Nil.isAllEmpty(inputs));
    }

    /**
     * infer all checked elements are null or zero size
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAllEmpty(Map[])
     */
    public static <T> Action<T> ifAllEmpty(Map<?, ?>... inputs) {
        return infer(Nil.isAllEmpty(inputs));
    }

    /**
     * infer all checked elements are empty
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAllEmpty(CharSequence...)
     */
    public static <T> Action<T> ifAllEmpty(CharSequence... inputs) {
        return infer(Nil.isAllEmpty(inputs));
    }

    /**
     * infer all checked elements are blank
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAllBlank(CharSequence...)
     */
    public static <T> Action<T> ifAllBlank(CharSequence... inputs) {
        return infer(Nil.isAllBlank(inputs));
    }

    /**
     * infer all checked elements are true
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Booleans#isAllTrue(Boolean...)
     */
    public static <T> Action<T> ifAllTrue(Boolean... inputs) {
        return infer(Booleans.isAllTrue(inputs));
    }

    /**
     * infer all checked elements are false
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Booleans#isAllFalse(Boolean...)
     */
    public static <T> Action<T> ifAllFalse(Boolean... inputs) {
        return infer(Booleans.isAllFalse(inputs));
    }

    /**
     * infer all checked elements > 0
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Numbers#isAllPositive(Number...)
     */
    public static <T> Action<T> ifAllPositive(Number... inputs) {
        return infer(Numbers.isAllPositive(inputs));
    }

    /**
     * infer the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#equals(long, long)
     */
    public static <T> Action<T> ifEquals(long input, long comparedElement) {
        return infer(Comparators.equals(input, comparedElement));
    }

    /**
     * infer the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#equals(float, float)
     */
    public static <T> Action<T> ifEquals(float input, float comparedElement) {
        return infer(Comparators.equals(input, comparedElement));
    }

    /**
     * infer the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#equals(double, double)
     */
    public static <T> Action<T> ifEquals(double input, double comparedElement) {
        return infer(Comparators.equals(input, comparedElement));
    }

    /**
     * infer the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#equals(BigDecimal, BigDecimal)
     */
    public static <T> Action<T> ifEquals(BigDecimal input, BigDecimal comparedElement) {
        return infer(Comparators.equals(input, comparedElement));
    }

    /**
     * infer the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#equals(Number, Number)
     */
    public static <T> Action<T> ifEquals(Number input, Number comparedElement) {
        return infer(Comparators.equals(input, comparedElement));
    }

    /**
     * infer the checked element equals any compared element
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @param <T>              the infer value type
     * @return after infer action
     * @see Comparators#equals(CharSequence, CharSequence...)
     */
    public static <T> Action<T> ifEquals(CharSequence input, CharSequence... comparedElements) {
        return infer(Comparators.equals(input, comparedElements));
    }

    /**
     * infer the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#equals(byte[], byte[])
     */
    public static <T> Action<T> ifEquals(byte[] input, byte[] comparedElement) {
        return infer(Comparators.equals(input, comparedElement));
    }

    /**
     * infer the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#equals(short[], short[])
     */
    public static <T> Action<T> ifEquals(short[] input, short[] comparedElement) {
        return infer(Comparators.equals(input, comparedElement));
    }

    /**
     * infer the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#equals(int[], int[])
     */
    public static <T> Action<T> ifEquals(int[] input, int[] comparedElement) {
        return infer(Comparators.equals(input, comparedElement));
    }

    /**
     * infer the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#equals(long[], long[])
     */
    public static <T> Action<T> ifEquals(long[] input, long[] comparedElement) {
        return infer(Comparators.equals(input, comparedElement));
    }

    /**
     * infer the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#equals(float[], float[])
     */
    public static <T> Action<T> ifEquals(float[] input, float[] comparedElement) {
        return infer(Comparators.equals(input, comparedElement));
    }

    /**
     * infer the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#equals(double[], double[])
     */
    public static <T> Action<T> ifEquals(double[] input, double[] comparedElement) {
        return infer(Comparators.equals(input, comparedElement));
    }

    /**
     * infer the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#equals(boolean[], boolean[])
     */
    public static <T> Action<T> ifEquals(boolean[] input, boolean[] comparedElement) {
        return infer(Comparators.equals(input, comparedElement));
    }

    /**
     * infer the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#equals(char[], char[])
     */
    public static <T> Action<T> ifEquals(char[] input, char[] comparedElement) {
        return infer(Comparators.equals(input, comparedElement));
    }

    /**
     * infer the checked element equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#equals(Object[], Object[])
     */
    public static <T> Action<T> ifEquals(Object[] input, Object[] comparedElement) {
        return infer(Comparators.equals(input, comparedElement));
    }

    /**
     * infer the checked element equals the compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#equals(Object, Object)
     */
    public static <T> Action<T> ifEquals(Object input, Object comparedElement) {
        return infer(Comparators.equals(input, comparedElement));
    }

    /**
     * infer the checked element equals any compared element
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @param <T>              the infer value type
     * @return after infer action
     * @see Comparators#equalsAny(Object, Object...)
     */
    public static <T> Action<T> ifEqualsAny(Object input, Object... comparedElements) {
        return infer(Comparators.equalsAny(input, comparedElements));
    }

    /**
     * infer the checked element equals any compared element
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @param <T>              the infer value type
     * @return after infer action
     * @see Comparators#equalsAll(Object, Object...)
     */
    public static <T> Action<T> ifEqualsAll(Object input, Object... comparedElements) {
        return infer(Comparators.equalsAll(input, comparedElements));
    }

    /**
     * infer the checked element equals any compared element ignore case
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @param <T>              the infer value type
     * @return after infer action
     * @see Comparators#equalsIgnoreCase(CharSequence, CharSequence...)
     */
    public static <T> Action<T> ifEqualsIgnoreCase(CharSequence input, CharSequence... comparedElements) {
        return infer(Comparators.equalsIgnoreCase(input, comparedElements));
    }

    /**
     * infer the checked element is not null
     *
     * @param input the checked element
     * @param <T>   the infer value type
     * @return after infer action
     * @see Nil#isNotNull(Object)
     */
    public static <T> Action<T> ifNotNull(Object input) {
        return infer(Nil.isNotNull(input));
    }

    /**
     * infer the checked element is not zero value, the zero value is {@code null} or {@code false}
     *
     * @param input the checked element
     * @param <T>   the infer value type
     * @return after infer action
     * @see Nil#isNotZeroValue(Boolean)
     */
    public static <T> Action<T> ifNotZeroValue(Boolean input) {
        return infer(Nil.isNotZeroValue(input));
    }

    /**
     * infer the checked element is not zero value, the zero value is {@code null} or {@code 0}
     *
     * @param input the checked element
     * @param <T>   the infer value type
     * @return after infer action
     * @see Nil#isNotZeroValue(Number)
     */
    public static <T> Action<T> ifNotZeroValue(Number input) {
        return infer(Nil.isNotZeroValue(input));
    }

    /**
     * infer the checked element is not zero value, the zero value is {@code null} or {@code ""}
     *
     * @param input the checked element
     * @param <T>   the infer value type
     * @return after infer action
     * @see Nil#isNotZeroValue(CharSequence)
     */
    public static <T> Action<T> ifNotZeroValue(CharSequence input) {
        return infer(Nil.isNotZeroValue(input));
    }

    /**
     * infer the checked element is not empty
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isNotEmpty(byte[])
     */
    public static <T> Action<T> ifNotEmpty(byte[] inputs) {
        return infer(Nil.isNotEmpty(inputs));
    }

    /**
     * infer the checked element is not empty
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isNotEmpty(short[])
     */
    public static <T> Action<T> ifNotEmpty(short[] inputs) {
        return infer(Nil.isNotEmpty(inputs));
    }

    /**
     * infer the checked element is not empty
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isNotEmpty(int[])
     */
    public static <T> Action<T> ifNotEmpty(int[] inputs) {
        return infer(Nil.isNotEmpty(inputs));
    }

    /**
     * infer the checked element is not empty
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isNotEmpty(long[])
     */
    public static <T> Action<T> ifNotEmpty(long[] inputs) {
        return infer(Nil.isNotEmpty(inputs));
    }

    /**
     * infer the checked element is not empty
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isNotEmpty(float[])
     */
    public static <T> Action<T> ifNotEmpty(float[] inputs) {
        return infer(Nil.isNotEmpty(inputs));
    }

    /**
     * infer the checked element is not empty
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isNotEmpty(double[])
     */
    public static <T> Action<T> ifNotEmpty(double[] inputs) {
        return infer(Nil.isNotEmpty(inputs));
    }

    /**
     * infer the checked element is not empty
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isNotEmpty(boolean[])
     */
    public static <T> Action<T> ifNotEmpty(boolean[] inputs) {
        return infer(Nil.isNotEmpty(inputs));
    }

    /**
     * infer the checked element is not empty
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isNotEmpty(char[])
     */
    public static <T> Action<T> ifNotEmpty(char[] inputs) {
        return infer(Nil.isNotEmpty(inputs));
    }

    /**
     * infer the checked element is not empty
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isNotEmpty(Object[])
     */
    public static <T> Action<T> ifNotEmpty(T[] inputs) {
        return infer(Nil.isNotEmpty(inputs));
    }

    /**
     * infer the checked element is not empty
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isNotEmpty(Iterator)
     */
    public static <T> Action<T> ifNotEmpty(Iterator<?> inputs) {
        return infer(Nil.isNotEmpty(inputs));
    }

    /**
     * infer the checked element is not empty
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isNotEmpty(Iterable)
     */
    public static <T> Action<T> ifNotEmpty(Iterable<?> inputs) {
        return infer(Nil.isNotEmpty(inputs));
    }

    /**
     * infer the checked element is not empty
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isNotEmpty(Enumeration)
     */
    public static <T> Action<T> ifNotEmpty(Enumeration<?> inputs) {
        return infer(Nil.isNotEmpty(inputs));
    }

    /**
     * infer the checked element is not empty
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isNotEmpty(Map)
     */
    public static <T> Action<T> ifNotEmpty(Map<?, ?> inputs) {
        return infer(Nil.isNotEmpty(inputs));
    }

    /**
     * infer the checked element is not empty
     *
     * @param input the checked element
     * @param <T>   the infer value type
     * @return after infer action
     * @see Nil#isNotEmpty(CharSequence)
     */
    public static <T> Action<T> ifNotEmpty(CharSequence input) {
        return infer(Nil.isNotEmpty(input));
    }

    /**
     * infer the checked element is not blank
     *
     * @param input the checked element
     * @param <T>   the infer value type
     * @return after infer action
     * @see Nil#isNotBlank(CharSequence)
     */
    public static <T> Action<T> ifNotBlank(CharSequence input) {
        return infer(Nil.isNotBlank(input));
    }

    /**
     * infer the checked element <= 0
     *
     * @param input the checked element
     * @param <T>   the infer value type
     * @return after infer action
     * @see Numbers#isNotPositive(Number)
     */
    public static <T> Action<T> ifNotPositive(Number input) {
        return infer(Numbers.isNotPositive(input));
    }

    /**
     * infer the checked elements are not null
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAllNotNull(Number...)
     */
    public static <T> Action<T> ifAllNotNull(Number... inputs) {
        return infer(Nil.isAllNotNull(inputs));
    }

    /**
     * infer the checked elements are not null
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAllNotNull(Enum[])
     */
    public static <T> Action<T> ifAllNotNull(Enum<?>... inputs) {
        return infer(Nil.isAllNotNull(inputs));
    }

    /**
     * infer the checked elements are not null
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAllNotNull(Boolean...)
     */
    public static <T> Action<T> ifAllNotNull(Boolean... inputs) {
        return infer(Nil.isAllNotNull(inputs));
    }

    /**
     * infer the checked elements are not null
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAllNotNull(Object...)
     */
    public static <T> Action<T> ifAllNotNull(Object... inputs) {
        return infer(Nil.isAllNotNull(inputs));
    }

    /**
     * infer the checked elements are not empty
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAllNotEmpty(Iterator[])
     */
    public static <T> Action<T> ifAllNotEmpty(Iterator<?>... inputs) {
        return infer(Nil.isAllNotEmpty(inputs));
    }

    /**
     * infer the checked elements are not empty
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAllNotEmpty(Iterable[])
     */
    public static <T> Action<T> ifAllNotEmpty(Iterable<?>... inputs) {
        return infer(Nil.isAllNotEmpty(inputs));
    }

    /**
     * infer the checked elements are not empty
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAllNotEmpty(Enumeration[])
     */
    public static <T> Action<T> ifAllNotEmpty(Enumeration<?>... inputs) {
        return infer(Nil.isAllNotEmpty(inputs));
    }

    /**
     * infer the checked elements are not empty
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAllNotEmpty(Map[])
     */
    public static <T> Action<T> ifAllNotEmpty(Map<?, ?>... inputs) {
        return infer(Nil.isAllNotEmpty(inputs));
    }

    /**
     * infer the checked elements are not empty
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAllNotEmpty(CharSequence...)
     */
    public static <T> Action<T> ifAllNotEmpty(CharSequence... inputs) {
        return infer(Nil.isAllNotEmpty(inputs));
    }

    /**
     * infer the checked elements are not blank
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Nil#isAllNotBlank(CharSequence...)
     */
    public static <T> Action<T> ifAllNotBlank(CharSequence... inputs) {
        return infer(Nil.isAllNotBlank(inputs));
    }

    /**
     * infer all checked elements <= 0
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
     * @see Numbers#isAllNotPositive(Number...)
     */
    public static <T> Action<T> ifAllNotPositive(Number... inputs) {
        return infer(Numbers.isAllNotPositive(inputs));
    }

    /**
     * infer the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#notEquals(long, long)
     */
    public static <T> Action<T> ifNotEquals(long input, long comparedElement) {
        return infer(Comparators.notEquals(input, comparedElement));
    }

    /**
     * infer the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#notEquals(float, float)
     */
    public static <T> Action<T> ifNotEquals(float input, float comparedElement) {
        return infer(Comparators.notEquals(input, comparedElement));
    }

    /**
     * infer the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#notEquals(double, double)
     */
    public static <T> Action<T> ifNotEquals(double input, double comparedElement) {
        return infer(Comparators.notEquals(input, comparedElement));
    }

    /**
     * infer the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#notEquals(BigDecimal, BigDecimal)
     */
    public static <T> Action<T> ifNotEquals(BigDecimal input, BigDecimal comparedElement) {
        return infer(Comparators.notEquals(input, comparedElement));
    }

    /**
     * infer the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#notEquals(Number, Number)
     */
    public static <T> Action<T> ifNotEquals(Number input, Number comparedElement) {
        return infer(Comparators.notEquals(input, comparedElement));
    }

    /**
     * infer the checked element not equals all compared elements
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @param <T>              the infer value type
     * @return after infer action
     * @see Comparators#notEquals(CharSequence, CharSequence...)
     */
    public static <T> Action<T> ifNotEquals(CharSequence input, CharSequence... comparedElements) {
        return infer(Comparators.notEquals(input, comparedElements));
    }

    /**
     * infer the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#notEquals(byte[], byte[])
     */
    public static <T> Action<T> ifNotEquals(byte[] input, byte[] comparedElement) {
        return infer(Comparators.notEquals(input, comparedElement));
    }

    /**
     * infer the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#notEquals(short[], short[])
     */
    public static <T> Action<T> ifNotEquals(short[] input, short[] comparedElement) {
        return infer(Comparators.notEquals(input, comparedElement));
    }

    /**
     * infer the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#notEquals(int[], int[])
     */
    public static <T> Action<T> ifNotEquals(int[] input, int[] comparedElement) {
        return infer(Comparators.notEquals(input, comparedElement));
    }

    /**
     * infer the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#notEquals(long[], long[])
     */
    public static <T> Action<T> ifNotEquals(long[] input, long[] comparedElement) {
        return infer(Comparators.notEquals(input, comparedElement));
    }

    /**
     * infer the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#notEquals(float[], float[])
     */
    public static <T> Action<T> ifNotEquals(float[] input, float[] comparedElement) {
        return infer(Comparators.notEquals(input, comparedElement));
    }

    /**
     * infer the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#notEquals(double[], double[])
     */
    public static <T> Action<T> ifNotEquals(double[] input, double[] comparedElement) {
        return infer(Comparators.notEquals(input, comparedElement));
    }

    /**
     * infer the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#notEquals(boolean[], boolean[])
     */
    public static <T> Action<T> ifNotEquals(boolean[] input, boolean[] comparedElement) {
        return infer(Comparators.notEquals(input, comparedElement));
    }

    /**
     * infer the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#notEquals(char[], char[])
     */
    public static <T> Action<T> ifNotEquals(char[] input, char[] comparedElement) {
        return infer(Comparators.notEquals(input, comparedElement));
    }

    /**
     * infer the checked element not equals compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#notEquals(Object[], Object[])
     */
    public static <T> Action<T> ifNotEquals(Object[] input, Object[] comparedElement) {
        return infer(Comparators.notEquals(input, comparedElement));
    }

    /**
     * infer the checked element not equals the compared elements
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
     * @see Comparators#notEquals(Object, Object)
     */
    public static <T> Action<T> ifNotEquals(Object input, Object comparedElement) {
        return infer(Comparators.notEquals(input, comparedElement));
    }

    /**
     * infer the checked element not equals all compared elements
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @param <T>              the infer value type
     * @return after infer action
     * @see Comparators#notEquals(Object, Object...)
     */
    public static <T> Action<T> ifNotEquals(Object input, Object... comparedElements) {
        return infer(Comparators.notEquals(input, comparedElements));
    }

    /**
     * infer the checked element not equals all compared elements ignore case
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @param <T>              the infer value type
     * @return after infer action
     * @see Comparators#notEquals(CharSequence, CharSequence...)
     */
    public static <T> Action<T> ifNotEqualsIgnoreCase(CharSequence input, CharSequence... comparedElements) {
        return infer(Comparators.notEqualsIgnoreCase(input, comparedElements));
    }

    /**
     * infer action
     *
     * @param input  the checked element
     * @param action the infer action
     * @param <T>    the infer value type
     * @return after infer action
     */
    public static <T> Action<T> infer(T input, Predicate<T> action) {
        return infer(action.test(input));
    }

    /**
     * infer action
     *
     * @param isSuccess infer condition
     * @param <T>       the infer value type
     * @return after infer action
     */
    public static <T> Action<T> infer(boolean isSuccess) {
        return new Action<>(isSuccess);
    }

}
