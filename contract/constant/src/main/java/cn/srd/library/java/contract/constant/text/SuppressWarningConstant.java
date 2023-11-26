// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.constant.text;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * {@link SuppressWarnings} value constant
 *
 * @author wjm
 * @since 2023-09-21 19:35
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SuppressWarningConstant {

    /**
     * ignore all warning: {@code "all"}
     */
    public static final String ALL = "all";

    /**
     * ignore using {@link Deprecated} warning: {@code "deprecation"}
     */
    public static final String DEPRECATED = "deprecation";

    /**
     * ignore duplicated code: {@code "DuplicatedCode"}
     */
    public static final String DUPLICATED_CODE = "DuplicatedCode";

    /**
     * ignore unchecked warning: {@code "unchecked"}
     */
    public static final String UNCHECKED = "unchecked";

    /**
     * ignore without generic warning: {@code "rawtypes"}
     */
    public static final String RAW_TYPE = "rawtypes";

    /**
     * ignore without serial version UID warning: {@code "serial"}
     */
    public static final String SERIAL = "serial";

    /**
     * ignore unused warning: {@code "unused"}
     */
    public static final String UNUSED = "unused";

    /**
     * ignore varargs warning: {@code "varargs"}, the same as {@link SafeVarargs}
     */
    public static final String VARARGS = "varargs";

}
