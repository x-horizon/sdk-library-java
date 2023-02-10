package cn.srd.itcp.sugar.component.convert.all.mapstruct.mapstruct;

import cn.srd.itcp.sugar.component.convert.all.mapstruct.bean.domain.GradeDO;
import cn.srd.itcp.sugar.component.convert.all.mapstruct.bean.vo.GradeVO;
import cn.srd.itcp.sugar.component.convert.mapstruct.core.BindMapstruct;
import cn.srd.itcp.sugar.component.convert.mapstruct.utils.MapstructMappingManager;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@BindMapstruct
@Mapper(componentModel = "spring", uses = MapstructMappingManager.class)
public interface GradeMapstructConverter {

    GradeMapstructConverter INSTANCE = Mappers.getMapper(GradeMapstructConverter.class);

    GradeVO toVO(GradeDO entity);

    default List<GradeVO> toVOs(List<GradeDO> entities) {
        return new ArrayList<GradeVO>() {{
            entities.forEach(entity -> add(toVO(entity)));
        }};
    }

}
