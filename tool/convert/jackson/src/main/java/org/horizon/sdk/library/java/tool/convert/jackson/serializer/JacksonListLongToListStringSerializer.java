package org.horizon.sdk.library.java.tool.convert.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import lombok.SneakyThrows;
import org.horizon.sdk.library.java.contract.constant.number.NumberConstant;
import org.horizon.sdk.library.java.tool.lang.convert.Converts;
import org.horizon.sdk.library.java.tool.lang.object.Nil;

import java.util.List;

/**
 * the jackson serializer to convert {@code List<Long>} to {@code List<String>}
 *
 * @author wjm
 * @since 2023-03-14 17:57
 */
public class JacksonListLongToListStringSerializer extends JsonSerializer<List<Long>> {

    @Override
    @SneakyThrows
    public void serialize(List<Long> sourceValues, JsonGenerator jsonGenerator, SerializerProvider serializers) {
        List<String> values = sourceValues.stream().filter(Nil::isNotNull).map(param -> Long.toString(param)).toList();
        jsonGenerator.writeArray(Converts.toArray(values, String.class), NumberConstant.ZERO_INT_VALUE, values.size());
    }

    @Override
    @SneakyThrows
    public void serializeWithType(List<Long> sourceValues, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        WritableTypeId writableTypeId = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(sourceValues, JsonToken.VALUE_STRING));
        serialize(sourceValues, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, writableTypeId);
    }

}