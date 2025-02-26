package org.horizon.library.java.orm.contract.model.base;

import org.horizon.library.java.contract.model.base.POJO;
import org.horizon.library.java.doc.knife4j.contract.constant.ApiDocConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.util.List;

/**
 * @author wjm
 * @since 2024-04-17 10:42
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class BaseListConditionVO implements POJO {

    @Serial private static final long serialVersionUID = -5642714146557455163L;

    @Schema(description = "ids", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.LIST_NUMBER)
    private List<Long> ids;

}