package cn.srd.library.java.tool.convert.all.mapstruct.converter;

import cn.srd.library.java.tool.convert.all.mapstruct.bean.domain.GradeDO;
import cn.srd.library.java.tool.convert.all.mapstruct.bean.domain.StudentDO;
import cn.srd.library.java.tool.convert.all.mapstruct.bean.domain.StudentUnsupportedMapstructConvertDO;
import cn.srd.library.java.tool.convert.all.mapstruct.bean.vo.StudentUnsupportedMapstructConvertVO;
import cn.srd.library.java.tool.convert.all.mapstruct.bean.vo.StudentVO;
import cn.srd.library.java.tool.convert.mapstruct.core.BindMapstruct;
import cn.srd.library.java.tool.convert.mapstruct.utils.MapstructMappingManager;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@BindMapstruct
@Mapper(componentModel = "spring", uses = MapstructMappingManager.class)
public interface StudentMapstructConverter {

    StudentMapstructConverter INSTANCE = Mappers.getMapper(StudentMapstructConverter.class);

    @Mappings({
            @Mapping(source = "id", target = "studentId"),
            @Mapping(source = "name", target = "studentName"),
            @Mapping(source = "age", target = "studentAge", ignore = true),
            @Mapping(source = "sex.number", target = "studentSexNumber"),
            @Mapping(source = "sex", target = "studentSexName"),
    })
    StudentVO toVO(StudentDO studentDO);

    @Mappings({
            @Mapping(source = "studentDO.id", target = "studentId"),
            @Mapping(source = "gradeDO.name", target = "studentName"),
            @Mapping(source = "studentDO.age", target = "studentAge", ignore = true),
            @Mapping(source = "studentDO.sex.number", target = "studentSexNumber"),
            @Mapping(source = "studentDO.sex", target = "studentSexName"),
    })
    StudentVO toVO(StudentDO studentDO, GradeDO gradeDO);

    @Mappings({
            @Mapping(source = "gradeDO1.id", target = "studentId"),
            @Mapping(source = "gradeDO2.name", target = "studentName"),
            @Mapping(source = "studentDO.age", target = "studentAge", ignore = true),
            @Mapping(source = "studentDO.sex.number", target = "studentSexNumber"),
            @Mapping(source = "studentDO.sex", target = "studentSexName"),
    })
    StudentVO toVO(StudentDO studentDO, GradeDO gradeDO1, GradeDO gradeDO2);

    @IterableMapping(elementTargetType = StudentVO.class)
    List<StudentVO> toVOs(List<StudentDO> entities);

    StudentUnsupportedMapstructConvertDO toUnsupportedConvertDO1(StudentUnsupportedMapstructConvertVO entity);

    StudentUnsupportedMapstructConvertDO toUnsupportedConvertDO2(StudentUnsupportedMapstructConvertVO entity);

    @IterableMapping(elementTargetType = StudentUnsupportedMapstructConvertVO.class)
    List<StudentUnsupportedMapstructConvertVO> toUnsupportedConvertVOs1(List<StudentUnsupportedMapstructConvertDO> entities);

    @IterableMapping(elementTargetType = StudentUnsupportedMapstructConvertVO.class)
    List<StudentUnsupportedMapstructConvertVO> toUnsupportedConvertVOs2(List<StudentUnsupportedMapstructConvertDO> entities);

}
