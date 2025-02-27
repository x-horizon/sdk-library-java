package org.horizon.sdk.library.java.studio.low.code.model.enums;

import com.mybatisflex.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 教师课程类型
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Getter
@AllArgsConstructor
public enum TeacherCourseType {

    CHINESE("语文"),
    MATH("数学"),
    ENGLISH("英语"),
    ;

    @EnumValue
    private final String code;

}