// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.constant.storage;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjm
 * @since 2024-07-19 15:45
 */
@Getter
@AllArgsConstructor
public enum StorageUnitType {

    BIT("Bit"),
    BYTE("Byte"),
    KB("KB"),
    MB("MB"),
    GB("GB"),
    TB("TB"),

    ;

    private final String value;

}