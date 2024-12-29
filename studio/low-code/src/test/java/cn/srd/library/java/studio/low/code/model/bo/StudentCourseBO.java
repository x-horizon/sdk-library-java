package cn.srd.library.java.studio.low.code.model.bo;

import cn.srd.library.java.contract.model.base.BO;
import cn.srd.library.java.doc.knife4j.contract.constant.ApiDocConstant;
import cn.srd.library.java.tool.convert.jackson.NullableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.util.List;

/**
 * 学生课程信息 model
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Schema(description = "学生课程信息")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class StudentCourseBO implements BO, NullableObject {

    @Serial private static final long serialVersionUID = -7236373958148461707L;

    private transient boolean isNull;

    @Schema(description = "名字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.STRING)
    private String name;

    @Schema(description = "学分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.NUMBER)
    private Short credit;

    @Schema(description = "书本信息")
    @JsonProperty("bookInfo")
    private StudentCourseBookBO bookBO;

    @Schema(description = "工具信息")
    @JsonProperty("toolInfos")
    private List<StudentCourseToolBO> toolBOs;

}