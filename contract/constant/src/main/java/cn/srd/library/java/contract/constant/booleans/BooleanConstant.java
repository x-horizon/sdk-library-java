// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.constant.booleans;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * boolean constant
 *
 * @author wjm
 * @since 2023-10-16 01:30
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BooleanConstant {

    public static final Boolean TRUE = Boolean.TRUE;

    public static final Boolean FALSE = Boolean.FALSE;

    public static final String TRUE_STRING_LOWER_CASE = "true";

    public static final String TRUE_STRING_UPPER_CASE = "TRUE";

    public static final String TRUE_STRING_UPPER_FIRST = "True";

    public static final String FALSE_STRING_LOWER_CASE = "false";

    public static final String FALSE_STRING_UPPER_CASE = "FALSE";

    public static final String FALSE_STRING_UPPER_FIRST = "False";

}
