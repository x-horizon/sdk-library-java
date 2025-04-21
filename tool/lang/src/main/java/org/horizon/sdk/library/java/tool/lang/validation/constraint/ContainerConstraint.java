package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.validation.support.ValidationRule;
import org.horizon.sdk.library.java.tool.lang.validation.violation.ViolationMessageType;

/**
 * @author wjm
 * @since 2025-04-21 17:26
 */
public abstract class ContainerConstraint<V, S extends Number, C extends ContainerConstraint<V, S, C>> extends SizeConstraint<V, S, C> {

    public C mustEmpty() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.EMPTY, Nil::isEmpty));
        return toThis();
    }

    public C mustNotEmpty() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.NOT_EMPTY, Nil::isNotEmpty));
        return toThis();
    }

}