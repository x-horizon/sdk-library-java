package cn.srd.itcp.sugar.tool.web;

import cn.srd.itcp.sugar.tool.core.Objects;
import cn.srd.itcp.sugar.tool.exceptions.RunningException;

/**
 * root response model
 *
 * @author wjm
 * @since 2023-03-11 16:59:12
 */
public interface ResponseModel<T> {

    /**
     * 获取状态码
     *
     * @return 状态码
     */
    Integer getStatus();

    /**
     * 获取响应数据
     *
     * @return 响应数据
     */
    T getData();

    /**
     * 获取响应信息
     *
     * @return 响应信息
     */
    String getMessage();

    /**
     * 是否响应成功
     *
     * @return 是否响应成功
     */
    default boolean successIs() {
        return Objects.equals(HttpStatusEnum.SUCCESS.getCode(), getStatus());
    }

    /**
     * 是否响应失败
     *
     * @return 是否响应失败
     */
    default boolean errorIs() {
        return !this.successIs();
    }

    /**
     * 构造 {@link RunningException}
     *
     * @return {@link RunningException}
     */
    default RunningException buildRunningException() {
        return new RunningException(this.getStatus(), this.getMessage());
    }

}
