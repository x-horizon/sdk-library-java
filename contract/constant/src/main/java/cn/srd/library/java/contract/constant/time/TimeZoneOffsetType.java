// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.constant.time;

import lombok.Getter;

/**
 * time zone offset type
 *
 * @author wjm
 * @since 2024-07-09 10:02
 */
@Getter
public enum TimeZoneOffsetType {

    EAST_EIGHTH("+08:00"),

    ;

    TimeZoneOffsetType(String name) {
        this.value = name;
    }

    private final String value;

}