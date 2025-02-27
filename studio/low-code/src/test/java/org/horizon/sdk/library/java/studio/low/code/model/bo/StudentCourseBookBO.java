package org.horizon.sdk.library.java.studio.low.code.model.bo;

import org.horizon.sdk.library.java.contract.model.base.BO;
import org.horizon.sdk.library.java.doc.knife4j.contract.constant.ApiDocConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 学生课程书本信息 model
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Schema(description = "学生课程书本信息")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class StudentCourseBookBO implements BO {

    @Serial private static final long serialVersionUID = -3314867672446353894L;

    @Schema(description = "名字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.STRING)
    private String name;

}