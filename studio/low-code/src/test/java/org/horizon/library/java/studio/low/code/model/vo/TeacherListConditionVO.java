package org.horizon.library.java.studio.low.code.model.vo;

import org.horizon.library.java.doc.knife4j.contract.constant.ApiDocConstant;
import org.horizon.library.java.orm.contract.model.base.BaseListConditionVO;
import org.horizon.library.java.studio.low.code.model.enums.TeacherCourseType;
import org.horizon.library.java.studio.low.code.model.enums.TeacherLevelType;
import org.horizon.library.java.studio.low.code.model.enums.TeacherStatus;
import org.horizon.library.java.tool.convert.jackson.deserializer.JacksonEnumValueToEnumDeserializer;
import org.horizon.library.java.tool.convert.jackson.deserializer.JacksonListEnumValueToListEnumDeserializer;
import org.horizon.library.java.tool.convert.jackson.serializer.JacksonEnumToIntegerSerializer;
import org.horizon.library.java.tool.convert.jackson.serializer.JacksonListEnumToListIntegerSerializer;
import org.horizon.library.java.tool.convert.jackson.serializer.JacksonListEnumToListStringSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 教师列表查询条件信息 model
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class TeacherListConditionVO extends BaseListConditionVO implements Serializable {

    @Serial private static final long serialVersionUID = 1676836699387822114L;

    @Schema(description = "学校id", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.LIST_NUMBER)
    private Long schoolId;

    @Schema(description = "名字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.STRING)
    private String name;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.NUMBER)
    @JsonSerialize(using = JacksonEnumToIntegerSerializer.class)
    @JsonDeserialize(using = JacksonEnumValueToEnumDeserializer.class)
    private TeacherStatus status;

    @Schema(description = "等级类型", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.LIST_NUMBER)
    @JsonDeserialize(using = JacksonListEnumValueToListEnumDeserializer.class)
    @JsonSerialize(using = JacksonListEnumToListIntegerSerializer.class)
    private List<TeacherLevelType> levelTypes;

    @Schema(description = "课程类型", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.LIST_STRING)
    @JsonDeserialize(using = JacksonListEnumValueToListEnumDeserializer.class)
    @JsonSerialize(using = JacksonListEnumToListStringSerializer.class)
    private List<TeacherCourseType> courseTypes;

}