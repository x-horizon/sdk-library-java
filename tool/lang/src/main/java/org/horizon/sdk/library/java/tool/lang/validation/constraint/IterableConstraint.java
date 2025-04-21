package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import org.horizon.sdk.library.java.tool.lang.collection.Collections;

/**
 * @author wjm
 * @since 2025-04-20 01:17
 */
public class IterableConstraint<V extends Iterable<E>, E> extends ContainerConstraint<V, Integer, IterableConstraint<V, E>> {

    @Override
    protected Integer getComparedFieldValue(V fieldValue) {
        return Collections.getSize(fieldValue);
    }

    @Override
    protected IterableConstraint<V, E> toThis() {
        return this;
    }

}