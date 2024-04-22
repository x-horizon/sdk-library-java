// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.object;

import cn.hutool.core.text.CharSequenceUtil;
import cn.srd.library.java.contract.constant.number.NumberConstant;
import cn.srd.library.java.tool.lang.booleans.Booleans;
import cn.srd.library.java.tool.lang.compare.Comparators;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

/**
 * empty validator toolkit
 *
 * @author wjm
 * @since 2023-09-23 11:49
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Nil {

    /**
     * return true if the checked element is null
     *
     * @param input the checked element
     * @return return true if the checked element is null
     */
    public static boolean isNull(Object input) {
        return null == input;
    }

    /**
     * return true if the checked element is zero value, the zero value is {@code null} or {@code false}
     *
     * @param input the checked element
     * @return return true if the checked element is zero value
     */
    public static boolean isZeroValue(Boolean input) {
        return Nil.isNull(input) || Booleans.isFalse(input);
    }

    /**
     * return true if the checked element is zero value, the zero value is {@code null} or {@code 0}
     *
     * @param input the checked element
     * @return return true if the checked element is zero value
     */
    public static boolean isZeroValue(Number input) {
        return Nil.isNull(input) || Comparators.equals(NumberConstant.ZERO_NUMBER_VALUE, input);
    }

    /**
     * return true if the checked element is zero value, the zero value is {@code null} or {@code ""}
     *
     * @param input the checked element
     * @return return true if the checked element is zero value
     */
    public static boolean isZeroValue(CharSequence input) {
        return isEmpty(input);
    }

    /**
     * return true if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is null or zero size
     */
    public static boolean isEmpty(byte[] inputs) {
        return Nil.isNull(inputs) || inputs.length == 0;
    }

    /**
     * return true if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is null or zero size
     */
    public static boolean isEmpty(short[] inputs) {
        return Nil.isNull(inputs) || inputs.length == 0;
    }

    /**
     * return true if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is null or zero size
     */
    public static boolean isEmpty(int[] inputs) {
        return Nil.isNull(inputs) || inputs.length == 0;
    }

    /**
     * return true if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is null or zero size
     */
    public static boolean isEmpty(long[] inputs) {
        return Nil.isNull(inputs) || inputs.length == 0;
    }

    /**
     * return true if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is null or zero size
     */
    public static boolean isEmpty(float[] inputs) {
        return Nil.isNull(inputs) || inputs.length == 0;
    }

    /**
     * return true if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is null or zero size
     */
    public static boolean isEmpty(double[] inputs) {
        return Nil.isNull(inputs) || inputs.length == 0;
    }

    /**
     * return true if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is null or zero size
     */
    public static boolean isEmpty(boolean[] inputs) {
        return Nil.isNull(inputs) || inputs.length == 0;
    }

    /**
     * return true if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is null or zero size
     */
    public static boolean isEmpty(char[] inputs) {
        return Nil.isNull(inputs) || inputs.length == 0;
    }

    /**
     * return true if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is null or zero size
     */
    public static <T> boolean isEmpty(T[] inputs) {
        return Nil.isNull(inputs) || inputs.length == 0;
    }

    /**
     * return true if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is null or zero size
     */
    public static boolean isEmpty(Iterator<?> inputs) {
        return Nil.isNull(inputs) || !inputs.hasNext();
    }

    /**
     * return true if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is null or zero size
     */
    public static boolean isEmpty(Iterable<?> inputs) {
        return Nil.isNull(inputs) || isEmpty(inputs.iterator());
    }

    /**
     * return true if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is null or zero size
     */
    public static boolean isEmpty(Enumeration<?> inputs) {
        return Nil.isNull(inputs) || !inputs.hasMoreElements();
    }

    /**
     * return true if the checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if the checked element is null or zero size
     */
    public static boolean isEmpty(Map<?, ?> inputs) {
        return Nil.isNull(inputs) || inputs.isEmpty();
    }

    /**
     * see {@link CharSequenceUtil#isEmpty(CharSequence)}
     *
     * @param input the checked element
     * @return return true if the checked element is empty
     */
    public static boolean isEmpty(CharSequence input) {
        return CharSequenceUtil.isEmpty(input);
    }

    /**
     * see {@link CharSequenceUtil#isBlank(CharSequence)}
     *
     * @param input the checked element
     * @return return true if the checked element is blank
     */
    public static boolean isBlank(CharSequence input) {
        return CharSequenceUtil.isBlank(input);
    }

    /**
     * return true if any checked element is null
     *
     * @param inputs the checked elements
     * @return return true if any checked element is null
     */
    public static boolean isAnyNull(Number... inputs) {
        if (isNull(inputs)) {
            return true;
        }
        for (Number input : inputs) {
            if (isNull(input)) {
                return true;
            }
        }
        return false;
    }

    /**
     * return true if any checked element is null
     *
     * @param inputs the checked elements
     * @return return true if any checked element is null
     */
    public static boolean isAnyNull(Enum<?>... inputs) {
        if (isNull(inputs)) {
            return true;
        }
        for (Enum<?> input : inputs) {
            if (isNull(input)) {
                return true;
            }
        }
        return false;
    }

    /**
     * return true if any checked element is null
     *
     * @param inputs the checked elements
     * @return return true if any checked element is null
     */
    public static boolean isAnyNull(Boolean... inputs) {
        if (isNull(inputs)) {
            return true;
        }
        for (Object input : inputs) {
            if (isNull(input)) {
                return true;
            }
        }
        return false;
    }

    /**
     * return true if any checked element is null
     *
     * @param inputs the checked elements
     * @return return true if any checked element is null
     */
    public static boolean isAnyNull(Object... inputs) {
        if (isNull(inputs)) {
            return true;
        }
        for (Object input : inputs) {
            if (isNull(input)) {
                return true;
            }
        }
        return false;
    }

    /**
     * return true if any checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if any checked element is null or zero size
     */
    public static boolean isAnyEmpty(Iterator<?>... inputs) {
        if (Nil.isNull(inputs)) {
            return true;
        }
        for (Iterator<?> input : inputs) {
            if (isEmpty(input)) {
                return true;
            }
        }
        return false;
    }

    /**
     * return true if any checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if any checked element is null or zero size
     */
    public static boolean isAnyEmpty(Iterable<?>... inputs) {
        if (Nil.isNull(inputs)) {
            return true;
        }
        for (Iterable<?> input : inputs) {
            if (isEmpty(input)) {
                return true;
            }
        }
        return false;
    }

    /**
     * return true if any checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if any checked element is null or zero size
     */
    public static boolean isAnyEmpty(Enumeration<?>... inputs) {
        if (Nil.isNull(inputs)) {
            return true;
        }
        for (Enumeration<?> input : inputs) {
            if (isEmpty(input)) {
                return true;
            }
        }
        return false;
    }

    /**
     * return true if any checked element is null or zero size
     *
     * @param inputs the checked elements
     * @return return true if any checked element is null or zero size
     */
    public static boolean isAnyEmpty(Map<?, ?>... inputs) {
        if (Nil.isNull(inputs)) {
            return true;
        }
        for (Map<?, ?> input : inputs) {
            if (isEmpty(input)) {
                return true;
            }
        }
        return false;
    }

    /**
     * see {@link CharSequenceUtil#hasEmpty(CharSequence...)}
     *
     * @param inputs the checked elements
     * @return return true if any checked element is empty
     */
    public static boolean isAnyEmpty(CharSequence... inputs) {
        return CharSequenceUtil.hasEmpty(inputs);
    }

    /**
     * see {@link CharSequenceUtil#hasBlank(CharSequence...)}
     *
     * @param inputs the checked elements
     * @return return true if any checked element is blank
     */
    public static boolean isAnyBlank(CharSequence... inputs) {
        return CharSequenceUtil.hasBlank(inputs);
    }

    /**
     * return true if all checked elements are null
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are null
     */
    public static boolean isAllNull(Number... inputs) {
        if (isNull(inputs)) {
            return true;
        }
        for (Number input : inputs) {
            if (isNotNull(input)) {
                return false;
            }
        }
        return true;
    }

    /**
     * return true if all checked elements are null
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are null
     */
    public static boolean isAllNull(Enum<?>... inputs) {
        if (isNull(inputs)) {
            return true;
        }
        for (Enum<?> input : inputs) {
            if (isNotNull(input)) {
                return false;
            }
        }
        return true;
    }

    /**
     * return true if all checked elements are null
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are null
     */
    public static boolean isAllNull(Boolean... inputs) {
        if (isNull(inputs)) {
            return true;
        }
        for (Boolean input : inputs) {
            if (isNotNull(input)) {
                return false;
            }
        }
        return true;
    }

    /**
     * return true if all checked elements are null
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are null
     */
    public static boolean isAllNull(Object... inputs) {
        if (isNull(inputs)) {
            return true;
        }
        for (Object input : inputs) {
            if (isNotNull(input)) {
                return false;
            }
        }
        return true;
    }

    /**
     * return true if all checked elements are null or zero size
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are null or zero size
     */
    public static boolean isAllEmpty(Iterator<?>... inputs) {
        if (Nil.isNull(inputs)) {
            return true;
        }
        for (Iterator<?> input : inputs) {
            if (isNotEmpty(input)) {
                return false;
            }
        }
        return true;
    }

    /**
     * return true if all checked elements are null or zero size
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are null or zero size
     */
    public static boolean isAllEmpty(Iterable<?>... inputs) {
        if (Nil.isNull(inputs)) {
            return true;
        }
        for (Iterable<?> input : inputs) {
            if (isNotEmpty(input)) {
                return false;
            }
        }
        return true;
    }

    /**
     * return true if all checked elements are null or zero size
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are null or zero size
     */
    public static boolean isAllEmpty(Enumeration<?>... inputs) {
        if (Nil.isNull(inputs)) {
            return true;
        }
        for (Enumeration<?> input : inputs) {
            if (isNotEmpty(input)) {
                return false;
            }
        }
        return true;
    }

    /**
     * return true if all checked elements are null or zero size
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are null or zero size
     */
    public static boolean isAllEmpty(Map<?, ?>... inputs) {
        if (Nil.isNull(inputs)) {
            return true;
        }
        for (Map<?, ?> input : inputs) {
            if (isNotEmpty(input)) {
                return false;
            }
        }
        return true;
    }

    /**
     * see {@link CharSequenceUtil#isAllEmpty(CharSequence...)}
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are empty
     */
    public static boolean isAllEmpty(CharSequence... inputs) {
        return CharSequenceUtil.isAllEmpty(inputs);
    }

    /**
     * see {@link CharSequenceUtil#isAllBlank(CharSequence...)}
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are blank
     */
    public static boolean isAllBlank(CharSequence... inputs) {
        return CharSequenceUtil.isAllBlank(inputs);
    }

    /**
     * reverse {@link #isNull(Object)}
     *
     * @param input the checked element
     * @return return true if the checked elements is not null
     */
    public static boolean isNotNull(Object input) {
        return !isNull(input);
    }

    /**
     * reverse {@link #isZeroValue(Boolean)}
     *
     * @param input the checked element
     * @return return true if the checked elements is not zero value, the zero value is {@code null} or {@code false}
     */
    public static boolean isNotZeroValue(Boolean input) {
        return !isZeroValue(input);
    }

    /**
     * reverse {@link #isZeroValue(Number)}
     *
     * @param input the checked element
     * @return return true if the checked elements is not zero value, the zero value is {@code null} or {@code 0}
     */
    public static boolean isNotZeroValue(Number input) {
        return !isZeroValue(input);
    }

    /**
     * reverse {@link #isZeroValue(CharSequence)}
     *
     * @param input the checked element
     * @return return true if the checked elements is not zero value, the zero value is {@code null} or {@code ""}
     */
    public static boolean isNotZeroValue(CharSequence input) {
        return !isZeroValue(input);
    }

    /**
     * reverse {@link Nil#isEmpty(byte[])}
     *
     * @param inputs the checked elements
     * @return return true if the checked element is not null and at least one size
     */
    public static boolean isNotEmpty(byte[] inputs) {
        return !isEmpty(inputs);
    }

    /**
     * reverse {@link Nil#isEmpty(short[])}
     *
     * @param inputs the checked elements
     * @return return true if the checked element is not null and at least one size
     */
    public static boolean isNotEmpty(short[] inputs) {
        return !isEmpty(inputs);
    }

    /**
     * reverse {@link Nil#isEmpty(int[])}
     *
     * @param inputs the checked elements
     * @return return true if the checked element is not null and at least one size
     */
    public static boolean isNotEmpty(int[] inputs) {
        return !isEmpty(inputs);
    }

    /**
     * reverse {@link Nil#isEmpty(long[])}
     *
     * @param inputs the checked elements
     * @return return true if the checked element is not null and at least one size
     */
    public static boolean isNotEmpty(long[] inputs) {
        return !isEmpty(inputs);
    }

    /**
     * reverse {@link Nil#isEmpty(float[])}
     *
     * @param inputs the checked elements
     * @return return true if the checked element is not null and at least one size
     */
    public static boolean isNotEmpty(float[] inputs) {
        return !isEmpty(inputs);
    }

    /**
     * reverse {@link Nil#isEmpty(double[])}
     *
     * @param inputs the checked elements
     * @return return true if the checked element is not null and at least one size
     */
    public static boolean isNotEmpty(double[] inputs) {
        return !isEmpty(inputs);
    }

    /**
     * reverse {@link Nil#isEmpty(boolean[])}
     *
     * @param inputs the checked elements
     * @return return true if the checked element is not null and at least one size
     */
    public static boolean isNotEmpty(boolean[] inputs) {
        return !isEmpty(inputs);
    }

    /**
     * reverse {@link Nil#isEmpty(char[])}
     *
     * @param inputs the checked elements
     * @return return true if the checked element is not null and at least one size
     */
    public static boolean isNotEmpty(char[] inputs) {
        return !isEmpty(inputs);
    }

    /**
     * reverse {@link Nil#isEmpty(Object[])}
     *
     * @param inputs the checked elements
     * @return return true if the checked element is not null and at least one size
     */
    public static <T> boolean isNotEmpty(T[] inputs) {
        return !isEmpty(inputs);
    }

    /**
     * reverse {@link Nil#isEmpty(Iterator)}
     *
     * @param inputs the checked elements
     * @return return true if the checked element is not null and at least one size
     */
    public static boolean isNotEmpty(Iterator<?> inputs) {
        return !isEmpty(inputs);
    }

    /**
     * reverse {@link Nil#isEmpty(Iterable)}
     *
     * @param inputs the checked elements
     * @return return true if the checked element is not null and at least one size
     */
    public static boolean isNotEmpty(Iterable<?> inputs) {
        return !isEmpty(inputs);
    }

    /**
     * reverse {@link Nil#isEmpty(Enumeration)}
     *
     * @param inputs the checked elements
     * @return return true if the checked element is not null and at least one size
     */
    public static boolean isNotEmpty(Enumeration<?> inputs) {
        return !isEmpty(inputs);
    }

    /**
     * reverse {@link Nil#isEmpty(Map)}
     *
     * @param inputs the checked elements
     * @return return true if the checked element is not null and at least one size
     */
    public static boolean isNotEmpty(Map<?, ?> inputs) {
        return !isEmpty(inputs);
    }

    /**
     * see {@link CharSequenceUtil#isNotEmpty(CharSequence)}
     *
     * @param input the checked element
     * @return return true if the checked element is not empty
     */
    public static boolean isNotEmpty(CharSequence input) {
        return !isEmpty(input);
    }

    /**
     * see {@link CharSequenceUtil#isNotBlank(CharSequence)}
     *
     * @param input the checked element
     * @return return true if the checked element is not blank
     */
    public static boolean isNotBlank(CharSequence input) {
        return !isBlank(input);
    }

    /**
     * reverse {@link #isAnyNull(Number[])}
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are not null
     */
    public static boolean isAllNotNull(Number... inputs) {
        return !isAnyNull(inputs);
    }

    /**
     * reverse {@link #isAnyNull(Enum[])}
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are not null
     */
    public static boolean isAllNotNull(Enum<?>... inputs) {
        return !isAnyNull(inputs);
    }

    /**
     * reverse {@link #isAnyNull(Boolean...)}
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are not null
     */
    public static boolean isAllNotNull(Boolean... inputs) {
        return !isAnyNull(inputs);
    }

    /**
     * reverse {@link #isAnyNull(Object...)}
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are not null
     */
    public static boolean isAllNotNull(Object... inputs) {
        return !isAnyNull(inputs);
    }

    /**
     * reverse {@link Nil#isAnyEmpty(Iterator[])}
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are null or zero size
     */
    public static boolean isAllNotEmpty(Iterator<?>... inputs) {
        return !isAnyEmpty(inputs);
    }

    /**
     * reverse {@link Nil#isAnyEmpty(Iterable[])}
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are null or zero size
     */
    public static boolean isAllNotEmpty(Iterable<?>... inputs) {
        return !isAnyEmpty(inputs);
    }

    /**
     * reverse {@link Nil#isAnyEmpty(Enumeration[])}
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are null or zero size
     */
    public static boolean isAllNotEmpty(Enumeration<?>... inputs) {
        return !isAnyEmpty(inputs);
    }

    /**
     * reverse {@link Nil#isAnyEmpty(Map[])}
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are null or zero size
     */
    public static boolean isAllNotEmpty(Map<?, ?>... inputs) {
        return !isAnyEmpty(inputs);
    }

    /**
     * reverse {@link #isAnyEmpty(CharSequence...)}
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are not empty
     */
    public static boolean isAllNotEmpty(CharSequence... inputs) {
        return !isAnyEmpty(inputs);
    }

    /**
     * reverse {@link #isAnyBlank(CharSequence...)}
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are not blank
     */
    public static boolean isAllNotBlank(CharSequence... inputs) {
        return !isAnyBlank(inputs);
    }

}