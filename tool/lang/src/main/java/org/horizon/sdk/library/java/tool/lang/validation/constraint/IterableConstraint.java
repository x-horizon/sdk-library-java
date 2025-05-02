package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import org.horizon.sdk.library.java.tool.lang.collection.Collections;

import java.io.Serial;

/**
 * constraint implementation for validating {@link Iterable} types (collections, lists, etc.).
 *
 * <p>extends {@link ContainerConstraint} to provide size validation capabilities for iterable containers.
 * automatically calculates collection size using {@link Collections#getSize} for comparison operations.</p>
 *
 * <p>usage example:
 * <pre>{@code
 * IterableConstraint<List<String>, String> constraint = new IterableConstraint<>();
 * constraint.mustNotEmpty();
 * }</pre></p>
 *
 * @param <V> the iterable container type being validated (e.g., List, Set)
 * @param <E> the element type contained in the iterable
 * @author wjm
 * @see ContainerConstraint
 * @since 2025-04-20 01:17
 */
public class IterableConstraint<V extends Iterable<E>, E> extends ContainerConstraint<V, Integer, IterableConstraint<V, E>> {

    @Serial private static final long serialVersionUID = 8826274164332082751L;

    /**
     * calculates the size of iterable containers for validation comparisons.
     *
     * <p>uses {@link Collections#getSize} to determine container size, supporting both {@link java.util.Collection} types and other iterables through size calculation.</p>
     *
     * @param fieldValue the iterable container to measure
     * @return number of elements in the container (0 for null)
     * @throws IllegalArgumentException if the container doesn't support fast size calculation
     */
    @Override
    protected Integer getComparedFieldValue(V fieldValue) {
        return Collections.getSize(fieldValue);
    }

    /**
     * {@inheritDoc}
     *
     * <p>returns {@code this} instance to enable fluent validation rule chaining</p>
     */
    @Override
    protected IterableConstraint<V, E> toThis() {
        return this;
    }

}