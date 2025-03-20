package org.horizon.sdk.library.java.tool.convert.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import lombok.SneakyThrows;
import org.horizon.sdk.library.java.tool.lang.time.Times;

import java.time.LocalDate;

/**
 * the jackson serializer to convert {@code LocalDate} to {@code String} like "2023-03-27"
 *
 * @author wjm
 * @since 2023-06-21 12:01
 */
public class JacksonLocalDateToStringSerializer extends JsonSerializer<LocalDate> {

    @SneakyThrows
    @Override
    public void serialize(LocalDate sourceValue, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeObject(Times.toStringYearMonthDay(sourceValue));
    }

    @Override
    @SneakyThrows
    public void serializeWithType(LocalDate sourceValue, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        WritableTypeId writableTypeId = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(sourceValue, JsonToken.VALUE_STRING));
        serialize(sourceValue, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, writableTypeId);
    }

}