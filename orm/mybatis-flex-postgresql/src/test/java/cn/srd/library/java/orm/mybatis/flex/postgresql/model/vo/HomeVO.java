package cn.srd.library.java.orm.mybatis.flex.postgresql.model.vo;

import cn.srd.library.java.contract.model.base.VO;
import cn.srd.library.java.orm.mybatis.flex.postgresql.model.bo.HomeBO;
import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.HomePO;
import cn.srd.library.java.tool.convert.api.Converts;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

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