// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.constant.time;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * time unit constant
 *
 * @author wjm
 * @since 2022-11-14 21:16
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUnitConstant {

    /**
     * millisecond unit: {@code 1000}
     */
    public static final int MILLISECOND = 1000;

    /**
     * second unit: {@code 60}
     */
    public static final int SECOND = 60;

    /**
     * minute unit: {@code 60}
     */
    public static final short MINUTE = 60;

    /**
     * hour unit: {@code 24}
     */
    public static final short HOUR = 24;

    /**
     * one hour second
     */
    public static final int ONE_HOUR_SECOND = 3600;

}
