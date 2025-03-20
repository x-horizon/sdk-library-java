package org.horizon.sdk.library.java.tool.convert.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import lombok.SneakyThrows;
import org.horizon.sdk.library.java.tool.lang.enums.Enums;

/**
 * the jackson serializer to convert {@code Enum<?>} to the enum internal string value, see {@link Enums#getFieldValue(Enum, Class)}
 *
 * @author wjm
 * @since 2022-10-27 18:56
 */
public class JacksonEnumToStringSerializer extends JsonSerializer<Enum<?>> {

    @Override
    @SneakyThrows
    public void serialize(Enum<?> sourceValue, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeObject(Enums.getFieldValue(sourceValue, String.class));
    }

    @Override
    @SneakyThrows
    public void serializeWithType(Enum<?> sourceValue, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        WritableTypeId writableTypeId = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(sourceValue, JsonToken.VALUE_STRING));
        serialize(sourceValue, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, writableTypeId);
    }

}