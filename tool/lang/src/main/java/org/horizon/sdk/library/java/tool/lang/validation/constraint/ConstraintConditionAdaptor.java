package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import org.horizon.sdk.library.java.tool.lang.validation.support.ValidationGroup;

import java.util.function.BiPredicate;

/**
 * @author wjm
 * @since 2025-04-21 21:36
 */
@FunctionalInterface
public interface ConstraintConditionAdaptor<T> extends BiPredicate<T, ValidationGroup[]> {

}