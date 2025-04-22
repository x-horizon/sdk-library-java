package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import org.horizon.sdk.library.java.contract.model.base.POJO;

/**
 * constraint implementation for {@link POJO} validation with string-specific rules.
 *
 * <p>this class extends the base {@link Constraint} to provide pojo-specific validation capabilities.</p>
 *
 * <p>typical usage example:
 * <pre>{@code
 * PojoConstraint<AccountVO> constraint = new PojoConstraint<>();
 * constraint.mustNotNull().mustEquals(...);
 * }</pre></p>
 *
 * @author wjm
 * @since 2025-04-22 16:51
 */
public class PojoConstraint<V extends POJO> extends Constraint<V, PojoConstraint<V>> {

    @Override
    protected PojoConstraint<V> toThis() {
        return this;
    }

}