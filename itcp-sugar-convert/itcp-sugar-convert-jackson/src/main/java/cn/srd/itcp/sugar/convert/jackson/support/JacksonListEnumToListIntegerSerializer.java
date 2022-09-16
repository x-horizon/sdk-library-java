package cn.srd.itcp.sugar.convert.jackson.support;

import cn.srd.itcp.sugar.tools.core.enums.EnumsUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * Jackson 序列化处理器：List&lt;Enum&gt; => List&lt;Enum 内部 Integer 类型的值&gt;
 *
 * @author wjm
 * @since 2022-09-16 16:24:45
 */
public class JacksonListEnumToListIntegerSerializer extends JsonSerializer<List<Enum<?>>> {

    @Override
    @SneakyThrows
    public void serialize(List<Enum<?>> prepareToSerializerEnums, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        List<Integer> prepareToSerializerValues = new ArrayList<>();
        prepareToSerializerEnums.forEach(prepareToSerializerEnum -> prepareToSerializerValues.add(EnumsUtil.getEnumValue(prepareToSerializerEnum, Integer.class)));
        jsonGenerator.writeObject(prepareToSerializerValues);
    }

}