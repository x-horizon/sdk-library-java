package cn.srd.sugar.tool.convert.all.mapstruct.mapstruct;

import cn.srd.sugar.tool.convert.all.mapstruct.bean.domain.GradeDO;
import cn.srd.sugar.tool.convert.all.mapstruct.bean.vo.GradeVO;
import cn.srd.sugar.tool.convert.mapstruct.core.BindMapstruct;
import cn.srd.sugar.tool.convert.mapstruct.utils.MapstructMappingManager;
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
