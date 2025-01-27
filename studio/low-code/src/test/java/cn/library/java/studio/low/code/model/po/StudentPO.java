package cn.library.java.studio.low.code.model.po;

import cn.library.java.contract.model.base.PO;
import cn.library.java.studio.low.code.model.bo.StudentBO;
import cn.library.java.studio.low.code.model.vo.StudentVO;
import cn.library.java.tool.convert.api.Converts;
import com.mybatisflex.annotation.Table;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 学生信息 model
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
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