package cn.srd.sugar.tool.convert.jackson.support;

import cn.srd.itcp.sugar.tool.core.time.TimeUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import lombok.SneakyThrows;

import java.time.LocalDate;

/**
 * Jackson 序列化处理器：LocalDate =&gt; String
 *
 * @author wjm
 * @since 2023-06-21 12:01:01
 */
public class JacksonLocalDateToStringSerializer extends JsonSerializer<LocalDate> {

    @SneakyThrows
    @Override
    public void serialize(LocalDate from, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeObject(TimeUtil.formatNormal(from));
    }

    @Override
    @SneakyThrows
    public void serializeWithType(LocalDate value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        WritableTypeId typeIdDef = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(value, JsonToken.VALUE_STRING));
        serialize(value, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, typeIdDef);
    }

}