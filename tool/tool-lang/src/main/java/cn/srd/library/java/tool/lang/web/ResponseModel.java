package cn.srd.library.java.tool.lang.web;

import cn.srd.library.java.contract.throwable.core.RunningException;
import cn.srd.library.java.tool.lang.core.object.Objects;

import java.util.function.Consumer;

/**
 * root response model
 *
 * @author wjm
 * @since 2023-03-11 16:59:12
 */
public interface ResponseModel<T> {

    /**
     * get status
     *
     * @return 状态码
     */
    Integer getStatus();

    /**
     * get data
     *
     * @return 响应数据
     */
    T getData();

    /**
     * get message
     *
     * @return 响应信息
     */
    String getMessage();

    /**
     * return true if {@link HttpStatusEnum#SUCCESS}
     *
     * @return 是否响应成功
     */
    default boolean successIs() {
        return Objects.equals(HttpStatusEnum.SUCCESS.getCode(), getStatus());
    }

    /**
     * return true if not {@link HttpStatusEnum#SUCCESS}
     *
     * @return 是否响应失败
     */
    default boolean errorIs() {
        return !this.successIs();
    }

    /**
     * throw a {@link RunningException} if not success
     */
    default void requireSuccess() {
        notSuccessAndThen(value -> {
            throw this.buildRunningException();
        });
    }

    /**
     * do something after {@link #requireSuccess()}
     *
     * @param logic the logic
     */
    default void requireSuccessAndThen(Consumer<ResponseModel<T>> logic) {
        requireSuccessAndThen(this, logic);
    }

    /**
     * do something after {@link #requireSuccess()}
     *
     * @param model the model extend {@link ResponseModel}
     * @param logic the logic
     * @param <M>   {@link ResponseModel} extension
     */
    default <M extends ResponseModel<T>> void requireSuccessAndThen(M model, Consumer<M> logic) {
        requireSuccess();
        logic.accept(model);
    }

    /**
     * do something after {@link #errorIs()}
     *
     * @param logic the logic
     */
    default void notSuccessAndThen(Consumer<ResponseModel<T>> logic) {
        if (errorIs()) {
            logic.accept(this);
        }
    }

    /**
     * build {@link RunningException}
     *
     * @return {@link RunningException}
     */
    default RunningException buildRunningException() {
        return new RunningException(this.getStatus(), this.getMessage());
    }

}
