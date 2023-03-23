package cn.srd.itcp.sugar.framework.spring.tool.common.core.page;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;

import java.io.Serial;
import java.io.Serializable;

/**
 * 分页参数
 *
 * @author wjm
 * @since 2020/01/05 13:45
 */
@Schema(description = "分页参数模型")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class PageParam implements Serializable {

    @Serial
    private static final long serialVersionUID = -2956893884714618641L;

    /**
     * 页码
     */
    @Schema(description = "页码", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小值为1")
    private Integer pageIndex;

    /**
     * 每页显示条数
     */
    @Schema(description = "每页显示条数", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    @NotNull(message = "每页显示条数不能为空")
    @Range(min = 1, max = 100, message = "每页显示条数范围为[1, 100]")
    private Integer pageSize;

}