package cn.srd.library.tool.convert.jackson.support;

import cn.srd.library.tool.lang.core.ArraysUtil;
import cn.srd.library.tool.lang.core.CollectionsUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import lombok.SneakyThrows;

import java.util.List;

/**
 * Jackson 序列化处理器：List&lt;Long&gt; =&gt; List&lt;String&gt;
 *
 * @author xiongjing
 * @since 2023-03-14 17:57:59
 */
public class JacksonListLongToListStringSerializer extends JsonSerializer<List<Long>> {

    @Override
    @SneakyThrows
    public void serialize(List<Long> from, JsonGenerator jsonGenerator, SerializerProvider serializers) {
        List<String> strings = CollectionsUtil.map(from, param -> Long.toString(param), true);
        jsonGenerator.writeArray(ArraysUtil.toArray(strings, String.class), 0, strings.size());
    }

    @Override
    @SneakyThrows
    public void serializeWithType(List<Long> value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        WritableTypeId typeIdDef = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(value, JsonToken.VALUE_STRING));
        serialize(value, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, typeIdDef);
    }

}
