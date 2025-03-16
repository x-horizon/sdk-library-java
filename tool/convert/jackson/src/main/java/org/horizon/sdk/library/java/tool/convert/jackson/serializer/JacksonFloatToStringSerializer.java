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
 * the jackson serializer to convert {@code Float} to {@code String}
 *
 * @author wjm
 * @since 2024-07-23 19:46
 */
public class JacksonFloatToStringSerializer extends JsonSerializer<Float> {

    @Override
    @SneakyThrows
    public void serialize(Float from, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeObject(Converts.toString(from));
    }

    @Override
    @SneakyThrows
    public void serializeWithType(Float value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        WritableTypeId typeIdDef = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(value, JsonToken.VALUE_STRING));
        serialize(value, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, typeIdDef);
    }

}