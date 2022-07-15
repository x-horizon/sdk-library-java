package cn.srd.itcp.sugar.tools.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果
 *
 * @author wjm
 * @date 2020/01/05 13:45
 */
@ApiModel(description = "分页结果模型")
@Data
@Accessors(chain = true)
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = -6490851620278359181L;

    @ApiModelProperty(value = "总条数", required = true, example = "1000")
    private Long total;

    @ApiModelProperty(value = "总页数", required = true, example = "100")
    private Long totalPages;

    @ApiModelProperty(value = "当前页码", required = true, example = "1")
    private Long currentPage;

    @ApiModelProperty(value = "每页显示条数", required = true, example = "10")
    private Long pageSize;

    @ApiModelProperty(value = "集合数据", required = true)
    private List<T> data;

    @ApiModelProperty(value = "对象数据", required = false)
    private Object datum;

}