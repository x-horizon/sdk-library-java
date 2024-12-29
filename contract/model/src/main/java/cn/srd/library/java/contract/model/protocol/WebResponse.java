package cn.srd.library.java.contract.model.protocol;

import cn.srd.library.java.contract.constant.web.HttpStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * web response model
 *
 * @param <T> the response data type
 * @author wjm
 * @since 2020-01-05 13:45
 */
@Data
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WebResponse<T> implements TransportModel<T> {

    @Serial private static final long serialVersionUID = 879304484973014674L;

    private Integer status;

    private String message;

    private T data;

    /**
     * build a success web response model
     *
     * @param <T> the response model type
     * @return response model
     */
    public static <T> WebResponse<T> success() {
        return response(HttpStatus.OK, null);
    }

    /**
     * build a success web response model
     *
     * @param data the response data
     * @param <T>  the response model type
     * @return response model
     */
    public static <T> WebResponse<T> success(T data) {
        return response(HttpStatus.OK, data);
    }

    /**
     * build a success web response model
     *
     * @param message the response message
     * @param <T>     the response model type
     * @return response model
     */
    public static <T> WebResponse<T> success(String message) {
        return success(message, null);
    }

    /**
     * build a success web response model
     *
     * @param message the response message
     * @param data    the response data
     * @param <T>     the response model type
     * @return response model
     */
    public static <T> WebResponse<T> success(String message, T data) {
        return response(HttpStatus.OK.getStatus(), message, data);
    }

    /**
     * build a failed web response model
     *
     * @param <T> the response model type
     * @return response model
     */
    public static <T> WebResponse<T> error() {
        return response(HttpStatus.INTERNAL_ERROR, null);
    }

    /**
     * build a failed web response model
     *
     * @param data the response data
     * @param <T>  the response model type
     * @return response model
     */
    public static <T> WebResponse<T> error(T data) {
        return response(HttpStatus.INTERNAL_ERROR, data);
    }

    /**
     * build a failed web response model
     *
     * @param message the response message
     * @param <T>     the response model type
     * @return response model
     */
    public static <T> WebResponse<T> error(String message) {
        return error(message, null);
    }

    /**
     * build a failed web response model
     *
     * @param message the response message
     * @param data    the response data
     * @param <T>     the response model type
     * @return response model
     */
    public static <T> WebResponse<T> error(String message, T data) {
        return response(HttpStatus.INTERNAL_ERROR.getStatus(), message, data);
    }

    /**
     * build a failed web response model
     *
     * @param httpStatus the response code、响应信息
     * @param <T>        the response model type
     * @return response model
     */
    public static <T> WebResponse<T> error(HttpStatus httpStatus) {
        return response(httpStatus, null);
    }

    /**
     * build a failed web response model
     *
     * @param status  the response code
     * @param message the response message
     * @param <T>     the response model type
     * @return response model
     */
    public static <T> WebResponse<T> error(Integer status, String message) {
        return response(status, message, null);
    }

    /**
     * build a failed web response model
     *
     * @param httpStatus the response code
     * @param message    the response message
     * @param <T>        the response model type
     * @return response model
     */
    public static <T> WebResponse<T> error(HttpStatus httpStatus, String message) {
        return response(httpStatus.getStatus(), message, null);
    }

    /**
     * build a web response model
     *
     * @param httpStatus the response code、响应信息
     * @param data       the response data
     * @param <T>        the response model type
     * @return response model
     */
    public static <T> WebResponse<T> response(HttpStatus httpStatus, T data) {
        return response(httpStatus.getStatus(), httpStatus.getDescription(), data);
    }

    /**
     * build a web response model
     *
     * @param status  the response code
     * @param message the response message
     * @param data    the response data
     * @param <T>     the response model type
     * @return response model
     */
    public static <T> WebResponse<T> response(Integer status, String message, T data) {
        return WebResponse.<T>builder()
                .status(status)
                .message(message)
                .data(data)
                .build();
    }

}