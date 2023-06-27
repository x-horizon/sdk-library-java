package cn.srd.itcp.sugar.component.convert.jackson.support;

import cn.srd.itcp.sugar.tool.core.EnumsUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * Jackson 序列化处理器：List&lt;Enum&gt; =&gt; List&lt;Enum 内部 Integer 类型的值&gt;
 *
 * @author wjm
 * @since 2022-09-16 16:24:45
 */
public class JacksonListEnumToListIntegerSerializer extends JsonSerializer<List<Enum<?>>> {

    @Override
    @SneakyThrows
    public void serialize(List<Enum<?>> from, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        List<Integer> prepareToSerializerValues = new ArrayList<>();
        from.forEach(prepareToSerializerEnum -> prepareToSerializerValues.add(EnumsUtil.getEnumValue(prepareToSerializerEnum, Integer.class)));
        jsonGenerator.writeObject(prepareToSerializerValues);
    }

    @Override
    @SneakyThrows
    public void serializeWithType(List<Enum<?>> value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        WritableTypeId typeIdDef = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(value, JsonToken.START_ARRAY));
        serialize(value, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, typeIdDef);
    }

}