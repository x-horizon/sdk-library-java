package cn.srd.library.java.orm.mybatis.flex.postgresql.model.enums;

import com.mybatisflex.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MaterialType {

    WOODINESS(1),
    STEEL(2),

    ;

    @EnumValue
    private final int code;

}
