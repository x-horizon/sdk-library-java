package org.horizon.sdk.library.java.orm.contract.model.base;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.horizon.sdk.library.java.contract.model.base.POJO;
import org.horizon.sdk.library.java.doc.knife4j.contract.constant.ApiDocConstant;

import java.io.Serial;

/**
 * @author wjm
 * @since 2024-04-17 10:42
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class BaseGetConditionVO implements POJO {

    @Serial private static final long serialVersionUID = -1483563030718449313L;

    @Schema(description = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.NUMBER)
    @NotNull(message = "“id”不可为空")
    private Long id;

}