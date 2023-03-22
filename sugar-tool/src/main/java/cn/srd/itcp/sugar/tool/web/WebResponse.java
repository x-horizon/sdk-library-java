package cn.srd.itcp.sugar.tool.web;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 * web response
 *
 * @param <T> 响应结果类型
 * @author wjm
 * @since 2020/01/05 13:45
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public final class WebResponse<T> implements ResponseModel<T>, Serializable {

    @Serial
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
     * @param <T> 响应数据类型
     * @return 响应实例
     */
    public static <T> WebResponse<T> success() {
        return response(HttpStatusEnum.SUCCESS.getCode(), HttpStatusEnum.SUCCESS.getDescription(), null);
    }

    /**
     * 响应正确信息
     *
     * @param data 自定义响应数据，字段名为 data
     * @param <T>  响应数据类型
     * @return 响应实例
     */
    public static <T> WebResponse<T> success(T data) {
        return response(HttpStatusEnum.SUCCESS.getCode(), HttpStatusEnum.SUCCESS.getDescription(), data);
    }

    /**
     * 响应正确信息
     *
     * @param message 自定义响应信息，字段名为 message
     * @param <T>     响应数据类型
     * @return 响应实例
     */
    public static <T> WebResponse<T> success(String message) {
        return response(HttpStatusEnum.SUCCESS.getCode(), message, null);
    }

    /**
     * 响应正确信息
     *
     * @param message 自定义响应信息，字段名为 message
     * @param data    自定义响应数据，字段名为 data
     * @param <T>     响应数据类型
     * @return 响应实例
     */
    public static <T> WebResponse<T> success(String message, T data) {
        return response(HttpStatusEnum.SUCCESS.getCode(), message, data);
    }

    /**
     * 响应错误信息
     *
     * @param <T> 响应数据类型
     * @return 响应实例
     */
    public static <T> WebResponse<T> error() {
        return response(HttpStatusEnum.INTERNAL_ERROR.getCode(), HttpStatusEnum.INTERNAL_ERROR.getDescription(), null);
    }

    /**
     * 响应错误信息
     *
     * @param data 自定义响应数据，字段名为 data
     * @param <T>  响应数据类型
     * @return 响应实例
     */
    public static <T> WebResponse<T> error(T data) {
        return response(HttpStatusEnum.INTERNAL_ERROR.getCode(), HttpStatusEnum.INTERNAL_ERROR.getDescription(), data);
    }

    /**
     * 响应错误信息
     *
     * @param message 自定义响应信息，字段名为 message
     * @param <T>     响应数据类型
     * @return 响应实例
     */
    public static <T> WebResponse<T> error(String message) {
        return response(HttpStatusEnum.INTERNAL_ERROR.getCode(), message, null);
    }

    /**
     * 响应错误信息
     *
     * @param message 自定义响应信息，字段名为 message
     * @param data    自定义响应数据，字段名为 data
     * @param <T>     响应数据类型
     * @return 响应实例
     */
    public static <T> WebResponse<T> error(String message, T data) {
        return response(HttpStatusEnum.INTERNAL_ERROR.getCode(), message, data);
    }

    /**
     * 响应错误信息
     *
     * @param httpStatusEnum 自定义状态码、响应信息
     * @param <T>            响应数据类型
     * @return 响应实例
     */
    public static <T> WebResponse<T> error(HttpStatusEnum httpStatusEnum) {
        return response(httpStatusEnum.getCode(), httpStatusEnum.getDescription(), null);
    }

    /**
     * 响应错误信息
     *
     * @param status  自定义状态码
     * @param message 自定义响应信息，字段名为 message
     * @param <T>     响应数据类型
     * @return 响应实例
     */
    public static <T> WebResponse<T> error(Integer status, String message) {
        return response(status, message, null);
    }

    /**
     * 响应错误信息
     *
     * @param httpStatusEnum 自定义状态码
     * @param message        自定义响应信息，字段名为 message
     * @param <T>            响应数据类型
     * @return 响应实例
     */
    public static <T> WebResponse<T> error(HttpStatusEnum httpStatusEnum, String message) {
        return response(httpStatusEnum.getCode(), message, null);
    }

    /**
     * 响应信息
     *
     * @param httpStatusEnum 自定义状态码、响应信息
     * @param data           自定义响应数据，字段名为 data
     * @param <T>            响应数据类型
     * @return 响应实例
     */
    public static <T> WebResponse<T> response(HttpStatusEnum httpStatusEnum, T data) {
        return WebResponse.<T>builder()
                .status(httpStatusEnum.getCode())
                .message(httpStatusEnum.getDescription())
                .data(data)
                .build();
    }

    /**
     * 响应信息
     *
     * @param status  自定义状态码
     * @param message 自定义响应信息，字段名为 message
     * @param data    自定义响应数据，字段名为 data
     * @param <T>     响应数据类型
     * @return 响应实例
     */
    public static <T> WebResponse<T> response(Integer status, String message, T data) {
        return WebResponse.<T>builder()
                .status(status)
                .message(message)
                .data(data)
                .build();
    }

}
