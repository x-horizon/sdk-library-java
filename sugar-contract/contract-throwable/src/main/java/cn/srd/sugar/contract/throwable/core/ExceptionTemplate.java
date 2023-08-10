package cn.srd.sugar.contract.throwable.core;

/**
 * 自定义异常模板
 *
 * @author wjm
 * @since 2020/6/7 20:52
 */
public interface ExceptionTemplate {

    /**
     * 状态码
     *
     * @return 状态码
     */
    Integer getCode();

    /**
     * 状态码对应的描述信息
     *
     * @return 状态码对应的描述信息
     */
    String getDescription();

    /**
     * 具体异常
     *
     * @return 具体异常
     */
    Exception getException();

}
