package cn.srd.itcp.sugar.convert.jackson.support;

import cn.srd.itcp.sugar.convert.jackson.exception.JacksonDeserializerException;
import cn.srd.itcp.sugar.tools.core.Objects;
import cn.srd.itcp.sugar.tools.core.StringsUtil;
import cn.srd.itcp.sugar.tools.core.TypesUtil;
import cn.srd.itcp.sugar.tools.core.enums.EnumsUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;

/**
 * Jackson 反序列化处理器：Enum 属性值 => Enum
 *
 * @author wjm
 * @since 2020/12/15 17:02
 */
public class JacksonEnumValueToEnumDeserializer<E extends Enum<E>> extends JsonDeserializer<E> {

    @SneakyThrows
    @Override
    @SuppressWarnings("unchecked")
    public E deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        // 字段名
        String fieldName = jsonParser.getCurrentName();
        // 字段所在的类
        Class<?> fieldOfClass = jsonParser.getCurrentValue().getClass();
        // 字段类型
        Class<?> fieldType = TypesUtil.getTypeClass(fieldOfClass, fieldName);
        if (EnumsUtil.isNotEnum(fieldType)) {
            throw new JacksonDeserializerException(StringsUtil.format(" 该类 “{}” 中的 “{}” 字段不是枚举类型, 无法反序列化，请检查！", fieldOfClass.getName(), fieldName));
        }
        // 字段值
        String fieldValue = jsonParser.getText();
        return EnumsUtil.capableToEnum(Objects.getActualValue(fieldValue), (Class<E>) fieldType);
    }

}
