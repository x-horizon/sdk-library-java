package cn.sugar.convert.all.mapstruct.mapstruct;

import cn.sugar.convert.mapstruct.core.BindMapstruct;
import cn.sugar.convert.mapstruct.core.util.MapstructMappingManager;
import cn.sugar.convert.all.mapstruct.bean.domain.GradeDO;
import cn.sugar.convert.all.mapstruct.bean.vo.GradeVO;
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
