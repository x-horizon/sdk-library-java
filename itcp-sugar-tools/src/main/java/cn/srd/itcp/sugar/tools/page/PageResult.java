package cn.srd.itcp.sugar.tools.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 分页结果
 *
 * @author wjm
 * @since 2020/01/05 13:45
 */
@ApiModel(description = "分页结果模型")
@Data
@Accessors(chain = true)
public class PageResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -6490851620278359181L;

    /**
     * 总条数
     */
    @ApiModelProperty(value = "总条数", required = true, example = "1000")
    private Long total;

    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数", required = true, example = "100")
    private Long totalPages;

    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页码", required = true, example = "1")
    private Long currentPage;

    /**
     * 每页显示条数
     */
    @ApiModelProperty(value = "每页显示条数", required = true, example = "10")
    private Long pageSize;

    /**
     * 集合数据
     */
    @ApiModelProperty(value = "集合数据", required = true)
    private List<T> data;

    /**
     * 对象数据
     */
    @ApiModelProperty(value = "对象数据", required = false)
    private Object datum;

}