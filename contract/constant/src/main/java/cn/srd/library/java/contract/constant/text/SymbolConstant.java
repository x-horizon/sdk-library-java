// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.constant.text;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * symbol constant
 *
 * @author wjm
 * @since 2021-02-01 20:38
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SymbolConstant {

    /**
     * the {@code "\t"} is the same as "    "
     */
    public static final String TAB = "\t";

    /**
     * the {@code "\n"} is line break
     */
    public static final String LF = "\n";

    /**
     * the {@code "\r"} is line break, is mostly used on Linux and MacOS
     */
    public static final String CR = "\r";

    /**
     * the {@code ""} is line break, is mostly used on Windows
     */
    public static final String CRLF = CR + LF;

    /**
     * {@code ""}
     */
    public static final String EMPTY = "";

    /**
     * {@code " "}
     */
    public static final String SPACE = " ";

    /**
     * {@code "'"}
     */
    public static final String SINGLE_QUOTE = "'";

    /**
     * {@code "."}
     */
    public static final String DOT = ".";

    /**
     * {@code "/"}
     */
    public static final String SLASH = "/";

    /**
     * {@code "\\"}
     */
    public static final String BACKSLASH = "\\";

    /**
     * {@code "_"}
     */
    public static final String UNDERLINE = "_";

    /**
     * {@code "-"}
     */
    public static final String DASHED = "-";

    /**
     * {@code "="}
     */
    public static final String EQUALS = "=";

    /**
     * {@code "|"}
     */
    public static final String VB = "|";

    /**
     * {@code ","}
     */
    public static final String COMMA = ",";

    /**
     * {@code ":"}
     */
    public static final String COLON = ":";

    /**
     * {@code ";"}
     */
    public static final String SEMICOLON = ";";

    /**
     * {@code "*"}
     */
    public static final String ASTERISK = "*";

    /**
     * {@code """}
     */
    public static final String DOUBLE_QUOTES = "\"";

    /**
     * {@code "%"}
     */
    public static final String PERCENT = "%";

    /**
     * {@code "$"}
     */
    public static final String DOLLAR = "$";

    /**
     * {@code "@"}
     */
    public static final String AT = "@";

    /**
     * {@code "{"}
     */
    public static final String DELIM_START = "{";

    /**
     * {@code "}"}
     */
    public static final String DELIM_END = "}";

    /**
     * {@code "{}"}
     */
    public static final String DELIM_START_AND_END = "{}";

    /**
     * {@code "{}"}
     */
    public static final String EMPTY_MAP = DELIM_START_AND_END;

    /**
     * {@code "["}
     */
    public static final String BRACKET_START = "[";

    /**
     * {@code "]"}
     */
    public static final String BRACKET_END = "]";

    /**
     * {@code "[]"}
     */
    public static final String BRACKET_START_AND_END = "[]";

    /**
     * {@code "[]"}
     */
    public static final String EMPTY_ARRAY = BRACKET_START_AND_END;

    /**
     * {@code "<"}
     */
    public static final String SINGLE_BOOK_NAME_LEFT = "<";

    /**
     * {@code "<"}
     */
    public static final String LESS_THAN = SINGLE_BOOK_NAME_LEFT;

    /**
     * {@code ">"}
     */
    public static final String SINGLE_BOOK_NAME_RIGHT = ">";

    /**
     * {@code ">"}
     */
    public static final String GREATER_THAN = SINGLE_BOOK_NAME_RIGHT;

    /**
     * {@code "${"}
     */
    public static final String DOLLAR_AND_DELIM_START = DOLLAR + DELIM_START;

}