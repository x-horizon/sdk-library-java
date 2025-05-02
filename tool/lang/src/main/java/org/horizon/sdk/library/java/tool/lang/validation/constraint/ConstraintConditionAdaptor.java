package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import org.horizon.sdk.library.java.tool.lang.functional.SerializableBiPredicate;
import org.horizon.sdk.library.java.tool.lang.validation.support.ValidationGroup;

/**
 * functional interface adapting validation conditions with context-aware validation groups.
 *
 * <p>extends {@link SerializableBiPredicate} to validate models against both the target object and validation groups.
 * serves as a bridge between validation rules and group-based validation scenarios, allowing conditional
 * validation execution based on active validation groups.</p>
 *
 * @param <T> the type of model object being validated or extra
 * @author wjm
 * @see ConstraintCondition
 * @see ValidationGroup
 * @since 2025-04-21 21:36
 */
@FunctionalInterface
public interface ConstraintConditionAdaptor<T> extends SerializableBiPredicate<T, ValidationGroup[]> {

}