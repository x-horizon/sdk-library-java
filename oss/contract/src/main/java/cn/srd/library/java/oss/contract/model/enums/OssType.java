// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.oss.contract.model.enums;

import cn.srd.library.java.oss.contract.strategy.OssStorage;
import cn.srd.library.java.tool.enums.EnumAutowired;
import cn.srd.library.java.tool.enums.strategy.EnumAutowiredFieldMatchByContainIgnoreCaseRule;
import lombok.Getter;

/**
 * @author wjm
 * @since 2024-07-16 19:45
 */
@Getter
@EnumAutowired(rootClasses = OssStorage.class, allowNull = true, matchRule = EnumAutowiredFieldMatchByContainIgnoreCaseRule.class)
public enum OssType {

    LOCAL(1, "local"),
    MINIO(2, "minio"),

    ;

    OssType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    private final int code;

    private final String description;

    private OssStorage storage;

}