// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.compare;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.NumberUtil;
import cn.srd.library.java.tool.lang.object.Nil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * compare toolkit
 *
 * @author wjm
 * @since 2023-09-23 14:42
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comparators {

    /**
     * see {@link NumberUtil#equals(long, long)}
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element equals compared element
     */
    public static boolean equals(long input, long comparedElement) {
        return NumberUtil.equals(input, comparedElement);
    }

    /**
     * see {@link NumberUtil#equals(float, float)}
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element equals compared element
     */
    public static boolean equals(float input, float comparedElement) {
        return NumberUtil.equals(input, comparedElement);
    }

    /**
     * see {@link NumberUtil#equals(double, double)}
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element equals compared element
     */
    public static boolean equals(double input, double comparedElement) {
        return NumberUtil.equals(input, comparedElement);
    }

    /**
     * see {@link NumberUtil#equals(BigDecimal, BigDecimal)}
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element equals compared element
     */
    public static boolean equals(BigDecimal input, BigDecimal comparedElement) {
        return NumberUtil.equals(input, comparedElement);
    }

    /**
     * see {@link NumberUtil#equals(Number, Number)}
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element equals compared element
     */
    public static boolean equals(Number input, Number comparedElement) {
        return NumberUtil.equals(input, comparedElement);
    }

    /**
     * see {@link CharSequenceUtil#equalsAny(CharSequence, CharSequence...)}
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @return return true if the checked element equals any compared element
     */
    public static boolean equals(CharSequence input, CharSequence... comparedElements) {
        return CharSequenceUtil.equalsAny(input, comparedElements);
    }

    /**
     * return true if the checked element equals the compared element, use {@link #Object#equals(Object)}.
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element equals the compared element
     */
    public static boolean equals(Object input, Object comparedElement) {
        if (input == comparedElement) {
            return true;
        }
        if (Nil.isNull(input) || Nil.isNull(comparedElement)) {
            return false;
        }
        return input.equals(comparedElement);
    }

    /**
     * return true if the checked element equals any compared element, use {@link #Object#equals(Object)}.
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @return return true if the checked element equals any compared element
     */
    public static boolean equalsAny(Object input, Object... comparedElements) {
        if (input == comparedElements) {
            return true;
        }
        if (Nil.isNull(input) || Nil.isNull(comparedElements)) {
            return false;
        }
        for (Object comparedElement : comparedElements) {
            if (input == comparedElement) {
                return true;
            }
            if (input.equals(comparedElement)) {
                return true;
            }
        }
        return false;
    }

    /**
     * return true if the checked element equals all compared elements, use {@link #Object#equals(Object)}.
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @return return true if the checked element equals any compared element
     */
    public static boolean equalsAll(Object input, Object... comparedElements) {
        if (input == comparedElements) {
            return true;
        }
        if (Nil.isNull(input) || null == comparedElements) {
            return false;
        }
        for (Object comparedElement : comparedElements) {
            if (!input.equals(comparedElement)) {
                return false;
            }
        }
        return true;
    }

    /**
     * see {@link CharSequenceUtil#equalsAnyIgnoreCase(CharSequence, CharSequence...)}
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @return return true if the checked element equals any compared element ignore case
     */
    public static boolean equalsIgnoreCase(CharSequence input, CharSequence... comparedElements) {
        return CharSequenceUtil.equalsAnyIgnoreCase(input, comparedElements);
    }

    /**
     * reverse {@link #equals(long, long)}
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element not equals compared element
     */
    public static boolean notEquals(long input, long comparedElement) {
        return !equals(input, comparedElement);
    }

    /**
     * reverse {@link #equals(float, float)}
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element not equals compared element
     */
    public static boolean notEquals(float input, float comparedElement) {
        return !equals(input, comparedElement);
    }

    /**
     * reverse {@link #equals(double, double)}
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element not equals compared element
     */
    public static boolean notEquals(double input, double comparedElement) {
        return !equals(input, comparedElement);
    }

    /**
     * reverse {@link #equals(BigDecimal, BigDecimal)}
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element not equals compared element
     */
    public static boolean notEquals(BigDecimal input, BigDecimal comparedElement) {
        return !equals(input, comparedElement);
    }

    /**
     * reverse {@link #equals(Number, Number)}
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element not equals compared element
     */
    public static boolean notEquals(Number input, Number comparedElement) {
        return !equals(input, comparedElement);
    }

    /**
     * reverse {@link #equals(CharSequence, CharSequence...)}
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @return return true if the checked element not equals all compared elements
     */
    public static boolean notEquals(CharSequence input, CharSequence... comparedElements) {
        return !equals(input, comparedElements);
    }

    /**
     * reverse {@link #equals(Object, Object)}
     *
     * @param input           the checked element
     * @param comparedElement the compared element
     * @return return true if the checked element not equals the compared element
     */
    public static boolean notEquals(Object input, Object comparedElement) {
        return !equals(input, comparedElement);
    }

    /**
     * reverse {@link #equalsAny(Object, Object...)}
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @return return true if the checked element not equals all compared elements
     */
    public static boolean notEquals(Object input, Object... comparedElements) {
        return !equalsAny(input, comparedElements);
    }

    /**
     * reverse {@link #equalsIgnoreCase(CharSequence, CharSequence...)}
     *
     * @param input            the checked element
     * @param comparedElements the compared elements
     * @return return true if the checked element not equals all compared elements ignore case
     */
    public static boolean notEqualsIgnoreCase(CharSequence input, CharSequence... comparedElements) {
        return !equalsIgnoreCase(input, comparedElements);
    }

}
