package cn.srd.library.java.orm.mybatis.flex.model.enums;

import com.mybatisflex.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StudentType {

    A(1),
    B(2),
    C(3),
    ;

    @EnumValue
    private final int code;

}
