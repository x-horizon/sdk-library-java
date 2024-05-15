// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.constant.time;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * time pattern constant
 *
 * @author wjm
 * @since 2022-11-14 21:16
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimePatternConstant {

    /**
     * {@code "HH:mm"}
     */
    public static final String HOUR_MINUTE = "HH:mm";

    /**
     * {@code "HH:mm:ss"}
     */
    public static final String HOUR_MINUTE_SECOND = "HH:mm:ss";

    /**
     * {@code "yyyy-MM-dd HH:mm"}
     */
    public static final String DATETIME_WITHOUT_SECOND = "yyyy-MM-dd HH:mm";

    /**
     * {@code "yyyy-MM-dd HH:mm:ss"}
     */
    public static final String DATETIME_MS0 = "yyyy-MM-dd HH:mm:ss";

    /**
     * {@code "yyyy-MM-dd HH:mm:ss.S"}
     */
    public static final String DATETIME_MS1 = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * {@code "yyyy-MM-dd HH:mm:ss.SS"}
     */
    public static final String DATETIME_MS2 = "yyyy-MM-dd HH:mm:ss.SS";

    /**
     * {@code "yyyy-MM-dd HH:mm:ss.SSS"}
     */
    public static final String DATETIME_MS3 = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * {@code "yyyy-MM-dd HH:mm:ss.SSSS"}
     */
    public static final String DATETIME_MS4 = "yyyy-MM-dd HH:mm:ss.SSSS";

    /**
     * {@code "yyyy-MM-dd HH:mm:ss.SSSSS"}
     */
    public static final String DATETIME_MS5 = "yyyy-MM-dd HH:mm:ss.SSSSS";

    /**
     * {@code "yyyy-MM-dd HH:mm:ss.SSSSSS"}
     */
    public static final String DATETIME_MS6 = "yyyy-MM-dd HH:mm:ss.SSSSSS";

    /**
     * east eighth RFC3339 standard: {@code "yyyy-MM-dd'T'HH:mm:ss+08:00"}
     */
    public static final String DATETIME_RFC3339_EAST_EIGHTH_TIMEZONE = "yyyy-MM-dd'T'HH:mm:ss" + TimeZoneConstant.EAST_EIGHTH;

}