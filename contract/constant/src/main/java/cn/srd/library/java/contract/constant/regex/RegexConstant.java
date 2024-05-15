// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.constant.regex;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

/**
 * @author wjm
 * @since 2024-04-24 15:14
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegexConstant {

    /**
     * matching example: "-1.11", "-1", "0", "0.0", "1", "1.11"
     */
    public static final String NUMBER = "^-?\\d+(\\.\\d+)?$";

    public static final Pattern NUMBER_PATTERN = Pattern.compile(NUMBER);

    /**
     * matching example: "-1", "0", "1"
     */
    public static final String INTEGRAL_NUMBER = "^-?\\d+$";

    public static final Pattern INTEGRAL_NUMBER_PATTERN = Pattern.compile(INTEGRAL_NUMBER);

    /**
     * matching example: "-1.11", "0.0", "1.11"
     */
    public static final String FLOAT_NUMBER = "^-?\\d+(\\.\\d+)$";

    public static final Pattern FLOAT_NUMBER_PATTERN = Pattern.compile(FLOAT_NUMBER);

    /**
     * matching example: "0", "0.0", "1", "1.11"
     */
    public static final String POSITIVE_NUMBER = "^\\d+(\\.\\d+)?$";

    public static final Pattern POSITIVE_NUMBER_PATTERN = Pattern.compile(POSITIVE_NUMBER);

    /**
     * matching example: "0", "1"
     */
    public static final String POSITIVE_INTEGRAL_NUMBER = "^\\d+$";

    public static final Pattern POSITIVE_INTEGRAL_NUMBER_PATTERN = Pattern.compile(POSITIVE_INTEGRAL_NUMBER);

    /**
     * matching example: "0.0", "1.11"
     */
    public static final String POSITIVE_FLOAT_NUMBER = "^\\d+(\\.\\d+)$";

    public static final Pattern POSITIVE_FLOAT_NUMBER_PATTERN = Pattern.compile(POSITIVE_FLOAT_NUMBER);

    /**
     * matching example: "-1.11", "-1"
     */
    public static final String NEGATIVE_NUMBER = "^-\\d+(\\.\\d+)?$";

    public static final Pattern NEGATIVE_NUMBER_PATTERN = Pattern.compile(NEGATIVE_NUMBER);

    /**
     * matching example: "-1"
     */
    public static final String NEGATIVE_INTEGRAL_NUMBER = "^-\\d+$";

    public static final Pattern NEGATIVE_INTEGRAL_NUMBER_PATTERN = Pattern.compile(NEGATIVE_INTEGRAL_NUMBER);

    /**
     * matching example: "-1.11"
     */
    public static final String NEGATIVE_FLOAT_NUMBER = "^-\\d+(\\.\\d+)$";

    public static final Pattern NEGATIVE_FLOAT_NUMBER_PATTERN = Pattern.compile(NEGATIVE_FLOAT_NUMBER);

    /**
     * matching example: "abcdefg'11', '22','33'   ,'rr', 'pp' sdncsldbcls" -> matching the last '' -> pp
     */
    public static final String THE_LAST_DOUBLE_QUOTE = "'([^']*)'(?!.*')";

    public static final Pattern THE_LAST_DOUBLE_QUOTE_PATTERN = Pattern.compile(THE_LAST_DOUBLE_QUOTE);

}