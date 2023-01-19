package cn.srd.itcp.sugar.tools.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * 分页参数
 *
 * @author wjm
 * @since 2020/01/05 13:45
 */
@ApiModel(description = "分页参数模型")
@Data
@Accessors(chain = true)
public class PageParam implements Serializable {

    private static final long serialVersionUID = -2956893884714618641L;

    @ApiModelProperty(value = "页码", required = true, example = "1")
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小值为1")
    private Integer pageIndex;

    @ApiModelProperty(value = "每页显示条数", required = true, example = "10")
    @NotNull(message = "每页显示条数不能为空")
    @Range(min = 1, max = 100, message = "每页显示条数范围为[1, 100]")
    private Integer pageSize;

}