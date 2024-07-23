package cn.srd.library.java.tool.convert.jackson.geometry.serializer;

import cn.srd.library.java.tool.geometry.Geometries;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import lombok.SneakyThrows;
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
    public void serializeWithType(Geometry value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        WritableTypeId typeIdDef = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(value, JsonToken.VALUE_STRING));
        serialize(value, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, typeIdDef);
    }

}