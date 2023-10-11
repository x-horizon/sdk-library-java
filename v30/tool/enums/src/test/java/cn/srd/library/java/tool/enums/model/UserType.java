// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.enums.model;

import cn.srd.library.java.tool.enums.autowired.EnumAutowired;
import lombok.Getter;

@Getter
@EnumAutowired(rootClass = UserStrategy.class)
public enum UserType {

    STUDENT(1, "student"),
    TEACHER(2, "teacher"),

    ;

    UserType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    private final int code;

    private final String description;

    private UserStrategy strategy;

}
