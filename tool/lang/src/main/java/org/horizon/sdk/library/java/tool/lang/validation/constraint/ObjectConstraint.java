package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import java.io.Serial;

/**
 * constraint implementation for {@link Object} validation with string-specific rules.
 *
 * <p>this class extends the base {@link Constraint} to provide object-specific validation capabilities.</p>
 *
 * <p>typical usage example:
 * <pre>{@code
 * ObjectConstraint<AccountVO> constraint = new ObjectConstraint<>();
 * constraint.mustNotNull().mustEquals(...);
 * }</pre></p>
 *
 * @author wjm
 * @since 2025-05-03 21:32
 */
public class ObjectConstraint extends Constraint<Object, ObjectConstraint> {

    @Serial private static final long serialVersionUID = -9160825614091246995L;

    @Override
    protected ObjectConstraint toThis() {
        return this;
    }

}