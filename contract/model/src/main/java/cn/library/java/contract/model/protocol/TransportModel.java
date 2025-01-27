package cn.library.java.contract.model.protocol;

import cn.library.java.contract.constant.web.HttpStatus;
import cn.library.java.contract.model.throwable.RunningException;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * define transport standard info
 *
 * @param <T> the transport data type
 * @author wjm
 * @since 2023-03-11 16:59
 */
public interface TransportModel<T> extends Serializable {

    /**
     * the transport status
     *
     * @return transport status
     */
    Integer getStatus();

    /**
     * the transport message
     *
     * @return transport message
     */
    String getMessage();

    /**
     * the transport data
     *
     * @return transport data
     */
    default T getData() {
        return null;
    }

    /**
     * return true if {@link HttpStatus#OK}
     *
     * @return true if {@link HttpStatus#OK}
     */
    default boolean successIs() {
        return null != getStatus() && HttpStatus.OK.getStatus() == getStatus();
    }

    /**
     * return true if not {@link HttpStatus#OK}
     *
     * @return true if not {@link HttpStatus#OK}
     */
    default boolean notSuccessIs() {
        return !this.successIs();
    }

    /**
     * throw {@link RunningException} if not success
     */
    default void requireSuccess() {
        ifNotSuccess(ignore -> {
            throw this.buildRunningException();
        });
    }

    /**
     * return {@link #getData()} after {@link #requireSuccess()}
     */
    default T requireSuccessAndGetData() {
        requireSuccess();
        return getData();
    }

    /**
     * do something after {@link #requireSuccess()}
     *
     * @param logic the logic
     */
    default void requireSuccessAndThen(Consumer<TransportModel<T>> logic) {
        requireSuccessAndThen(this, logic);
    }

    /**
     * do something after {@link #requireSuccess()}
     *
     * @param model the model extend {@link TransportModel}
     * @param logic the logic
     * @param <M>   {@link TransportModel} extension
     */
    default <M extends TransportModel<T>> void requireSuccessAndThen(M model, Consumer<M> logic) {
        requireSuccess();
        logic.accept(model);
    }

    /**
     * do something after {@link #notSuccessIs()}
     *
     * @param logic the logic
     */
    default void ifNotSuccess(Consumer<TransportModel<T>> logic) {
        if (notSuccessIs()) {
            logic.accept(this);
        }
    }

    /**
     * build {@link RunningException}
     *
     * @return {@link RunningException}
     */
    default RunningException buildRunningException() {
        RunningException runningException = new RunningException(getMessage());
        runningException.setStatus(getStatus());
        return runningException;
    }

}