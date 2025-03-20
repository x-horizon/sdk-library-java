package org.horizon.sdk.library.java.tool.convert.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import lombok.SneakyThrows;
import org.horizon.sdk.library.java.tool.lang.convert.Converts;

/**
 * the jackson serializer to convert {@code Double} to {@code String}
 *
 * @author wjm
 * @since 2024-07-23 19:46
 */
public class JacksonDoubleToStringSerializer extends JsonSerializer<Double> {

    @Override
    @SneakyThrows
    public void serialize(Double sourceValue, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeObject(Converts.toString(sourceValue));
    }

    @Override
    @SneakyThrows
    public void serializeWithType(Double sourceValue, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        WritableTypeId writableTypeId = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(sourceValue, JsonToken.VALUE_STRING));
        serialize(sourceValue, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, writableTypeId);
    }

}