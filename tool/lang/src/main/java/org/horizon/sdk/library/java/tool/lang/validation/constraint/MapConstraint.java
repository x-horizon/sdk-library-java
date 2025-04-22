package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import org.horizon.sdk.library.java.tool.lang.collection.Collections;

import java.util.Map;

/**
 * constraint implementation for validating {@link Map} types with size-based rules.
 *
 * <p>extends {@link ContainerConstraint} to provide map-specific size validation capabilities.
 * automatically calculates map size using {@link Collections#getSize} for comparison operations.</p>
 *
 * <p>usage example:
 * <pre>{@code
 * MapConstraint<Map<String, Integer>, String, Integer> constraint = new MapConstraint<>();
 * constraint.mustNotEmpty().mustSizeLessThan(100);
 * }</pre></p>
 *
 * @param <V>        the map type being validated, must implement {@link Map}
 * @param <MapKey>   the type of map keys
 * @param <MapValue> the type of map values
 * @author wjm
 * @see ContainerConstraint
 * @since 2025-04-21 17:56
 */
public class MapConstraint<V extends Map<MapKey, MapValue>, MapKey, MapValue> extends ContainerConstraint<V, Integer, MapConstraint<V, MapKey, MapValue>> {

    /**
     * calculates the entry count of map for validation comparisons.
     *
     * <p>uses {@link Collections#getSize} to determine map size, supporting all {@link Map} implementations including those that don't provide constant-time size calculations.</p>
     *
     * @param fieldValue the map to measure
     * @return number of key-value pairs in the map (0 for null)
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
    protected MapConstraint<V, MapKey, MapValue> toThis() {
        return this;
    }

}