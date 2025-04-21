package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import java.math.BigDecimal;

/**
 * @author wjm
 * @since 2025-04-21 17:44
 */
public class BigDecimalConstraint extends NumberConstraint<BigDecimal, BigDecimal, BigDecimalConstraint> {

    @Override
    protected BigDecimalConstraint toThis() {
        return this;
    }

}