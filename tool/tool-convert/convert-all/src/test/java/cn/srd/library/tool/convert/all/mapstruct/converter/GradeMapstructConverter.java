package cn.srd.library.tool.convert.all.mapstruct.converter;

import cn.srd.library.tool.convert.all.mapstruct.bean.domain.GradeDO;
import cn.srd.library.tool.convert.all.mapstruct.bean.vo.GradeVO;
import cn.srd.library.tool.convert.mapstruct.core.BindMapstruct;
import cn.srd.library.tool.convert.mapstruct.utils.MapstructMappingManager;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@BindMapstruct
@Mapper(componentModel = "spring", uses = MapstructMappingManager.class)
public interface GradeMapstructConverter {

    GradeMapstructConverter INSTANCE = Mappers.getMapper(GradeMapstructConverter.class);

    GradeVO toVO(GradeDO entity);

    default List<GradeVO> toVOs(List<GradeDO> entities) {
        return new ArrayList<GradeVO>() {
            @Serial private static final long serialVersionUID = 5374314977639990661L;

            {
                entities.forEach(entity -> add(toVO(entity)));
            }
        };
    }

}
