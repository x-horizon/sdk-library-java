package cn.library.java.studio.low.code.model.vo;

import cn.library.java.contract.model.base.VO;
import cn.library.java.studio.low.code.model.bo.TeacherBO;
import cn.library.java.studio.low.code.model.po.TeacherPO;
import cn.library.java.tool.convert.api.Converts;
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
@AutoMappers({@AutoMapper(target = TeacherBO.class), @AutoMapper(target = TeacherPO.class)})
public class TeacherVO extends TeacherBO implements VO {

    @Serial private static final long serialVersionUID = -6211058936120808600L;

    @Override
    public TeacherPO toPO() {
        return Converts.onMapstruct().toBean(this, TeacherPO.class);
    }

}