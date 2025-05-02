package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import org.horizon.sdk.library.java.tool.lang.compare.Comparators;
import org.horizon.sdk.library.java.tool.lang.validation.support.ValidationRule;
import org.horizon.sdk.library.java.tool.lang.validation.violation.ViolationMessageType;

import java.io.Serial;

/**
 * abstract constraint class for size-based validation of collection-like structures.
 *
 * <p>extends {@link NumberConstraint} to provide size comparison rules for containers and iterables.
 * used for validating sizes/lengths of strings, collections, maps, etc. with size-specific
 * violation messages.</p>
 *
 * <p>typical usage example:
 * <pre>{@code
 * public class CharSequenceConstraint extends ContainerConstraint<CharSequence, Integer, CharSequenceConstraint> {
 *
 *     protected Integer getComparedFieldValue(CharSequence fieldValue) {
 *         return Nil.zeroValueIfNull(fieldValue).length();
 *     }
 *
 *     @Override
 *     protected CharSequenceConstraint toThis() {
 *         return this;
 *     }
 *
 * }
 * }</pre></p>
 *
 * @param <V> the container/iterable type being validated (e.g., String, List)
 * @param <S> the size number type (typically Integer)
 * @param <C> concrete constraint type for method chaining
 * @author wjm
 * @see NumberConstraint
 * @since 2025-04-20 01:21
 */
public abstract class SizeConstraint<V, S extends Number, C extends SizeConstraint<V, S, C>> extends NumberConstraint<V, S, C> {

    @Serial private static final long serialVersionUID = -4053968057923694495L;

    /**
     * adds a rule requiring the container size to exactly match the specified value.
     *
     * <p>uses {@link Comparators#equals} for size comparison and generates {@link ViolationMessageType#ITERABLE_SIZE_EQUALS} message when violated.</p>
     *
     * @param comparedValue the exact size required
     * @return current constraint instance for chaining
     * @see #getMustSizeEqualsViolationMessageType()
     */
    public C mustSizeEquals(S comparedValue) {
        this.validationRules.add(new ValidationRule<>(Collections.ofArrayList(comparedValue), getMustSizeEqualsViolationMessageType(), fieldValue -> Comparators.equals(getComparedFieldValue(fieldValue), comparedValue)));
        return toThis();
    }

    /**
     * adds a rule requiring the container size to differ from the specified value.
     *
     * <p>uses {@link Comparators#notEquals} for size comparison and generates {@link ViolationMessageType#ITERABLE_SIZE_NOT_EQUALS} message when violated.</p>
     *
     * @param comparedValue the size value to avoid
     * @return current constraint instance for chaining
     * @see #getMustSizeNotEqualsViolationMessageType()
     */
    public C mustSizeNotEquals(S comparedValue) {
        this.validationRules.add(new ValidationRule<>(Collections.ofArrayList(comparedValue), getMustSizeNotEqualsViolationMessageType(), fieldValue -> Comparators.notEquals(getComparedFieldValue(fieldValue), comparedValue)));
        return toThis();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@link ViolationMessageType#EQUALS} for general equality checks
     */
    @Override
    protected ViolationMessageType getMustEqualsViolationMessageType() {
        return ViolationMessageType.EQUALS;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@link ViolationMessageType#NOT_EQUALS} for general inequality checks
     */
    @Override
    protected ViolationMessageType getMustNotEqualsViolationMessageType() {
        return ViolationMessageType.NOT_EQUALS;
    }

    /**
     * provides a violation message type for exact size matches.
     *
     * @return {@link ViolationMessageType#ITERABLE_SIZE_EQUALS} for container size equality failures
     */
    protected ViolationMessageType getMustSizeEqualsViolationMessageType() {
        return ViolationMessageType.ITERABLE_SIZE_EQUALS;
    }

    /**
     * provides a violation message type for size inequality.
     *
     * @return {@link ViolationMessageType#ITERABLE_SIZE_NOT_EQUALS} for container size inequality failures
     */
    protected ViolationMessageType getMustSizeNotEqualsViolationMessageType() {
        return ViolationMessageType.ITERABLE_SIZE_NOT_EQUALS;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@link ViolationMessageType#ITERABLE_SIZE_GREATER_THAN} for container size comparisons
     */
    @Override
    protected ViolationMessageType getMustGreaterThanViolationMessageType() {
        return ViolationMessageType.ITERABLE_SIZE_GREATER_THAN;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@link ViolationMessageType#ITERABLE_SIZE_GREATER_THAN_OR_EQUALS} for container size comparisons
     */
    @Override
    protected ViolationMessageType getMustGreaterThanOrEqualsViolationMessageType() {
        return ViolationMessageType.ITERABLE_SIZE_GREATER_THAN_OR_EQUALS;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@link ViolationMessageType#ITERABLE_SIZE_LESS_THAN} for container size comparisons
     */
    @Override
    protected ViolationMessageType getMustLessThanViolationMessageType() {
        return ViolationMessageType.ITERABLE_SIZE_LESS_THAN;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@link ViolationMessageType#ITERABLE_SIZE_LESS_THAN_OR_EQUALS} for container size comparisons
     */
    @Override
    protected ViolationMessageType getMustLessThanOrEqualsViolationMessageType() {
        return ViolationMessageType.ITERABLE_SIZE_LESS_THAN_OR_EQUALS;
    }

}