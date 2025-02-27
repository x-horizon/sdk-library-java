package org.horizon.sdk.library.java.contract.constant.web;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * html escape constant
 *
 * @author wjm
 * @since 2021-02-01 20:38
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HtmlEscapeConstant {

    /**
     * {@code "&#064;" -> "@"}
     */
    public static final String AT = "&#064;";

    /**
     * {@code "&#42;" -> "*"}
     */
    public static final String ASTERISK = "&#42;";

    /**
     * {@code "&nbsp;" -> " "}
     */
    public static final String NBSP = "&nbsp;";

    /**
     * {@code "&amp;" -> "&"}
     */
    public static final String AMP = "&amp;";

    /**
     * {@code "&quot;" -> "\""}
     */
    public static final String QUOTE = "&quot;";

    /**
     * {@code "&apos" -> "'"}
     */
    public static final String APOS = "&apos;";

    /**
     * {@code "&lt;" -> "<"}
     */
    public static final String LT = "&lt;";

    /**
     * {@code "&gt;" -> ">"}
     */
    public static final String GT = "&gt;";

}