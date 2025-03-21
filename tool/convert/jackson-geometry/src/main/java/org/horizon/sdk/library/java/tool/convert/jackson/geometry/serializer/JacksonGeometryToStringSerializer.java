package org.horizon.sdk.library.java.tool.convert.jackson.geometry.serializer;

import org.horizon.sdk.library.java.tool.convert.jackson.serializer.JacksonSerializeToStringSerializer;
import org.horizon.sdk.library.java.tool.geometry.Geometries;
import org.locationtech.jts.geom.Geometry;

/**
 * the jackson serializer to convert {@link Geometry} to {@link String}
 *
 * @author wjm
 * @since 2023-03-15 09:51
 */
public class JacksonGeometryToStringSerializer extends JacksonSerializeToStringSerializer<Geometry, String> {

    @Override
    public String getTargetValue(Geometry sourceValue) {
        return Geometries.toString(sourceValue);
    }

}