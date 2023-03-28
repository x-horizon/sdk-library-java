package cn.srd.itcp.sugar.component.convert.jackson.support;

import cn.srd.itcp.sugar.tool.core.EnumsUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.SneakyThrows;

/**
 * Jackson 序列化处理器：Enum =&gt; Enum 内部 String 类型的值（不是枚举本身的值）
 *
 * @author wjm
 * @since 2022/10/27 18:56:21
 */
public class JacksonEnumToStringSerializer extends JsonSerializer<Enum<?>> {

    @Override
    @SneakyThrows
    public void serialize(Enum<?> from, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeObject(EnumsUtil.getEnumValue(from, String.class));
    }

}