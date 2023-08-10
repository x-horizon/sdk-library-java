package cn.srd.sugar.orm.mybatis.plus.convert.mapstruct;

import cn.srd.itcp.sugar.doc.knife4j.model.PageResult;
import cn.srd.sugar.orm.mybatis.plus.convert.bean.domain.StudentDO;
import cn.srd.sugar.orm.mybatis.plus.convert.bean.vo.StudentVO;
import cn.srd.sugar.tool.convert.mapstruct.core.BindMapstruct;
import cn.srd.sugar.tool.convert.mapstruct.utils.MapstructMappingManager;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
            @Mapping(source = "pages", target = "totalPages"),
            @Mapping(source = "current", target = "currentPage"),
            @Mapping(source = "size", target = "pageSize"),
            @Mapping(source = "records", target = "data", qualifiedByName = "toVOs"),
            @Mapping(target = "datum", ignore = true),
    })
    PageResult<StudentVO> toPageVO(IPage<StudentDO> page);

}
