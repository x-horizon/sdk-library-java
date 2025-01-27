package cn.library.java.tool.convert.jackson.serializer;

import cn.library.java.tool.lang.convert.Converts;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import lombok.SneakyThrows;

/**
 * the jackson serializer to convert {@link Long} to {@link String}
 *
 * @author wjm
 * @since 2022-10-13 10:26
 */
public class JacksonLongToStringSerializer extends JsonSerializer<Long> {

    @Override
    @SneakyThrows
    public void serialize(Long from, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeObject(Converts.toString(from));
    }

    @Override
    @SneakyThrows
    public void serializeWithType(Long value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        WritableTypeId typeIdDef = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(value, JsonToken.VALUE_STRING));
        serialize(value, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, typeIdDef);
    }

}