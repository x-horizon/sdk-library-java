package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import org.horizon.sdk.library.java.tool.lang.compare.Comparators;
import org.horizon.sdk.library.java.tool.lang.validation.support.ValidationRule;
import org.horizon.sdk.library.java.tool.lang.validation.violation.ViolationMessageType;

/**
 * @author wjm
 * @since 2025-04-20 01:21
 */
public abstract class SizeConstraint<V, S extends Number, C extends SizeConstraint<V, S, C>> extends NumberConstraint<V, S, C> {

    public C mustSizeEquals(S comparedValue) {
        this.validationRules.add(new ValidationRule<>(Collections.ofArrayList(comparedValue), getMustSizeEqualsViolationMessageType(), fieldValue -> Comparators.equals(getComparedFieldValue(fieldValue), comparedValue)));
        return toThis();
    }

    public C mustSizeNotEquals(S comparedValue) {
        this.validationRules.add(new ValidationRule<>(Collections.ofArrayList(comparedValue), getMustSizeNotEqualsViolationMessageType(), fieldValue -> Comparators.notEquals(getComparedFieldValue(fieldValue), comparedValue)));
        return toThis();
    }

    @Override
    protected ViolationMessageType getMustEqualsViolationMessageType() {
        return ViolationMessageType.EQUALS;
    }

    @Override
    protected ViolationMessageType getMustNotEqualsViolationMessageType() {
        return ViolationMessageType.NOT_EQUALS;
    }

    protected ViolationMessageType getMustSizeEqualsViolationMessageType() {
        return ViolationMessageType.ITERABLE_SIZE_EQUALS;
    }

    protected ViolationMessageType getMustSizeNotEqualsViolationMessageType() {
        return ViolationMessageType.ITERABLE_SIZE_NOT_EQUALS;
    }

    @Override
    protected ViolationMessageType getMustGreaterThanViolationMessageType() {
        return ViolationMessageType.ITERABLE_SIZE_GREATER_THAN;
    }

    @Override
    protected ViolationMessageType getMustGreaterThanOrEqualsViolationMessageType() {
        return ViolationMessageType.ITERABLE_SIZE_GREATER_THAN_OR_EQUALS;
    }

    @Override
    protected ViolationMessageType getMustLessThanViolationMessageType() {
        return ViolationMessageType.ITERABLE_SIZE_LESS_THAN;
    }

    @Override
    protected ViolationMessageType getMustLessThanOrEqualsViolationMessageType() {
        return ViolationMessageType.ITERABLE_SIZE_LESS_THAN_OR_EQUALS;
    }

}