package cn.srd.itcp.sugar.hsweb.curd.mapstruct;

import cn.srd.itcp.sugar.convert.mapstruct.core.BindMapstruct;
import cn.srd.itcp.sugar.convert.mapstruct.core.util.MapstructMappingManager;
import cn.srd.itcp.sugar.hsweb.curd.bean.domain.StudentDO;
import cn.srd.itcp.sugar.hsweb.curd.bean.vo.StudentVO;
import cn.srd.itcp.sugar.hsweb.curd.page.PageResult;
import org.hswebframework.web.api.crud.entity.PagerResult;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@BindMapstruct
@Mapper(componentModel = "spring", uses = MapstructMappingManager.class)
public interface StudentMapstructConverter {

    StudentMapstructConverter INSTANCE = Mappers.getMapper(StudentMapstructConverter.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "age", target = "age"),
    })
    StudentVO toVO(StudentDO studentDO);

    @Named("toVOs")
    @IterableMapping(elementTargetType = StudentVO.class)
    List<StudentVO> toVOs(List<StudentDO> entities);

    @Mappings({
            @Mapping(source = "data", target = "data", qualifiedByName = "toVOs"),
    })
    PageResult<StudentVO> toPageVO(PagerResult<StudentDO> page);

}
