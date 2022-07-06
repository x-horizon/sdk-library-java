package cn.srd.itcp.sugar.mybatis.plus.mapstruct;

import cn.srd.itcp.sugar.convert.mapstruct.core.BindMapstruct;
import cn.srd.itcp.sugar.convert.mapstruct.utils.MapstructMappingManager;
import cn.srd.itcp.sugar.mybatis.plus.bean.domain.StudentDO;
import cn.srd.itcp.sugar.mybatis.plus.bean.vo.StudentVO;
import cn.srd.itcp.sugar.tools.page.PageResult;
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
            @Mapping(source = "total", target = "amount"),
            @Mapping(source = "pages", target = "pages"),
            @Mapping(source = "current", target = "currentPage"),
            @Mapping(source = "size", target = "pageSize"),
            @Mapping(source = "records", target = "records", qualifiedByName = "toVOs"),
            @Mapping(target = "datum", ignore = true),
    })
    PageResult<StudentVO> toPageVO(IPage<StudentDO> page);

}
