package org.horizon.sdk.library.java.tool.lang.validation.constraint;

/**
 * @author wjm
 * @since 2025-04-21 17:44
 */
public class ShortConstraint extends NumberConstraint<Short, Short, ShortConstraint> {

    @Override
    protected ShortConstraint toThis() {
        return this;
    }

}