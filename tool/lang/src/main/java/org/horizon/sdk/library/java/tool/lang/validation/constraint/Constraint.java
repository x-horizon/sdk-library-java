package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import org.horizon.sdk.library.java.tool.lang.compare.Comparators;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.validation.support.ValidationRule;
import org.horizon.sdk.library.java.tool.lang.validation.violation.ViolationMessageType;

import java.util.List;

/**
 * @author wjm
 * @since 2025-04-18 17:01
 */
public abstract class Constraint<V, C extends Constraint<V, C>> {

    protected final List<ValidationRule<V>> validationRules = Collections.newArrayList();

    protected abstract C toThis();

    public C skipNull() {
        this.validationRules.add(new ValidationRule<>(true));
        return toThis();
    }

    public C mustNull() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.NULL, Nil::isNull));
        return toThis();
    }

    public C mustEquals(V comparedValue) {
        this.validationRules.add(new ValidationRule<>(Collections.ofArrayList(comparedValue), getMustEqualsViolationMessageType(), value -> Comparators.equals(value, comparedValue)));
        return toThis();
    }

    public C mustNotNull() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.NOT_NULL, Nil::isNotNull));
        return toThis();
    }

    public C mustNotEquals(V comparedValue) {
        this.validationRules.add(new ValidationRule<>(Collections.ofArrayList(comparedValue), getMustNotEqualsViolationMessageType(), value -> Comparators.notEquals(value, comparedValue)));
        return toThis();
    }

    protected ViolationMessageType getMustEqualsViolationMessageType() {
        return ViolationMessageType.EQUALS;
    }

    protected ViolationMessageType getMustNotEqualsViolationMessageType() {
        return ViolationMessageType.NOT_EQUALS;
    }

}