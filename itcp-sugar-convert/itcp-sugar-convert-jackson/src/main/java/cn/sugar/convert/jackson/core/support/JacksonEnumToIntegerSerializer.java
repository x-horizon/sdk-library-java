package cn.sugar.convert.jackson.core.support;

import cn.sugar.convert.jackson.exception.JacksonSerializerException;
import cn.sugar.tools.core.Objects;
import cn.sugar.tools.core.StringsUtil;
import cn.sugar.tools.core.enums.EnumsUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.SneakyThrows;

public class JacksonEnumToIntegerSerializer extends JsonSerializer<Enum<?>> {

    @Override
    @SneakyThrows
    public void serialize(Enum<?> prepareToDeserializerEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        Integer prepareToDeserializerValue = EnumsUtil.getEnumValue(prepareToDeserializerEnum, Integer.class);
        if (Objects.isNull(prepareToDeserializerValue)) {
            throw new JacksonSerializerException(StringsUtil.format("枚举 “{}” 不包含 Integer 类型的字段，请检查！", prepareToDeserializerEnum.getClass().getName()));
        }
        jsonGenerator.writeNumber(prepareToDeserializerValue);
    }

}