package cn.srd.itcp.sugar.convert.jackson.support;

import cn.srd.itcp.sugar.tools.core.enums.EnumsUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.SneakyThrows;

/**
 * Jackson 序列化处理器：Enum => Enum 内部 Integer 类型的值
 *
 * @author wjm
 * @since 2020/12/15 17:02
 */
public class JacksonEnumToIntegerSerializer extends JsonSerializer<Enum<?>> {

    @Override
    @SneakyThrows
    public void serialize(Enum<?> prepareToSerializerEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeObject(EnumsUtil.getEnumValue(prepareToSerializerEnum, Integer.class));
    }

}