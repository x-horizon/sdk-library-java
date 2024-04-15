package cn.srd.library.java.orm.mybatis.flex.postgresql.model.enums;

import com.mybatisflex.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjm
 * @since 2023-11-04 00:19
 */
@Getter
@AllArgsConstructor
public enum MaterialType {

    WOODINESS(1),
    STEEL(2),

    ;

    @EnumValue
    private final int code;

}