package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import java.io.Serial;

/**
 * constraint implementation for {@link Short} type numerical validation.
 *
 * <p>extends {@link NumberConstraint} to provide short integer-specific numerical validation rules.
 * supports range checks (greater than/less than), equality validation, and other numeric operations
 * for 16-bit integer values.</p>
 *
 * <p>usage example:
 * <pre>{@code
 * ShortConstraint constraint = new ShortConstraint()
 *     .mustGreaterThan((short) 0)
 *     .mustLessThanOrEquals((short) 10);
 * }</pre></p>
 *
 * @author wjm
 * @see NumberConstraint
 * @since 2025-04-21 17:44
 */
public class ShortConstraint extends NumberConstraint<Short, Short, ShortConstraint> {

    @Serial private static final long serialVersionUID = 7148525027780726711L;

    /**
     * {@inheritDoc}
     *
     * <p>maintains fluent api chaining by returning {@code this} instance for short-specific validation rules</p>
     */
    @Override
    protected ShortConstraint toThis() {
        return this;
    }

}