package cn.srd.itcp.sugar.framework.spring.tool.common.core.page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 分页结果
 *
 * @author wjm
 * @since 2020/01/05 13:45
 */
@Schema(description = "分页结果模型")
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class PageResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -6490851620278359181L;

    /**
     * 总条数
     */
    @Schema(description = "总条数", requiredMode = Schema.RequiredMode.REQUIRED, example = "1000")
    private Long total;

    /**
     * 总页数
     */
    @Schema(description = "总页数", requiredMode = Schema.RequiredMode.REQUIRED, example = "100")
    private Long totalPages;

    /**
     * 当前页码
     */
    @Schema(description = "当前页码", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long currentPage;

    /**
     * 每页显示条数
     */
    @Schema(description = "每页显示条数", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    private Long pageSize;

    /**
     * 集合数据
     */
    @Schema(description = "集合数据", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<T> data;

    /**
     * 对象数据
     */
    @Schema(description = "对象数据", required = false)
    private Object datum;

}