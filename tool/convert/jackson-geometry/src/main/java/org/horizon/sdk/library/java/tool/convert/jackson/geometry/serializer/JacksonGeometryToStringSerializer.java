package org.horizon.sdk.library.java.tool.convert.jackson.geometry.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import lombok.SneakyThrows;
import org.horizon.sdk.library.java.tool.geometry.Geometries;
import org.locationtech.jts.geom.Geometry;

/**
 * the jackson serializer to convert {@link Geometry} to {@link String}
 *
 * @author wjm
 * @since 2023-03-15 09:51
 */
public class JacksonGeometryToStringSerializer extends JsonSerializer<Geometry> {

    @Override
    @SneakyThrows
    public void serialize(Geometry prepareToSerializer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeObject(Geometries.toString(prepareToSerializer));
    }

    @Override
    @SneakyThrows
    public void serializeWithType(Geometry sourceValue, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        WritableTypeId writableTypeId = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(sourceValue, JsonToken.VALUE_STRING));
        serialize(sourceValue, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, writableTypeId);
    }

}