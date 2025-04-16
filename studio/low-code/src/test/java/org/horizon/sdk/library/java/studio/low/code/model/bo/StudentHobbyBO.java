package org.horizon.sdk.library.java.studio.low.code.model.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.horizon.sdk.library.java.contract.model.base.BO;
import org.horizon.sdk.library.java.doc.knife4j.contract.constant.ApiDocConstant;
import org.horizon.sdk.library.java.pluggable.annotation.api.processor.field.metadata.FieldMetadata;
import org.horizon.sdk.library.java.studio.low.code.model.enums.StudentHobbyAchievementType;
import org.horizon.sdk.library.java.studio.low.code.model.enums.StudentHobbyParticipationLevelType;
import org.horizon.sdk.library.java.tool.convert.jackson.NullableObject;
import org.horizon.sdk.library.java.tool.convert.jackson.deserializer.JacksonEnumValueToEnumDeserializer;
import org.horizon.sdk.library.java.tool.convert.jackson.deserializer.JacksonListEnumValueToListEnumDeserializer;
import org.horizon.sdk.library.java.tool.convert.jackson.serializer.JacksonEnumToIntegerSerializer;
import org.horizon.sdk.library.java.tool.convert.jackson.serializer.JacksonListEnumToListIntegerSerializer;

import java.io.Serial;
import java.util.List;

/**
 * 学生兴趣爱好信息 model
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Schema(description = "学生兴趣爱好信息")
@FieldMetadata
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class StudentHobbyBO implements BO, NullableObject {

    @Serial private static final long serialVersionUID = -3729928508090089728L;

    private transient boolean isNull;

    @Schema(description = "主要兴趣", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.STRING)
    private String primaryInterestName;

    @Schema(description = "具体兴趣", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.LIST_STRING)
    private List<String> specificInterestNames;

    @Schema(description = "参与程度", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.NUMBER)
    @JsonSerialize(using = JacksonEnumToIntegerSerializer.class)
    @JsonDeserialize(using = JacksonEnumValueToEnumDeserializer.class)
    private StudentHobbyParticipationLevelType levelType;

    @Schema(description = "获得的成就类型", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.LIST_NUMBER)
    @JsonDeserialize(using = JacksonListEnumValueToListEnumDeserializer.class)
    @JsonSerialize(using = JacksonListEnumToListIntegerSerializer.class)
    private List<StudentHobbyAchievementType> achievementTypes;

    @Schema(description = "书本信息")
    @JsonProperty("bookInfo")
    private StudentHobbyBookBO bookBO;

    @Schema(description = "工具信息")
    @JsonProperty("toolInfos")
    private List<StudentHobbyToolBO> toolBOs;

}