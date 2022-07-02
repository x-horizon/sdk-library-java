package cn.srd.itcp.sugar.tools.page;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author wjm
 * @date 2020/01/05 13:45
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = -6490851620278359181L;

    /**
     * 总条数
     */
    private Long amount;

    /**
     * 总页数
     */
    private Long pages;

    /**
     * 当前页码
     */
    private Long currentPage;

    /**
     * 每页显示条数
     */
    private Long pageSize;

    /**
     * 集合数据
     */
    private List<T> records;

    /**
     * 对象数据
     */
    private Object datum;

}