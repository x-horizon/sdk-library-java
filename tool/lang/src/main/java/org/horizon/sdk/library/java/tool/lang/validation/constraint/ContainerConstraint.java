package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.validation.support.SkipCheckType;
import org.horizon.sdk.library.java.tool.lang.validation.support.ValidationRule;
import org.horizon.sdk.library.java.tool.lang.validation.violation.ViolationMessageType;

import java.io.Serial;

/**
 * abstract constraint class for validating empty/non-empty states of container-like structures.
 *
 * <p>extends {@link SizeConstraint} to add container-specific validation rules for emptiness checks.
 * works with various container types including strings, collections, maps, and arrays.</p>
 *
 * <p>usage example:
 * <pre>{@code
 * public class ListConstraint extends ContainerConstraint<List<String>, Integer, ListConstraint> {
 *
 *     @Override
 *     protected Integer getComparedFieldValue(List<String> list) {
 *         return list != null ? list.size() : 0;
 *     }
 *
 *     @Override
 *     protected ListConstraint toThis() {
 *         return this;
 *     }
 *
 * }
 *
 * new ListConstraint().mustNotEmpty().mustSizeGreaterThan(0);
 * }</pre></p>
 *
 * @param <V> the container type being validated (e.g., String, Collection)
 * @param <S> the size number type (typically Integer)
 * @param <C> concrete constraint type for method chaining
 * @author wjm
 * @see SizeConstraint
 * @since 2025-04-21 17:26
 */
public abstract class ContainerConstraint<V, S extends Number, C extends ContainerConstraint<V, S, C>> extends SizeConstraint<V, S, C> {

    @Serial private static final long serialVersionUID = 5220505304411851250L;

    /**
     * adds a rule to skip validation when the field value is empty.
     * <p>when this rule is added, later validation rules will not be checked if the validated value is empty.</p>
     *
     * <p>example:
     * <pre>{@code
     * constraint.skipEmpty().mustEquals("1");
     * }</pre></p>
     *
     * @return current constraint instance for method chaining
     */
    public C skipEmpty() {
        this.validationRules.add(new ValidationRule<>(SkipCheckType.EMPTY));
        return toThis();
    }

    /**
     * adds a rule requiring the container to be empty.
     *
     * <p>uses {@link Nil#isEmpty} for validation check and generates {@link ViolationMessageType#EMPTY} message when violated.</p>
     *
     * @return current constraint instance for chaining
     * @see Nil#isEmpty(Object)
     */
    public C mustEmpty() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.EMPTY, Nil::isEmpty));
        return toThis();
    }

    /**
     * adds a rule requiring the container to be non-empty.
     *
     * <p>uses {@link Nil#isNotEmpty} for validation check and generates {@link ViolationMessageType#NOT_EMPTY} message when violated.</p>
     *
     * @return current constraint instance for chaining
     * @see Nil#isNotEmpty(Object)
     */
    public C mustNotEmpty() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.NOT_EMPTY, Nil::isNotEmpty));
        return toThis();
    }

}