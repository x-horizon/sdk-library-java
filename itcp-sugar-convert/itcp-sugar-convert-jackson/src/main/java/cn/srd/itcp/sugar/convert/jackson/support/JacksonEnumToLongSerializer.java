package cn.srd.itcp.sugar.convert.jackson.support;

import cn.srd.itcp.sugar.convert.jackson.exception.JacksonSerializerException;
import cn.srd.itcp.sugar.tools.core.Objects;
import cn.srd.itcp.sugar.tools.core.StringsUtil;
import cn.srd.itcp.sugar.tools.core.enums.EnumsUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.SneakyThrows;

/**
 * Jackson 序列化处理器：Enum => Enum 内部 Long 类型的值
 *
 * @author wjm
 * @since 2020/12/15 17:02
 */
public class JacksonEnumToLongSerializer extends JsonSerializer<Enum<?>> {

    @Override
    @SneakyThrows
    public void serialize(Enum<?> prepareToDeserializerEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        Long prepareToDeserializerValue = EnumsUtil.getEnumValue(prepareToDeserializerEnum, Long.class);
        if (Objects.isNull(prepareToDeserializerValue)) {
            throw new JacksonSerializerException(StringsUtil.format("枚举 “{}” 不包含 Long 类型的字段, 无法序列化, 请检查！", prepareToDeserializerEnum.getClass().getName()));
        }
        jsonGenerator.writeNumber(prepareToDeserializerValue);
    }

}