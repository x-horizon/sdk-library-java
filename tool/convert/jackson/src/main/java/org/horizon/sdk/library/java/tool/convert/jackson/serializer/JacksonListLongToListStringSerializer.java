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
import java.util.stream.Collectors;

/**
 * the jackson serializer to convert {@code List<Long>} to {@code List<String>}
 *
 * @author wjm
 * @since 2023-03-14 17:57
 */
public class JacksonListLongToListStringSerializer extends JsonSerializer<List<Long>> {

    @Override
    @SneakyThrows
    public void serialize(List<Long> from, JsonGenerator jsonGenerator, SerializerProvider serializers) {
        List<String> strings = from.stream().filter(Nil::isNotNull).map(param -> Long.toString(param)).collect(Collectors.toList());
        jsonGenerator.writeArray(Converts.toArray(strings, String.class), NumberConstant.ZERO_INT_VALUE, strings.size());
    }

    @Override
    @SneakyThrows
    public void serializeWithType(List<Long> value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        WritableTypeId typeIdDef = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(value, JsonToken.VALUE_STRING));
        serialize(value, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, typeIdDef);
    }

}