package cn.srd.library.java.orm.mybatis.flex.base.model.po;

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
public class DetailPO implements NullableObject, Serializable {

    @Serial private static final long serialVersionUID = 2742055391867234666L;

    @Override
    public boolean isNull() {
        return false;
    }

    private String name;

    private Short age;

}