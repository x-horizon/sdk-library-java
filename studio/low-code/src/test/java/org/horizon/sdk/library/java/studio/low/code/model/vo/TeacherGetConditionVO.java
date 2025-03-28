package org.horizon.sdk.library.java.studio.low.code.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.horizon.sdk.library.java.doc.knife4j.contract.constant.ApiDocConstant;
import org.horizon.sdk.library.java.orm.contract.model.base.BaseGetConditionVO;

import java.io.Serial;
import java.io.Serializable;

/**
 * 教师详情查询条件信息 model
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class TeacherGetConditionVO extends BaseGetConditionVO implements Serializable {

    @Serial private static final long serialVersionUID = 3598075624766929249L;

    @Schema(description = "名字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.STRING)
    private String name;

}