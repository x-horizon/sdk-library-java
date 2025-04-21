package org.horizon.sdk.library.java.tool.lang.validation.constraint;

/**
 * @author wjm
 * @since 2025-04-22 00:35
 */
public class EnumConstraint<V extends Enum<V>> extends Constraint<V, EnumConstraint<V>> {

    @Override
    protected EnumConstraint<V> toThis() {
        return this;
    }

}