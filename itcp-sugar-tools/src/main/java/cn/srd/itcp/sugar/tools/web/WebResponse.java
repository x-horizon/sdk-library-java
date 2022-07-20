package cn.srd.itcp.sugar.tools.web;

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
        return response(HttpStatusEnum.SUCCESS.getCode(), HttpStatusEnum.SUCCESS.getDescription(), data);
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
        return response(HttpStatusEnum.INTERNAL_ERROR.getCode(), HttpStatusEnum.INTERNAL_ERROR.getDescription(), data);
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
     * @param httpStatusEnum 自定义状态码、响应信息
     * @param data           自定义响应数据，字段名为 data
     * @param <T>
     * @return
     */
    public static <T> WebResponse<T> response(HttpStatusEnum httpStatusEnum, T data) {
        WebResponse<T> result = new WebResponse<>();
        result.status = httpStatusEnum.getCode();
        result.message = httpStatusEnum.getDescription();
        result.data = data;
        return result;
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
