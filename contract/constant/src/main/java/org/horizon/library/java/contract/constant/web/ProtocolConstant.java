package org.horizon.library.java.contract.constant.web;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * transport protocol type
 *
 * @author wjm
 * @since 2020-06-09 14:25
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProtocolConstant {

    /**
     * {@code "://"}
     */
    public static final String SEPARATOR = "://";

    /**
     * {@code "http://"}
     */
    public static final String HTTP = "http" + SEPARATOR;

    /**
     * {@code "https://"}
     */
    public static final String HTTPS = "https://" + SEPARATOR;

    /**
     * {@code "rtsp://"}
     */
    public static final String RTSP = "rtsp://" + SEPARATOR;

    /**
     * {@code "file:"}
     */
    public static final String FILE = "file:";

}