package org.horizon.sdk.library.java.orm.mybatis.flex.mysql.model.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.horizon.sdk.library.java.doc.knife4j.contract.constant.ApiDocConstant;
import org.horizon.sdk.library.java.orm.contract.mybatis.base.mysql.handler.JdbcJsonMappingJavaEntityTypeHandler;
import org.horizon.sdk.library.java.orm.contract.mybatis.base.mysql.handler.JdbcJsonMappingJavaListEntityTypeHandler;
import org.horizon.sdk.library.java.orm.contract.mybatis.base.mysql.handler.JdbcJsonMappingJavaListLongTypeHandler;
import org.horizon.sdk.library.java.orm.contract.mybatis.flex.model.bo.BaseWithVersionBO;
import org.horizon.sdk.library.java.orm.mybatis.flex.mysql.model.po.StudentPO;
import org.horizon.sdk.library.java.orm.mybatis.flex.mysql.model.vo.StudentVO;
import org.horizon.sdk.library.java.tool.convert.jackson.serializer.JacksonListLongToListStringSerializer;
import org.horizon.sdk.library.java.tool.convert.jackson.serializer.JacksonLongToStringSerializer;

import java.io.Serial;
import java.util.List;

@Schema(description = "学生信息")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@AutoMappers({@AutoMapper(target = StudentPO.class), @AutoMapper(target = StudentVO.class)})
public class StudentBO extends BaseWithVersionBO {

    @Serial private static final long serialVersionUID = 2234235631313555403L;

    @Schema(description = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.NUMBER)
    @Column(value = "id")
    @Id
    @JsonSerialize(using = JacksonLongToStringSerializer.class)
    private Long id;

    @Schema(description = "学校id", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.NUMBER)
    @Column(value = "school_id")
    @JsonSerialize(using = JacksonLongToStringSerializer.class)
    private Long schoolId;

    @Schema(description = "教师id", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.LIST_NUMBER)
    @Column(value = "teacher_ids", typeHandler = JdbcJsonMappingJavaListLongTypeHandler.class)
    @JsonSerialize(using = JacksonListLongToListStringSerializer.class)
    private List<Long> teacherIds;

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.STRING)
    @Column(value = "code")
    private String code;

    @Schema(description = "名字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.STRING)
    @Column(value = "name")
    private String name;

    @Schema(description = "排序", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.NUMBER)
    @Column(value = "sort")
    private Integer sort;

    @Schema(description = "兴趣爱好信息")
    @Column(value = "hobby_info", typeHandler = JdbcJsonMappingJavaEntityTypeHandler.class)
    @JsonProperty("hobbyInfo")
    private StudentHobbyBO hobbyBO;

    @Schema(description = "课程信息")
    @Column(value = "course_infos", typeHandler = JdbcJsonMappingJavaListEntityTypeHandler.class)
    @JsonProperty("courseInfos")
    private List<StudentCourseBO> courseBOs;

}