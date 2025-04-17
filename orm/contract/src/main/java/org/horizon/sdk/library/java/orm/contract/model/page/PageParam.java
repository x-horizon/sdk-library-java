package org.horizon.sdk.library.java.orm.contract.model.page;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.group.GroupSequenceProvider;
import org.horizon.sdk.library.java.contract.constant.page.PageConstant;
import org.horizon.sdk.library.java.contract.model.base.DTO;

import java.io.Serial;

/**
 * the page param model
 *
 * @author wjm
 * @since 2020-01-05 13:45
 */
@Schema(description = "page param model")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@GroupSequenceProvider(PageParamValidationGroup.class)
public class PageParam implements DTO {

    @Serial private static final long serialVersionUID = -2956893884714618641L;

    @Schema(description = "page index", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @Min(value = 1, message = "页码最小值为 1")
    @Builder.Default
    private Integer pageIndex = PageConstant.DEFAULT_PAGE_INDEX;

    @Schema(description = "record number per page", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    @Range(min = 1, max = 100, message = "每页显示条数最小值为 1，最大值为 100")
    @Builder.Default
    private Integer pageSize = PageConstant.DEFAULT_PAGE_SIZE;

    @Schema(description = "total record number, must > 0 if page index > 1", example = "999")
    @NotNull(message = "查询总数据量在页码大于 1 时必须大于 0", groups = PageParamValidationGroup.TotalRecordNumberValidator.class)
    private Long totalNumber;

}