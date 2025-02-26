package org.horizon.library.java.studio.low.code.model.vo;

import org.horizon.library.java.doc.knife4j.contract.constant.ApiDocConstant;
import org.horizon.library.java.orm.contract.model.base.BaseListConditionVO;
import org.horizon.library.java.studio.low.code.model.enums.StudentHobbyAchievementType;
import org.horizon.library.java.studio.low.code.model.enums.StudentHobbyParticipationLevelType;
import org.horizon.library.java.tool.convert.jackson.deserializer.JacksonEnumValueToEnumDeserializer;
import org.horizon.library.java.tool.convert.jackson.deserializer.JacksonListEnumValueToListEnumDeserializer;
import org.horizon.library.java.tool.convert.jackson.serializer.JacksonEnumToIntegerSerializer;
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
 * 学生列表查询条件信息 model
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class StudentListConditionVO extends BaseListConditionVO implements Serializable {

    @Serial private static final long serialVersionUID = -3730719478119777195L;

    @Schema(description = "名字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.STRING)
    private String name;

    @Schema(description = "学校名字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.LIST_NUMBER)
    private String schoolName;

    @Schema(description = "教师名字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.LIST_NUMBER)
    private String teacherName;

    @Schema(description = "课程-课程名字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.STRING)
    private String courseName;

    @Schema(description = "课程-课程学分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.NUMBER)
    private Short courseCredit;

    @Schema(description = "课程-书本-书本名字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.NUMBER)
    private String courseBookName;

    @Schema(description = "课程-工具-工具名字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.NUMBER)
    private String courseToolName;

    @Schema(description = "兴趣爱好-主要兴趣", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.STRING)
    private String hobbyPrimaryInterestName;

    @Schema(description = "兴趣爱好-具体兴趣", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.LIST_STRING)
    private String hobbySpecificInterestNames;

    @Schema(description = "兴趣爱好-参与程度", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.NUMBER)
    @JsonSerialize(using = JacksonEnumToIntegerSerializer.class)
    @JsonDeserialize(using = JacksonEnumValueToEnumDeserializer.class)
    private StudentHobbyParticipationLevelType hobbyParticipationLevelType;

    @Schema(description = "兴趣爱好-获得的成就类型", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.LIST_NUMBER)
    @JsonDeserialize(using = JacksonListEnumValueToListEnumDeserializer.class)
    @JsonSerialize(using = JacksonListEnumToListStringSerializer.class)
    private List<StudentHobbyAchievementType> hobbyAchievementTypes;

    @Schema(description = "兴趣爱好-书本-书本名字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.NUMBER)
    private String hobbyBookName;

    @Schema(description = "兴趣爱好-工具-工具名字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.NUMBER)
    private String hobbyToolName;

}