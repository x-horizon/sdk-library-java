package org.horizon.sdk.library.java.tool.convert.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import lombok.SneakyThrows;
import org.horizon.sdk.library.java.tool.lang.time.Times;

import java.time.LocalTime;

/**
 * the jackson serializer to convert {@code LocalTime} to {@code String} like "14:12"
 *
 * @author wjm
 * @since 2023-06-21 12:01
 */
public class JacksonLocalTimeToStringHourMinuteSerializer extends JsonSerializer<LocalTime> {

    @SneakyThrows
    @Override
    public void serialize(LocalTime sourceValue, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeObject(Times.toStringHourMinute(sourceValue));
    }

    @Override
    @SneakyThrows
    public void serializeWithType(LocalTime sourceValue, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        WritableTypeId writableTypeId = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(sourceValue, JsonToken.VALUE_STRING));
        serialize(sourceValue, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, writableTypeId);
    }

}