package org.horizon.sdk.library.java.orm.mybatis.flex.mysql.model.vo;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.horizon.sdk.library.java.contract.model.base.VO;
import org.horizon.sdk.library.java.orm.mybatis.flex.mysql.model.bo.StudentBO;
import org.horizon.sdk.library.java.orm.mybatis.flex.mysql.model.po.StudentPO;
import org.horizon.sdk.library.java.tool.convert.api.Converts;

import java.io.Serial;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@AutoMappers({@AutoMapper(target = StudentBO.class), @AutoMapper(target = StudentPO.class)})
public class StudentVO extends StudentBO implements VO {

    @Serial private static final long serialVersionUID = -5249617111496549985L;

    @Override
    public StudentPO toPO() {
        return Converts.onMapstruct().toBean(this, StudentPO.class);
    }

}