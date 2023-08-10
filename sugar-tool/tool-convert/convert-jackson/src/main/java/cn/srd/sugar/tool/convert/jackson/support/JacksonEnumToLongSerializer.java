package cn.srd.sugar.tool.convert.jackson.support;

import cn.srd.itcp.sugar.tool.core.EnumsUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import lombok.SneakyThrows;

/**
 * Jackson 序列化处理器：Enum =&gt; Enum 内部 Long 类型的值
 *
 * @author wjm
 * @since 2020/12/15 17:02
 */
public class JacksonEnumToLongSerializer extends JsonSerializer<Enum<?>> {

    @Override
    @SneakyThrows
    public void serialize(Enum<?> from, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeObject(EnumsUtil.getEnumValue(from, Long.class));
    }

    @Override
    @SneakyThrows
    public void serializeWithType(Enum<?> value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        WritableTypeId typeIdDef = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(value, JsonToken.VALUE_NUMBER_INT));
        serialize(value, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, typeIdDef);
    }

}