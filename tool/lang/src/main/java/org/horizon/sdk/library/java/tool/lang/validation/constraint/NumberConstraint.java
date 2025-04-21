package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import org.horizon.sdk.library.java.tool.lang.compare.Comparators;
import org.horizon.sdk.library.java.tool.lang.validation.support.ValidationRule;
import org.horizon.sdk.library.java.tool.lang.validation.violation.ViolationMessageType;

/**
 * @author wjm
 * @since 2025-04-20 00:08
 */
public abstract class NumberConstraint<V, N extends Number, C extends NumberConstraint<V, N, C>> extends Constraint<V, C> {

    public C mustGreaterThan(N comparedValue) {
        this.validationRules.add(new ValidationRule<>(Collections.ofArrayList(comparedValue), getMustGreaterThanViolationMessageType(), fieldValue -> Comparators.greaterThan(getComparedFieldValue(fieldValue), comparedValue)));
        return toThis();
    }

    public C mustGreaterThanOrEquals(N comparedValue) {
        this.validationRules.add(new ValidationRule<>(Collections.ofArrayList(comparedValue), getMustGreaterThanOrEqualsViolationMessageType(), fieldValue -> Comparators.greaterThanOrEquals(getComparedFieldValue(fieldValue), comparedValue)));
        return toThis();
    }

    public C mustLessThan(N comparedValue) {
        this.validationRules.add(new ValidationRule<>(Collections.ofArrayList(comparedValue), getMustLessThanViolationMessageType(), fieldValue -> Comparators.lessThan(getComparedFieldValue(fieldValue), comparedValue)));
        return toThis();
    }

    public C mustLessThanOrEquals(N comparedValue) {
        this.validationRules.add(new ValidationRule<>(Collections.ofArrayList(comparedValue), getMustLessThanOrEqualsViolationMessageType(), fieldValue -> Comparators.lessThanOrEquals(getComparedFieldValue(fieldValue), comparedValue)));
        return toThis();
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    protected N getComparedFieldValue(V fieldValue) {
        return (N) fieldValue;
    }

    @Override
    protected ViolationMessageType getMustEqualsViolationMessageType() {
        return ViolationMessageType.NUMBER_EQUALS;
    }

    @Override
    protected ViolationMessageType getMustNotEqualsViolationMessageType() {
        return ViolationMessageType.NUMBER_NOT_EQUALS;
    }

    protected ViolationMessageType getMustGreaterThanViolationMessageType() {
        return ViolationMessageType.NUMBER_GREATER_THAN;
    }

    protected ViolationMessageType getMustGreaterThanOrEqualsViolationMessageType() {
        return ViolationMessageType.NUMBER_GREATER_THAN_OR_EQUALS;
    }

    protected ViolationMessageType getMustLessThanViolationMessageType() {
        return ViolationMessageType.NUMBER_LESS_THAN;
    }

    protected ViolationMessageType getMustLessThanOrEqualsViolationMessageType() {
        return ViolationMessageType.NUMBER_LESS_THAN_OR_EQUALS;
    }

}