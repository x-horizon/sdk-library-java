package cn.srd.itcp.sugar.tools.page;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author wjm
 * @date 2020/01/05 13:45
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PageParam implements Serializable {

    private static final long serialVersionUID = -2956893884714618641L;

    /**
     * 页码
     */
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小值为 1")
    private Integer pageIndex;

    /**
     * 每页显示条数
     */
    @NotNull(message = "每页条数不能为空")
    @Range(min = 1, max = 100, message = "条数范围为 [1, 100]")
    private Integer pageSize;

}