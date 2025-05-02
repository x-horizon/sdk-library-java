package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import java.io.Serial;
import java.math.BigDecimal;

/**
 * constraint implementation for high-precision decimal validation using {@link BigDecimal}.
 *
 * <p>extends {@link NumberConstraint} to provide validation rules for arbitrary-precision
 * signed decimal numbers. handles monetary values, scientific calculations, and other scenarios
 * requiring exact numerical precision.</p>
 *
 * <p>usage example:
 * <pre>{@code
 * BigDecimalConstraint constraint = new BigDecimalConstraint()
 *      .mustGreaterThan(new BigDecimal("0.00"))
 *      .mustLessThanOrEquals(new BigDecimal("10.55"));
 * }</pre></p>
 *
 * @author wjm
 * @see NumberConstraint
 * @since 2025-04-21 17:44
 */
public class BigDecimalConstraint extends NumberConstraint<BigDecimal, BigDecimal, BigDecimalConstraint> {

    @Serial private static final long serialVersionUID = 3921881441037781185L;

    /**
     * {@inheritDoc}
     *
     * <p>maintains a fluent api pattern by returning {@code this} instance to enable chained validation configuration for high-precision decimal values</p>
     */
    @Override
    protected BigDecimalConstraint toThis() {
        return this;
    }

}