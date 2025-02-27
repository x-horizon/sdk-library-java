package org.horizon.sdk.library.java.tool.convert.jackson.serializer;

import org.horizon.sdk.library.java.tool.lang.enums.Enums;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import lombok.SneakyThrows;

/**
 * the jackson serializer to convert {@link Enum}<?> to the enum internal string value, see {@link Enums#getFieldValue(Enum, Class)}
 *
 * @author wjm
 * @since 2022-10-27 18:56
 */
public class JacksonEnumToStringSerializer extends JsonSerializer<Enum<?>> {

    @Override
    @SneakyThrows
    public void serialize(Enum<?> from, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeObject(Enums.getFieldValue(from, String.class));
    }

    @Override
    @SneakyThrows
    public void serializeWithType(Enum<?> value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        WritableTypeId typeIdDef = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(value, JsonToken.VALUE_STRING));
        serialize(value, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, typeIdDef);
    }

}