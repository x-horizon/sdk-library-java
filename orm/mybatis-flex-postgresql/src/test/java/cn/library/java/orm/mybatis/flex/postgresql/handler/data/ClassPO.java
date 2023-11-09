package cn.library.java.orm.mybatis.flex.postgresql.handler.data;

import cn.srd.library.java.tool.convert.jackson.NullableObject;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class ClassPO implements NullableObject, Serializable {

    @Serial private static final long serialVersionUID = 6362978914842925299L;

    @Override
    public boolean isNull() {
        return false;
    }

    private Long id;

    private String name;

}