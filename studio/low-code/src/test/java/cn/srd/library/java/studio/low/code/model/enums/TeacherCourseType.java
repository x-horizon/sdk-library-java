// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.studio.low.code.model.enums;

import com.mybatisflex.annotation.EnumValue;
import lombok.Getter;

/**
 * 教师课程类型
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Getter
public enum TeacherCourseType {

    CHINESE(1, "语文"),
    MATH(2, "数学"),
    ENGLISH(3, "英语"),
    ;

    TeacherCourseType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    @EnumValue
    private final int code;

    private final String description;

}