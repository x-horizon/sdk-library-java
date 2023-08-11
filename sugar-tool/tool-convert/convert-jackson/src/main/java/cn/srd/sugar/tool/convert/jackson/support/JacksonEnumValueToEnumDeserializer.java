package cn.srd.sugar.tool.convert.jackson.support;

import cn.srd.sugar.tool.convert.jackson.exception.JacksonDeserializerException;
import cn.srd.sugar.tool.lang.core.EnumsUtil;
import cn.srd.sugar.tool.lang.core.StringsUtil;
import cn.srd.sugar.tool.lang.core.TypesUtil;
import cn.srd.sugar.tool.lang.core.object.Objects;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;

/**
 * Jackson 反序列化处理器：Enum 属性值 =&gt; Enum
 *
 * @author wjm
 * @since 2020/12/15 17:02
 */
public class JacksonEnumValueToEnumDeserializer<E extends Enum<E>> extends JsonDeserializer<E> {

    @SneakyThrows
    @Override
    @SuppressWarnings("unchecked")
    public E deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        String jsonFieldName = jsonParser.getCurrentName();
        Class<?> fieldOfClass = jsonParser.getCurrentValue().getClass();
        Class<?> fieldType = TypesUtil.getTypeClass(fieldOfClass, jsonFieldName);
        if (EnumsUtil.isNotEnum(fieldType)) {
            throw new JacksonDeserializerException(StringsUtil.format("jackson deserializer: cannot deserializer field [{}] on class [{}] because the generic type in List.class is not Enum, please check!", jsonFieldName, fieldOfClass.getSimpleName()));
        }
        return EnumsUtil.capableToEnum(Objects.getActualValue(jsonParser.getText()), (Class<E>) fieldType);
    }

}
