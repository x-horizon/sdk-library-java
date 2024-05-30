// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.enums.model;

import cn.srd.library.java.tool.enums.EnumAutowired;
import lombok.Getter;

@Getter
@EnumAutowired(rootClass = GenderStrategy.class)
public enum GenderType {

    WOMAN(1, "woman"),
    MAN(2, "man"),
    UNKNOWN(3, "unknown"),

    ;

    GenderType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    private final int code;

    private final String description;

    private GenderStrategy strategy;

}