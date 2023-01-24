package cn.srd.itcp.sugar.hsweb.curd.page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 *
 * @author wjm
 * @since 2022/6/18 19:17
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PageResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 5999982405361066022L;

    /**
     * 数据总量
     */
    @Schema(description = "数据总量")
    private Long total;

    /**
     * 总页数
     */
    @Schema(description = "总页数")
    private Long pages;

    /**
     * 页码
     */
    @Schema(description = "页码")
    private Long pageIndex;

    /**
     * 每页数据量
     */
    @Schema(description = "每页数据量")
    private Long pageSize;

    /**
     * 数据列表
     */
    @Schema(description = "数据列表")
    private List<T> data;

    /**
     * 单个数据
     */
    @Schema(description = "单个数据")
    private Object datum;

}