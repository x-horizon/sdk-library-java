package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import org.horizon.sdk.library.java.tool.lang.functional.SerializablePredicate;

/**
 * functional interface for defining custom validation conditions with model-based predicates.
 *
 * <p>serves as bridge between standard {@link SerializablePredicate} and validation framework by providing
 * adaptor conversion. primarily used to create reusable validation conditions that can be adapted
 * to different validation contexts.</p>
 *
 * @param <M> the type of model object being validated
 * @author wjm
 * @see ConstraintConditionAdaptor
 * @since 2025-04-21 21:36
 */
@FunctionalInterface
public interface ConstraintCondition<M> extends SerializablePredicate<M> {

    /**
     * converts current condition to {@link ConstraintConditionAdaptor} for validation integration.
     *
     * <p>this default implementation creates an adaptor that ignores context parameter while maintaining original predicate logic. enables compatibility with validation workflows requiring both model and context parameters.</p>
     *
     * @return adaptor instance wrapping current condition logic
     */
    default ConstraintConditionAdaptor<M> toAdaptor() {
        return (model, ignore) -> this.test(model);
    }

}