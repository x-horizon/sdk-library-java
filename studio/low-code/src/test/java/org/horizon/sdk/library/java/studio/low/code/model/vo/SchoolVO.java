package org.horizon.sdk.library.java.studio.low.code.model.vo;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.horizon.sdk.library.java.contract.model.base.VO;
import org.horizon.sdk.library.java.studio.low.code.model.bo.SchoolBO;
import org.horizon.sdk.library.java.studio.low.code.model.po.SchoolPO;
import org.horizon.sdk.library.java.tool.convert.api.Converts;

import java.io.Serial;

/**
 * 学校信息 model
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@AutoMappers({@AutoMapper(target = SchoolBO.class), @AutoMapper(target = SchoolPO.class)})
public class SchoolVO extends SchoolBO implements VO {

    @Serial private static final long serialVersionUID = -8003642639221864103L;

    @Override
    public SchoolPO toPO() {
        return Converts.onMapstruct().toBean(this, SchoolPO.class);
    }

}