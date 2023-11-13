// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.functional;

import cn.hutool.core.text.CharSequenceUtil;
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

    // TODO wjm bug: value is always null
    // /**
    //  * do something if infer success
    //  *
    //  * @param action to do action
    //  * @return self
    //  */
    // public Action<V> then(Consumer<V> action) {
    //     if (isSuccess()) {
    //         this.isSuccess = true;
    //         action.accept(this.value);
    //     }
    //     return this;
    // }

    // TODO wjm bug: value is always null
    // /**
    //  * do something if infer failed
    //  *
    //  * @param action to do action
    //  * @return self
    //  */
    // public Action<V> otherwise(Consumer<V> action) {
    //     if (isFailed()) {
    //         this.isSuccess = false;
    //         action.accept(this.value);
    //     }
    //     return this;
    // }

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
     */
    public static <T> Action<T> ifNull(Object input) {
        return infer(Nil.isNull(input));
    }

    /**
     * infer the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
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
     * @see CharSequenceUtil#hasEmpty(CharSequence...)
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
     * @see CharSequenceUtil#hasBlank(CharSequence...)
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
     * @see CharSequenceUtil#isAllEmpty(CharSequence...)
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
     * @see CharSequenceUtil#isAllBlank(CharSequence...)
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
     */
    public static <T> Action<T> ifEquals(CharSequence input, CharSequence... comparedElements) {
        return infer(Comparators.equals(input, comparedElements));
    }

    /**
     * infer the checked element equals the compared element
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
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
     */
    public static <T> Action<T> ifNotNull(Object input) {
        return infer(Nil.isNotNull(input));
    }

    /**
     * infer the checked element is not empty
     *
     * @param inputs the checked elements
     * @param <T>    the infer value type
     * @return after infer action
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
     * @see CharSequenceUtil#isNotEmpty(CharSequence)
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
     * @see CharSequenceUtil#isNotBlank(CharSequence)
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
     */
    public static <T> Action<T> ifNotEquals(CharSequence input, CharSequence... comparedElements) {
        return infer(Comparators.notEquals(input, comparedElements));
    }

    /**
     * infer the checked element not equals the compared elements
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @param <T>             the infer value type
     * @return after infer action
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
