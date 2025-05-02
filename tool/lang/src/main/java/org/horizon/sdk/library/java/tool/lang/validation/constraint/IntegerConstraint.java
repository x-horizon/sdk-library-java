package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import java.io.Serial;

/**
 * constraint implementation for {@link Integer} type numerical validation.
 *
 * <p>extends {@link NumberConstraint} to provide integer-specific validation rules including
 * range checks, equality comparisons, and numerical boundaries. handles 32-bit integer
 * validation scenarios with type-safe operations.</p>
 *
 * <p>usage example:
 * <pre>{@code
 * IntegerConstraint constraint = new IntegerConstraint()
 *     .mustGreaterThan(0)
 *     .mustLessThanOrEquals(10);
 * }</pre></p>
 *
 * @author wjm
 * @see NumberConstraint
 * @since 2025-04-21 17:44
 */
public class IntegerConstraint extends NumberConstraint<Integer, Integer, IntegerConstraint> {

    @Serial private static final long serialVersionUID = 3089323689455601676L;

    /**
     * {@inheritDoc}
     *
     * <p>maintains fluent api chaining by returning the current {@code IntegerConstraint} instance after each validation configuration</p>
     */
    @Override
    protected IntegerConstraint toThis() {
        return this;
    }

}