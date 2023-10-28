// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.constant.web;

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
     * {@code "http://"}
     */
    public static final String HTTP = "http://";

    /**
     * {@code "https://"}
     */
    public static final String HTTPS = "https://";

    /**
     * {@code "rtsp://"}
     */
    public static final String RTSP = "rtsp://";

    /**
     * {@code "file:"}
     */
    public static final String FILE = "file:";

}
