package org.horizon.sdk.library.java.tool.lang.validation.constraint;

/**
 * constraint implementation for {@link Long} type numerical validation.
 *
 * <p>extends {@link NumberConstraint} to provide 64-bit integer validation rules including
 * range comparisons, equality checks, and numerical boundaries. handles long integer
 * validation scenarios with type-safe operations.</p>
 *
 * <p>usage example:
 * <pre>{@code
 * LongConstraint constraint = new LongConstraint()
 *     .mustGreaterThan(0L)
 *     .mustLessThanOrEquals(10L);
 * }</pre></p>
 *
 * @author wjm
 * @see NumberConstraint
 * @since 2025-04-21 17:44
 */
public class LongConstraint extends NumberConstraint<Long, Long, LongConstraint> {

    /**
     * {@inheritDoc}
     *
     * <p>enables a fluent api pattern by returning {@code this} instance to maintain chained validation configuration for long integer rules</p>
     */
    @Override
    protected LongConstraint toThis() {
        return this;
    }

}