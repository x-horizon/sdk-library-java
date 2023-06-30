package cn.srd.itcp.sugar.tool.core.validation;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

import java.util.function.UnaryOperator;

/**
 * the validator support chain of responsibility
 *
 * @author wjm
 * @since 2023-06-27 16:41:11
 */
public interface Validator<T> {

    /**
     * get the validated object
     *
     * @return the validated object
     */
    T get();

    /**
     * modify the validated object
     *
     * @param input the validated object
     */
    void set(T input);

    /**
     * validate success or not in current validator node
     *
     * @return validate success or not
     */
    boolean isSuccess();

    /**
     * validate failed or not in current validator node
     *
     * @return validate failed or not
     */
    default boolean isFailed() {
        return !isSuccess();
    }

    /**
     * get next validator node
     *
     * @return next validator node
     */
    Validator<T> getNextValidator();

    /**
     * the validate logic
     *
     * @return validate success or not
     */
    boolean doValidate();

    /**
     * construct the validator chain and do validate
     *
     * @return current validator
     */
    @CanIgnoreReturnValue
    default Validator<T> validate() {
        if (doValidate()) {
            return getNextValidator().validate();
        }
        return this;
    }

    /**
     * do something when validate failed on any validator node
     *
     * @param failLogic the fail logic
     * @return current validator
     */
    @CanIgnoreReturnValue
    default Validator<T> failAndThen(UnaryOperator<T> failLogic) {
        if (isFailed()) {
            set(failLogic.apply(get()));
        }
        return this;
    }

    /**
     * do something when validate success on all validator node
     *
     * @param successLogic the success logic
     * @return current validator
     */
    @CanIgnoreReturnValue
    default Validator<T> successAndThen(UnaryOperator<T> successLogic) {
        if (isSuccess()) {
            set(successLogic.apply(get()));
        }
        return this;
    }

    /**
     * do something whether validate success or fail
     *
     * @param logic the logic
     * @return current validator
     */
    @CanIgnoreReturnValue
    default Validator<T> finallyAndThen(UnaryOperator<T> logic) {
        set(logic.apply(get()));
        return this;
    }

}
