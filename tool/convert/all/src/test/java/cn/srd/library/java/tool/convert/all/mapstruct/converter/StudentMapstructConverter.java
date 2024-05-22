// // Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// // Use of this source code is governed by SRD.
// // license that can be found in the LICENSE file.
//
// package cn.srd.library.java.tool.convert.all.mapstruct.converter;
//
// import cn.srd.library.java.tool.convert.all.mapstruct.model.domain.GradeDO;
// import cn.srd.library.java.tool.convert.all.mapstruct.model.domain.StudentDO;
// import cn.srd.library.java.tool.convert.all.mapstruct.model.vo.StudentVO;
// import cn.srd.library.java.tool.convert.mapstruct.support.MapstructMappingManager;
// import org.mapstruct.IterableMapping;
// import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;
// import org.mapstruct.factory.Mappers;
//
// import java.util.List;
//
// @Mapper(componentModel = "spring", uses = MapstructMappingManager.class)
// public interface StudentMapstructConverter {
//
//     StudentMapstructConverter INSTANCE = Mappers.getMapper(StudentMapstructConverter.class);
//
//     @Mapping(source = "id", target = "studentId")
//     @Mapping(source = "name", target = "studentName")
//     @Mapping(source = "age", target = "studentAge", ignore = true)
//     @Mapping(source = "sex.number", target = "studentSexNumber")
//     @Mapping(source = "sex", target = "studentSexName")
//     StudentVO toVO(StudentDO studentDO);
//
//     @Mapping(source = "studentDO.id", target = "studentId")
//     @Mapping(source = "gradeDO.name", target = "studentName")
//     @Mapping(source = "studentDO.age", target = "studentAge", ignore = true)
//     @Mapping(source = "studentDO.sex.number", target = "studentSexNumber")
//     @Mapping(source = "studentDO.sex", target = "studentSexName")
//     StudentVO toVO(StudentDO studentDO, GradeDO gradeDO);
//
//     @Mapping(source = "gradeDO1.id", target = "studentId")
//     @Mapping(source = "gradeDO2.name", target = "studentName")
//     @Mapping(source = "studentDO.age", target = "studentAge", ignore = true)
//     @Mapping(source = "studentDO.sex.number", target = "studentSexNumber")
//     @Mapping(source = "studentDO.sex", target = "studentSexName")
//     StudentVO toVO(StudentDO studentDO, GradeDO gradeDO1, GradeDO gradeDO2);
//
//     @IterableMapping(elementTargetType = StudentVO.class)
//     List<StudentVO> toVOs(List<StudentDO> entities);
//
// }