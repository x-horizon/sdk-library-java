package org.horizon.sdk.library.java.tool.lang.validation.constraint;

/**
 * @author wjm
 * @since 2025-04-21 17:44
 */
public class DoubleConstraint extends NumberConstraint<Double, Double, DoubleConstraint> {

    @Override
    protected DoubleConstraint toThis() {
        return this;
    }

}