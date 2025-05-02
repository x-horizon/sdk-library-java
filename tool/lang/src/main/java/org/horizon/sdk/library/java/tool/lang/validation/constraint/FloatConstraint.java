package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import java.io.Serial;

/**
 * constraint implementation for {@link Float} type numerical validation.
 *
 * <p>extends {@link NumberConstraint} to provide validation rules for single-precision
 * floating-point numbers. supports range checks, equality comparisons, and special value
 * handling (NaN, infinity) through inherited numerical operations.</p>
 *
 * <p>usage example:
 * <pre>{@code
 * FloatConstraint constraint = new FloatConstraint()
 *     .mustGreaterThan(0.5f)
 *     .mustLessThanOrEquals(10.5f);
 * }</pre></p>
 *
 * @author wjm
 * @see NumberConstraint
 * @since 2025-04-21 17:44
 */
public class FloatConstraint extends NumberConstraint<Float, Float, FloatConstraint> {

    @Serial private static final long serialVersionUID = -5630662206581478065L;

    /**
     * {@inheritDoc}
     *
     * <p>maintains a fluent api pattern by returning {@code this} instance for float-specific validation configurations</p>
     */
    @Override
    protected FloatConstraint toThis() {
        return this;
    }

}