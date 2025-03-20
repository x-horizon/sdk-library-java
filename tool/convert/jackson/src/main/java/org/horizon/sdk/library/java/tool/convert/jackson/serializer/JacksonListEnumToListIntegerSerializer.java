package org.horizon.sdk.library.java.tool.convert.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import lombok.SneakyThrows;
import org.horizon.sdk.library.java.tool.lang.enums.Enums;

import java.util.ArrayList;
import java.util.List;

/**
 * the jackson serializer to convert {@code List<Enum<?>>} to {@code List<the int value type in the enum, see {@link Enums#getFieldValue(Enum, Class)}>};
 *
 * @author wjm
 * @since 2022-09-16 16:24
 */
public class JacksonListEnumToListIntegerSerializer extends JsonSerializer<List<Enum<?>>> {

    @Override
    @SneakyThrows
    public void serialize(List<Enum<?>> sourceValues, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        List<Integer> prepareToSerializerValues = new ArrayList<>();
        sourceValues.forEach(prepareToSerializerEnum -> prepareToSerializerValues.add(Enums.getFieldValue(prepareToSerializerEnum, Integer.class)));
        jsonGenerator.writeObject(prepareToSerializerValues);
    }

    @Override
    @SneakyThrows
    public void serializeWithType(List<Enum<?>> sourceValues, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        WritableTypeId writableTypeId = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(sourceValues, JsonToken.START_ARRAY));
        serialize(sourceValues, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, writableTypeId);
    }

}