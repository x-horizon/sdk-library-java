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
public enum JobType {

    A(1),
    B(2),
    C(3),
    ;

    @EnumValue
    private final int code;

}