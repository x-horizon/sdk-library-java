package org.horizon.sdk.library.java.orm.mybatis.flex.tdengine.model.vo;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.horizon.sdk.library.java.contract.model.base.VO;
import org.horizon.sdk.library.java.orm.mybatis.flex.tdengine.model.bo.HomeBO;
import org.horizon.sdk.library.java.orm.mybatis.flex.tdengine.model.po.HomePO;
import org.horizon.sdk.library.java.tool.convert.api.Converts;

import java.io.Serial;

/**
 * @author wjm
 * @since 2024-04-15 18:44
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@AutoMappers({@AutoMapper(target = HomeBO.class), @AutoMapper(target = HomePO.class)})
public class HomeVO extends HomeBO implements VO {

    @Serial private static final long serialVersionUID = 1030055421739967275L;

    @Override
    public HomePO toPO() {
        return Converts.onMapstruct().toBean(this, HomePO.class);
    }

}