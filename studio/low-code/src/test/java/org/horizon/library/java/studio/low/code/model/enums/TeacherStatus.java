package org.horizon.library.java.studio.low.code.model.enums;

import com.mybatisflex.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 教师状态
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Getter
@AllArgsConstructor
public enum TeacherStatus {

    ONLINE(1, "在职"),
    OFFLINE(2, "离职"),
    ;

    @EnumValue
    private final int code;

    private final String description;

}