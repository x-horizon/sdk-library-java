package org.horizon.sdk.library.java.tool.lang.validation.constraint;

/**
 * @author wjm
 * @since 2025-04-21 17:44
 */
public class LongConstraint extends NumberConstraint<Long, Long, LongConstraint> {

    @Override
    protected LongConstraint toThis() {
        return this;
    }

}