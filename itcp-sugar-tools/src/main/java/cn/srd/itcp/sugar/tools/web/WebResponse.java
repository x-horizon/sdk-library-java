package cn.srd.itcp.sugar.tools.web;

import cn.srd.itcp.sugar.tools.core.asserts.WarnAssert;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * webmvc 响应结果
 *
 * @param <T>
 * @author wjm
 * @date 2020/01/05 13:45
 */
@Getter
@Setter
public final class WebResponse<T> implements Serializable {

    private static final long serialVersionUID = 7838683692331475145L;

    /**
     * <pre>
     * {@link WarnAssert}
     * MpBaseMapper
     * 用于截取信息，例如当查数据库查到一条数据不存在时，会抛出例如“数据不存在，id：**，调用者信息：**”
     * 此时后台会打印 warn 的完整信息，而 mvc 只响应部分信息如：“数据不存在，id：**”
     * </pre>
     *
     * @return
     */
    public static final String MARKED_STACK_TRACE_MSG = ", 调用位置: ";

    /**
     * 状态码
     */
    private Integer status;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应正确信息
     *
     * @param <T>
     * @return
     */
    public static <T> WebResponse<T> success() {
        return response(HttpStatusEnum.SUCCESS.getCode(), HttpStatusEnum.SUCCESS.getDescription(), null);
    }

    /**
     * 响应正确信息
     *
     * @param data 自定义响应数据，字段名为 data
     * @param <T>
     * @return
     */
    public static <T> WebResponse<T> success(T data) {
        return response(HttpStatusEnum.SUCCESS.getCode(), null, data);
    }

    /**
     * 响应正确信息
     *
     * @param message 自定义响应信息，字段名为 message
     * @param <T>
     * @return
     */
    public static <T> WebResponse<T> success(String message) {
        return response(HttpStatusEnum.SUCCESS.getCode(), message, null);
    }

    /**
     * 响应正确信息
     *
     * @param message 自定义响应信息，字段名为 message
     * @param data    自定义响应数据，字段名为 data
     * @param <T>
     * @return
     */
    public static <T> WebResponse<T> success(String message, T data) {
        return response(HttpStatusEnum.SUCCESS.getCode(), message, data);
    }

    /**
     * 响应错误信息
     *
     * @param <T>
     * @return
     */
    public static <T> WebResponse<T> error() {
        return response(HttpStatusEnum.INTERNAL_ERROR.getCode(), HttpStatusEnum.INTERNAL_ERROR.getDescription(), null);
    }

    /**
     * 响应错误信息
     *
     * @param data 自定义响应数据，字段名为 data
     * @param <T>
     * @return
     */
    public static <T> WebResponse<T> error(T data) {
        return response(HttpStatusEnum.INTERNAL_ERROR.getCode(), null, data);
    }

    /**
     * 响应错误信息
     *
     * @param message 自定义响应信息，字段名为 message
     * @param <T>
     * @return
     */
    public static <T> WebResponse<T> error(String message) {
        return response(HttpStatusEnum.INTERNAL_ERROR.getCode(), message, null);
    }

    /**
     * 响应错误信息
     *
     * @param message 自定义响应信息，字段名为 message
     * @param data    自定义响应数据，字段名为 data
     * @param <T>
     * @return
     */
    public static <T> WebResponse<T> error(String message, T data) {
        return response(HttpStatusEnum.INTERNAL_ERROR.getCode(), message, data);
    }

    /**
     * 响应错误信息
     *
     * @param status  自定义状态码
     * @param message 自定义响应信息，字段名为 message
     * @param <T>
     * @return
     */
    public static <T> WebResponse<T> error(Integer status, String message) {
        return response(status, message, null);
    }

    /**
     * 响应错误信息
     *
     * @param httpStatusEnum 自定义状态码
     * @param message        自定义响应信息，字段名为 message
     * @param <T>
     * @return
     */
    public static <T> WebResponse<T> error(HttpStatusEnum httpStatusEnum, String message) {
        return response(httpStatusEnum.getCode(), message, null);
    }

    /**
     * 响应错误信息
     *
     * @param httpStatusEnum 自定义状态码、响应信息
     * @param <T>
     * @return
     */
    public static <T> WebResponse<T> error(HttpStatusEnum httpStatusEnum) {
        return response(httpStatusEnum.getCode(), httpStatusEnum.getDescription(), null);
    }

    /**
     * 响应信息
     *
     * @param status  自定义状态码
     * @param message 自定义响应信息，字段名为 message
     * @param data    自定义响应数据，字段名为 data
     * @param <T>
     * @return
     */
    public static <T> WebResponse<T> response(Integer status, String message, T data) {
        WebResponse<T> result = new WebResponse<>();
        result.status = status;
        result.message = message;
        result.data = data;
        return result;
    }

}
