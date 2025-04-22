package org.horizon.sdk.library.java.tool.lang.validation.constraint;

/**
 * constraint implementation for {@link Double} type numerical validation.
 *
 * <p>extends {@link NumberConstraint} to provide validation rules for double-precision
 * floating-point numbers. supports range checks, equality comparisons, and special value
 * handling (NaN, infinity) through numerical operations' inheritance.</p>
 *
 * <p>usage example:
 * <pre>{@code
 * DoubleConstraint constraint = new DoubleConstraint()
 *     .mustGreaterThan(0.55d)
 *     .mustLessThanOrEquals(10.5d);
 * }</pre></p>
 *
 * @author wjm
 * @see NumberConstraint
 * @since 2025-04-21 17:44
 */
public class DoubleConstraint extends NumberConstraint<Double, Double, DoubleConstraint> {

    /**
     * {@inheritDoc}
     *
     * <p>enables fluent api pattern by returning {@code this} instance to maintain chained validation configuration for double-precision rules</p>
     */
    @Override
    protected DoubleConstraint toThis() {
        return this;
    }

}