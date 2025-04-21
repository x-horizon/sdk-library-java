package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import org.horizon.sdk.library.java.tool.lang.collection.Collections;

import java.util.Map;

/**
 * @author wjm
 * @since 2025-04-21 17:56
 */
public class MapConstraint<V extends Map<MapKey, MapValue>, MapKey, MapValue> extends ContainerConstraint<V, Integer, MapConstraint<V, MapKey, MapValue>> {

    @Override
    protected Integer getComparedFieldValue(V fieldValue) {
        return Collections.getSize(fieldValue);
    }

    @Override
    protected MapConstraint<V, MapKey, MapValue> toThis() {
        return this;
    }

}