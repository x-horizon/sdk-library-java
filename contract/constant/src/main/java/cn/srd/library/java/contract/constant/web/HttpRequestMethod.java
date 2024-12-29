package cn.srd.library.java.contract.constant.web;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * http request method
 *
 * @author wjm
 * @since 2020-06-09 14:25
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpRequestMethod {

    // --------------------------------------------------------- http 1.0 ---------------------------------------------------------

    /**
     * {@code "GET"}
     */
    public static final String GET = "GET";

    /**
     * {@code "POST"}
     */
    public static final String POST = "POST";

    /**
     * {@code "HEAD"}
     */
    public static final String HEAD = "HEAD";

    // --------------------------------------------------------- http 1.1 ---------------------------------------------------------

    /**
     * {@code "OPTIONS"}
     */
    public static final String OPTIONS = "OPTIONS";

    /**
     * {@code "PUT"}
     */
    public static final String PUT = "PUT";

    /**
     * {@code "DELETE"}
     */
    public static final String DELETE = "DELETE";

    /**
     * {@code "TRACE"}
     */
    public static final String TRACE = "TRACE";

    /**
     * {@code "CONNECT"}
     */
    public static final String CONNECT = "CONNECT";

}