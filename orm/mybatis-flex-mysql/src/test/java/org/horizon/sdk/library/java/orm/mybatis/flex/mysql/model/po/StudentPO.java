package org.horizon.sdk.library.java.orm.mybatis.flex.mysql.model.po;

import com.mybatisflex.annotation.Table;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.horizon.sdk.library.java.contract.model.base.PO;
import org.horizon.sdk.library.java.orm.mybatis.flex.mysql.model.bo.StudentBO;
import org.horizon.sdk.library.java.orm.mybatis.flex.mysql.model.vo.StudentVO;
import org.horizon.sdk.library.java.tool.convert.api.Converts;

import java.io.Serial;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@AutoMappers({@AutoMapper(target = StudentBO.class), @AutoMapper(target = StudentVO.class)})
@Table(value = "student")
public class StudentPO extends StudentBO implements PO {

    @Serial private static final long serialVersionUID = 264523282982557895L;

    @Override
    public StudentVO toVO() {
        return Converts.onMapstruct().toBean(this, StudentVO.class);
    }

}