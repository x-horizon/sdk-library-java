package org.horizon.sdk.library.java.tool.convert.jackson.geometry.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;
import org.horizon.sdk.library.java.tool.geometry.Geometries;
import org.locationtech.jts.geom.Geometry;

/**
 * the jackson deserializer to convert {@link String} to {@link Geometry}
 *
 * @author wjm
 * @since 2023-03-15 09:51
 */
public class JacksonStringToGeometryDeserializer extends JsonDeserializer<Geometry> {

    @SneakyThrows
    @Override
    public Geometry deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return Geometries.toGeometry(jsonParser.getValueAsString());
    }

}