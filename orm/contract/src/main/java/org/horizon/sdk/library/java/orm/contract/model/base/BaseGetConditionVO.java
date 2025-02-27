package org.horizon.sdk.library.java.orm.contract.model.base;

import org.horizon.sdk.library.java.contract.model.base.POJO;
import org.horizon.sdk.library.java.doc.knife4j.contract.constant.ApiDocConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

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
    private Long id;

}