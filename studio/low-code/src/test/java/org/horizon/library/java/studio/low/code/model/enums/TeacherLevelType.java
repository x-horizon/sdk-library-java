package org.horizon.library.java.studio.low.code.model.enums;

import com.mybatisflex.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 教师等级类型
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Getter
@AllArgsConstructor
public enum TeacherLevelType {

    ONE(1, "一级教师"),
    TWO(2, "二级教师"),
    THREE(3, "三级教师"),
    HIGH(4, "高级教师"),
    ;

    @EnumValue
    private final int code;

    private final String description;

}