// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.constant.time;

import lombok.Getter;

/**
 * time zone type
 *
 * @author wjm
 * @since 2022-11-14 21:16
 */
@Getter
public enum TimeZoneType {

    SHANG_HAI("Asia/Shanghai"),
    UTC("UTC"),

    ;

    TimeZoneType(String name) {
        this.value = name;
    }

    private final String value;

}