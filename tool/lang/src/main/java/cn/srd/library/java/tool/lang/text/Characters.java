// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.text;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * toolkit for char
 *
 * @author wjm
 * @since 2020-05-19 17:12
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Characters {

    /**
     * return true if the input element is number
     *
     * @param input the input element
     * @return return true if the input element is number
     */
    public static boolean isNumber(char input) {
        return Character.isDigit(input);
    }

    /**
     * return true if the input element is not number
     *
     * @param input the input element
     * @return return true if the input element is not number
     */
    public static boolean isNotNumber(char input) {
        return !isNumber(input);
    }

}