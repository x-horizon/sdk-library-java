package org.horizon.sdk.library.java.tool.convert.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import lombok.SneakyThrows;
import org.horizon.sdk.library.java.tool.lang.time.Times;

import java.time.LocalDateTime;

/**
 * the jackson serializer to convert {@code LocalDateTime} to {@code Long}
 *
 * @author wjm
 * @since 2022-10-14 18:16
 */
public class JacksonLocalDateTimeToLongSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    @SneakyThrows
    public void serialize(LocalDateTime sourceValue, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeObject(Times.toLong(sourceValue));
    }

    @Override
    @SneakyThrows
    public void serializeWithType(LocalDateTime sourceValue, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        WritableTypeId writableTypeId = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(sourceValue, JsonToken.VALUE_NUMBER_INT));
        serialize(sourceValue, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, writableTypeId);
    }

}