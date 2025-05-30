package org.horizon.sdk.library.java.studio.low.code.model.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.horizon.sdk.library.java.contract.model.base.BO;
import org.horizon.sdk.library.java.doc.knife4j.contract.constant.ApiDocConstant;
import org.horizon.sdk.library.java.pluggable.annotation.api.processor.field.metadata.FieldMetadata;

import java.io.Serial;

/**
 * 学生爱好书本信息 model
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Schema(description = "学生爱好书本信息")
@FieldMetadata
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class StudentHobbyBookBO implements BO {

    @Serial private static final long serialVersionUID = -265466574012022330L;

    @Schema(description = "名字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.STRING)
    private String name;

}