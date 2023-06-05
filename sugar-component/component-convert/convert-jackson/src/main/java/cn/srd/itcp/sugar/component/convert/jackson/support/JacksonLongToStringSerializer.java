package cn.srd.itcp.sugar.component.convert.jackson.support;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.SneakyThrows;

/**
 * Jackson 序列化处理器：Long =&gt; String
 *
 * @author wjm
 * @since 2022-10-13 10:26:34
 */
public class JacksonLongToStringSerializer extends JsonSerializer<Long> {

    @Override
    @SneakyThrows
    public void serialize(Long from, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeObject(String.valueOf(from));
    }

}