package cn.library.java.studio.low.code.model.enums;

import com.mybatisflex.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 学校类型
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Getter
@AllArgsConstructor
public enum SchoolType {

    PRIMARY(1, "小学"),
    MIDDLE(2, "中学"),
    UNIVERSITY(3, "大学"),
    ;

    @EnumValue
    private final int code;

    private final String description;

}