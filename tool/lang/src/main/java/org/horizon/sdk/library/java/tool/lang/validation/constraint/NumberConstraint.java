package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import org.horizon.sdk.library.java.tool.lang.compare.Comparators;
import org.horizon.sdk.library.java.tool.lang.validation.support.ValidationRule;
import org.horizon.sdk.library.java.tool.lang.validation.violation.ViolationMessageType;

import java.io.Serial;

/**
 * abstract base class for numeric value validation constraints.
 *
 * <p>provides numerical comparison constraints (greater than, less than, etc.) and configures
 * number-specific violation messages. designed to be extended for concrete number types
 * like {@link Integer}, {@link Double}, etc.</p>
 *
 * <p>usage example:
 * <pre>{@code
 * public class IntegerConstraint extends NumberConstraint<Integer, Integer, IntegerConstraint> {
 *
 *     @Override
 *     protected IntegerConstraint toThis() {
 *         return this;
 *     }
 *
 * }
 *
 * new IntegerConstraint().mustGreaterThan(5).mustLessThanOrEquals(10);
 * }</pre></p>
 *
 * @param <V> the field value type being validated
 * @param <N> the number type for comparison (should match V)
 * @param <C> concrete constraint type for method chaining
 * @author wjm
 * @see Comparators
 * @since 2025-04-20 00:08
 */
public abstract class NumberConstraint<V, N extends Number, C extends NumberConstraint<V, N, C>> extends Constraint<V, C> {

    @Serial private static final long serialVersionUID = -4436264418840677932L;

    /**
     * adds a rule requiring the value to be greater than the specified number.
     *
     * <p>uses {@link Comparators#greaterThan} for comparison and generates {@link ViolationMessageType#NUMBER_GREATER_THAN} message when violated.</p>
     *
     * @param comparedValue the threshold value to compare against
     * @return current constraint instance for chaining
     * @see #getMustGreaterThanViolationMessageType()
     */
    public C mustGreaterThan(N comparedValue) {
        this.validationRules.add(new ValidationRule<>(Collections.ofArrayList(comparedValue), getMustGreaterThanViolationMessageType(), fieldValue -> Comparators.greaterThan(getComparedFieldValue(fieldValue), comparedValue)));
        return toThis();
    }

    /**
     * adds a rule requiring the value to be greater than or equal to the specified number.
     *
     * <p>uses {@link Comparators#greaterThanOrEquals} for comparison and generates {@link ViolationMessageType#NUMBER_GREATER_THAN_OR_EQUALS} message when violated.</p>
     *
     * @param comparedValue the threshold value to compare against
     * @return current constraint instance for chaining
     * @see #getMustGreaterThanOrEqualsViolationMessageType()
     */
    public C mustGreaterThanOrEquals(N comparedValue) {
        this.validationRules.add(new ValidationRule<>(Collections.ofArrayList(comparedValue), getMustGreaterThanOrEqualsViolationMessageType(), fieldValue -> Comparators.greaterThanOrEquals(getComparedFieldValue(fieldValue), comparedValue)));
        return toThis();
    }

    /**
     * adds a rule requiring the value to be less than the specified number.
     *
     * <p>uses {@link Comparators#lessThan} for comparison and generates {@link ViolationMessageType#NUMBER_LESS_THAN} message when violated.</p>
     *
     * @param comparedValue the threshold value to compare against
     * @return current constraint instance for chaining
     * @see #getMustLessThanViolationMessageType()
     */
    public C mustLessThan(N comparedValue) {
        this.validationRules.add(new ValidationRule<>(Collections.ofArrayList(comparedValue), getMustLessThanViolationMessageType(), fieldValue -> Comparators.lessThan(getComparedFieldValue(fieldValue), comparedValue)));
        return toThis();
    }

    /**
     * adds a rule requiring the value to be less than or equal to the specified number.
     *
     * <p>uses {@link Comparators#lessThanOrEquals} for comparison and generates {@link ViolationMessageType#NUMBER_LESS_THAN_OR_EQUALS} message when violated.</p>
     *
     * @param comparedValue the threshold value to compare against
     * @return current constraint instance for chaining
     * @see #getMustLessThanOrEqualsViolationMessageType()
     */
    public C mustLessThanOrEquals(N comparedValue) {
        this.validationRules.add(new ValidationRule<>(Collections.ofArrayList(comparedValue), getMustLessThanOrEqualsViolationMessageType(), fieldValue -> Comparators.lessThanOrEquals(getComparedFieldValue(fieldValue), comparedValue)));
        return toThis();
    }

    /**
     * converts the field value to a comparison number type.
     *
     * <p>this implementation performs direct casting assuming V and N are compatible, subclasses should override if different conversion logic is needed.</p>
     *
     * @param fieldValue the original field value
     * @return converted number value for comparison
     * @throws ClassCastException if V cannot be cast to N
     */
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    protected N getComparedFieldValue(V fieldValue) {
        return (N) fieldValue;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@link ViolationMessageType#NUMBER_EQUALS} for number-specific equality messages
     */
    @Override
    protected ViolationMessageType getMustEqualsViolationMessageType() {
        return ViolationMessageType.NUMBER_EQUALS;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@link ViolationMessageType#NUMBER_NOT_EQUALS} for number-specific inequality messages
     */
    @Override
    protected ViolationMessageType getMustNotEqualsViolationMessageType() {
        return ViolationMessageType.NUMBER_NOT_EQUALS;
    }

    /**
     * provides a violation message type for "greater than" failures.
     *
     * @return {@link ViolationMessageType#NUMBER_GREATER_THAN}
     */
    protected ViolationMessageType getMustGreaterThanViolationMessageType() {
        return ViolationMessageType.NUMBER_GREATER_THAN;
    }

    /**
     * provides a violation message type for "greater than or equals" failures.
     *
     * @return {@link ViolationMessageType#NUMBER_GREATER_THAN_OR_EQUALS}
     */
    protected ViolationMessageType getMustGreaterThanOrEqualsViolationMessageType() {
        return ViolationMessageType.NUMBER_GREATER_THAN_OR_EQUALS;
    }

    /**
     * provides a violation message type for "less than" failures.
     *
     * @return {@link ViolationMessageType#NUMBER_LESS_THAN}
     */
    protected ViolationMessageType getMustLessThanViolationMessageType() {
        return ViolationMessageType.NUMBER_LESS_THAN;
    }

    /**
     * provides a violation message type for "less than or equals" failures.
     *
     * @return {@link ViolationMessageType#NUMBER_LESS_THAN_OR_EQUALS}
     */
    protected ViolationMessageType getMustLessThanOrEqualsViolationMessageType() {
        return ViolationMessageType.NUMBER_LESS_THAN_OR_EQUALS;
    }

}