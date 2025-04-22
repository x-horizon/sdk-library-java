package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import org.horizon.sdk.library.java.tool.lang.compare.Comparators;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.validation.support.ValidationRule;
import org.horizon.sdk.library.java.tool.lang.validation.violation.ViolationMessageType;

import java.util.List;

/**
 * base abstract class for building validation constraints with fluent api support.
 *
 * <p>this class serves as the foundation for all constraint implementations, providing common validation
 * rules and template methods for concrete implementations. it uses generic types to support various
 * validation scenarios while maintaining type safety.</p>
 *
 * <p>typical usage example:
 * <pre>{@code
 * public class EnumConstraint<V extends Enum<V>> extends Constraint<V, EnumConstraint<V>> {
 *
 *     @Override
 *     protected EnumConstraint<V> toThis() {
 *         return this;
 *     }
 *
 * }
 * }</pre></p>
 *
 * @param <V> the type of value being validated
 * @param <C> the concrete constraint type for method chaining (self-referential generic)
 * @author wjm
 * @since 2025-04-18 17:01
 */
public abstract class Constraint<V, C extends Constraint<V, C>> {

    /**
     * stores all validation rules for this constraint.
     * <p>this list contains {@link ValidationRule} objects that define various validation conditions and corresponding violation messages.</p>
     */
    protected final List<ValidationRule<V>> validationRules = Collections.newArrayList();

    /**
     * template method for concrete implementations to return {@code this} instance.
     * <p>this method enables fluent api by allowing method chaining. concrete implementations must simply return {@code this}.</p>
     *
     * @return the concrete constraint instance
     */
    protected abstract C toThis();

    /**
     * adds a rule to skip validation when the field value is {@code null}.
     * <p>when this rule is added, subsequent validation rules will not be checked if the validated value is {@code null}.</p>
     *
     * <p>example:
     * <pre>{@code
     * constraint.skipNull().mustEquals("1");
     * }</pre></p>
     *
     * @return current constraint instance for method chaining
     * @see ValidationRule#isNeedToSkipNull()
     */
    public C skipNull() {
        this.validationRules.add(new ValidationRule<>(true));
        return toThis();
    }

    /**
     * adds a rule to require the field value to be {@code null}.
     * <p>generates {@link ViolationMessageType#NULL} message type when violated.</p>
     *
     * <p>example:
     * <pre>{@code
     * constraint.mustNull();
     * }</pre></p>
     *
     * @return current constraint instance for method chaining
     * @see Nil#isNull
     */
    public C mustNull() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.NULL, Nil::isNull));
        return toThis();
    }

    /**
     * adds a rule to require the field value to equal the specified value.
     * <p>uses {@link Comparators#equals} for equality check and generates {@link ViolationMessageType#EQUALS} message type when violated.</p>
     *
     * <p>example:
     * <pre>{@code
     * constraint.mustEquals("expectedValue");
     * }</pre></p>
     *
     * @param comparedValue the value to compare against
     * @return current constraint instance for method chaining
     * @see #getMustEqualsViolationMessageType()
     */
    public C mustEquals(V comparedValue) {
        this.validationRules.add(new ValidationRule<>(Collections.ofArrayList(comparedValue),
                getMustEqualsViolationMessageType(), value -> Comparators.equals(value, comparedValue)));
        return toThis();
    }

    /**
     * adds a rule to require the field value to be non-{@code null}.
     * <p>generates {@link ViolationMessageType#NOT_NULL} message type when violated.</p>
     *
     * <p>example:
     * <pre>{@code
     * constraint.mustNotNull();
     * }</pre></p>
     *
     * @return current constraint instance for method chaining
     * @see Nil#isNotNull
     */
    public C mustNotNull() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.NOT_NULL, Nil::isNotNull));
        return toThis();
    }

    /**
     * adds a rule to require the field value to not equal specified value.
     * <p>uses {@link Comparators#notEquals} for inequality check and generates {@link ViolationMessageType#NOT_EQUALS} message type when violated.</p>
     *
     * <p>example:
     * <pre>{@code
     * constraint.mustNotEquals("forbiddenValue");
     * }</pre></p>
     *
     * @param comparedValue the value to compare against
     * @return current constraint instance for method chaining
     * @see #getMustNotEqualsViolationMessageType()
     */
    public C mustNotEquals(V comparedValue) {
        this.validationRules.add(new ValidationRule<>(Collections.ofArrayList(comparedValue), getMustNotEqualsViolationMessageType(), value -> Comparators.notEquals(value, comparedValue)));
        return toThis();
    }

    /**
     * provides violation message type for equality failures.
     * <p>default implementation returns {@link ViolationMessageType#EQUALS}, subclasses may override to provide type-specific message types.</p>
     *
     * @return message type for equality validation failures
     */
    protected ViolationMessageType getMustEqualsViolationMessageType() {
        return ViolationMessageType.EQUALS;
    }

    /**
     * provides violation message type for inequality failures.
     * <p>default implementation returns {@link ViolationMessageType#NOT_EQUALS}, subclasses may override to provide type-specific message types.</p>
     *
     * @return message type for inequality validation failures
     */
    protected ViolationMessageType getMustNotEqualsViolationMessageType() {
        return ViolationMessageType.NOT_EQUALS;
    }

}