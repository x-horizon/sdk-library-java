package cn.library.java.orm.mybatis.flex.postgresql.handler.data;

import com.mybatisflex.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
