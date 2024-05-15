// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.constant.time;

import lombok.Getter;

/**
 * time unit type
 *
 * @author wjm
 * @since 2023-02-11 17:54
 */
@Getter
public enum TimeUnitType {

    NANOSECOND("ns", "NS", "nanosecond", "Nanosecond", "NANOSECOND", "纳秒"),
    MICROSECOND("microsecond", "Microsecond", "MICROSECOND", "微秒"),
    MILLISECOND("ms", "MS", "millisecond", "Millisecond", "MILLISECOND", "毫秒"),
    SECOND("s", "S", "second", "Second", "SECOND", "秒"),
    MINUTE("m", "M", "minute", "Minute", "MINUTE", "分"),
    HOUR("h", "H", "hour", "Hour", "HOUR", "时", "小时"),
    DAY("d", "D", "day", "Day", "DAY", "天"),
    MONTH("month", "Month", "MONTH", "月"),
    YEAR("y", "Y", "year", "Year", "YEAR", "年"),

    ;

    TimeUnitType(String... names) {
        this.names = names;
    }

    private final String[] names;

}