package org.horizon.library.java.studio.low.code.model.po;

import org.horizon.library.java.contract.model.base.PO;
import org.horizon.library.java.studio.low.code.model.bo.TeacherBO;
import org.horizon.library.java.studio.low.code.model.vo.TeacherVO;
import org.horizon.library.java.tool.convert.api.Converts;
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
 * 教师信息 model
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@AutoMappers({@AutoMapper(target = TeacherBO.class), @AutoMapper(target = TeacherVO.class)})
@Table(value = "teacher")
public class TeacherPO extends TeacherBO implements PO {

    @Serial private static final long serialVersionUID = -5169963718660974805L;

    @Override
    public TeacherVO toVO() {
        return Converts.onMapstruct().toBean(this, TeacherVO.class);
    }

}