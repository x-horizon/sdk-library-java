// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.constant.number;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * number constant
 *
 * @author wjm
 * @since 2023-09-19 19:44
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NumberConstant {

    /**
     * the number when two big decimal are equal
     */
    public static final int EQUAL_BIG_DECIMAL = 0;

    /**
     * the scale of decimal places: 0
     */
    public static final int ZERO_SCALE = 0;

    /**
     * one hundred
     */
    public static final int ONE_HUNDRED = 100;

    /**
     * one thousand
     */
    public static final int ONE_THOUSAND = 1000;

}
