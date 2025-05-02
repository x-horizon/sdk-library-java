package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import java.io.Serial;

/**
 * constraint implementation for {@link Byte} type numerical validation.
 *
 * <p>extends {@link NumberConstraint} to provide byte-specific numerical validation rules
 * including range checks (greater than/less than) and equality validations. this
 * specialization ensures type-safe validation for byte values with proper numeric
 * comparison handling.</p>
 *
 * <p>usage example:
 * <pre>{@code
 * ByteConstraint constraint = new ByteConstraint()
 *     .mustGreaterThan((byte) 0)
 *     .mustLessThanOrEquals((byte) 10);
 * }</pre></p>
 *
 * @author wjm
 * @see NumberConstraint
 * @since 2025-04-21 17:44
 */
public class ByteConstraint extends NumberConstraint<Byte, Byte, ByteConstraint> {

    @Serial private static final long serialVersionUID = 6392273892555853858L;

    /**
     * {@inheritDoc}
     *
     * <p>concrete implementation returns {@code this} instance to maintain fluent chaining for byte-specific validation rules</p>
     */
    @Override
    protected ByteConstraint toThis() {
        return this;
    }

}