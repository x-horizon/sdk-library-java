package org.horizon.sdk.library.java.tool.lang.validation.constraint;

/**
 * @author wjm
 * @since 2025-04-21 17:44
 */
public class IntegerConstraint extends NumberConstraint<Integer, Integer, IntegerConstraint> {

    @Override
    protected IntegerConstraint toThis() {
        return this;
    }

}