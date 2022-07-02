package cn.srd.itcp.sugar.tools.exceptions;

/**
 * 自定义异常模板
 *
 * @author wjm
 * @date 2020/6/7 20:52
 */
public interface ExceptionTemplate {

    /**
     * 状态码
     *
     * @return
     */
    Integer getCode();

    /**
     * 状态码对应的描述信息
     *
     * @return
     */
    String getDescription();

    /**
     * 具体异常
     *
     * @return
     */
    Exception getException();

}
