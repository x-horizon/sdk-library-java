package cn.library.java.orm.mybatis.flex.postgresql.model.po;

import cn.library.java.orm.mybatis.contract.postgresql.handler.JavaListObjectMappingJdbcJsonbTypeHandler;
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

    @Serial private static final long serialVersionUID = 88531220073385451L;

    private Long id;

    private String name;

    @Override
    public boolean isNull() {
        return false;
    }

    public static class ClassPOMappingJsonbTypeHandler extends JavaListObjectMappingJdbcJsonbTypeHandler<ClassPO> {

        @Override
        public Class<ClassPO> getTargetClass() {
            return ClassPO.class;
        }

    }

}