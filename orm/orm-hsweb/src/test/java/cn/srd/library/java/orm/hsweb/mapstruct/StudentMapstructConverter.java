package cn.srd.library.java.orm.hsweb.mapstruct;

import cn.srd.library.java.orm.hsweb.bean.domain.StudentDO;
import cn.srd.library.java.orm.hsweb.bean.vo.StudentVO;
import cn.srd.library.java.orm.hsweb.page.PageResult;
import cn.srd.library.java.tool.convert.mapstruct.core.BindMapstruct;
import cn.srd.library.java.tool.convert.mapstruct.utils.MapstructMappingManager;
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
