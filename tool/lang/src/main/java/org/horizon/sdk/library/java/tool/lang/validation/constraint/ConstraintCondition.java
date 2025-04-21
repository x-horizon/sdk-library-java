package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import java.util.function.Predicate;

/**
 * @author wjm
 * @since 2025-04-21 21:36
 */
@FunctionalInterface
public interface ConstraintCondition<M> extends Predicate<M> {

    default ConstraintConditionAdaptor<M> toAdaptor() {
        return (model, ignore) -> this.test(model);
    }

}