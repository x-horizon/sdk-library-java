package org.horizon.sdk.library.java.tool.convert.jackson.geometry.deserializer;

import com.fasterxml.jackson.databind.util.StdConverter;
import org.horizon.sdk.library.java.tool.geometry.Geometries;
import org.locationtech.jts.geom.Geometry;

/**
 * the jackson serializer to convert {@link String} to {@link Geometry}
 *
 * @author wjm
 * @since 2023-03-15 09:51
 */
public class JacksonStringToGeometryDeserializer extends StdConverter<String, Geometry> {

    @Override
    public Geometry convert(String value) {
        return Geometries.toGeometry(value);
    }

}