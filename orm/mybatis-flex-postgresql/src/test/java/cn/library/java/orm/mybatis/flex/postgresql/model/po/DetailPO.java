package cn.library.java.orm.mybatis.flex.postgresql.model.po;

import cn.library.java.orm.mybatis.contract.postgresql.handler.JavaObjectMappingJdbcJsonbTypeHandler;
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

    private String name;

    @Override
    public boolean isNull() {
        return false;
    }

    public static class DetailPOMappingJsonbTypeHandler extends JavaObjectMappingJdbcJsonbTypeHandler<DetailPO> {

        @Override
        public Class<DetailPO> getTargetClass() {
            return DetailPO.class;
        }

    }

}