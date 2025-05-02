package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import java.io.Serial;

/**
 * specialized constraint implementation for validating {@link Enum} type values.
 *
 * <p>this class extends the base {@link Constraint} to provide enum-specific validation capabilities.
 * it enables validation rules like checking allowed enum constants or ensuring non-null enum values.</p>
 *
 * <p>typical usage example:
 * <pre>{@code
 * public enum Status { ACTIVE, INACTIVE }
 *
 * EnumConstraint<Status> constraint = new EnumConstraint<>();
 * constraint.mustNotNull().mustEquals(Status.ACTIVE);
 * }</pre></p>
 *
 * @param <V> the enum type being validated, must extend {@link Enum}
 * @author wjm
 * @see Constraint
 * @since 2025-04-22 00:35
 */
public class EnumConstraint<V extends Enum<V>> extends Constraint<V, EnumConstraint<V>> {

    @Serial private static final long serialVersionUID = -7904944752649918738L;

    /**
     * returns {@code this} instance to support fluent method chaining.
     *
     * <p>this implementation enables the constraint builder pattern by returning the concrete
     * enum constraint instance after each configuration method call.</p>
     *
     * @return current {@link EnumConstraint} instance
     * @see Constraint#toThis()
     */
    @Override
    protected EnumConstraint<V> toThis() {
        return this;
    }

}