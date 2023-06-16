package cn.srd.itcp.sugar.component.convert.jackson.support;

import cn.srd.itcp.sugar.component.convert.jackson.exception.JacksonDeserializerException;
import cn.srd.itcp.sugar.tool.core.EnumsUtil;
import cn.srd.itcp.sugar.tool.core.StringsUtil;
import cn.srd.itcp.sugar.tool.core.TypesUtil;
import cn.srd.itcp.sugar.tool.core.object.Objects;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * Jackson 反序列化处理器：List&lt;Enum 属性值&gt; =&gt; List&lt;Enum&gt;
 *
 * @author wjm
 * @since 2022-09-13 09:45:13
 */
public class JacksonListEnumValueToListEnumDeserializer<E extends Enum<E>> extends JsonDeserializer<List<E>> {

    @SneakyThrows
    @Override
    @SuppressWarnings("unchecked")
    public List<E> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        // json 中该字段的字段名
        String jsonFieldName = jsonParser.getCurrentName();
        // 字段所在的类
        Class<?> fieldOfClass = jsonParser.getParsingContext().getParent().getCurrentValue().getClass();
        // 字段类型
        Class<?> fieldType = TypesUtil.getTypeClass(fieldOfClass, jsonFieldName);
        if (Objects.notEquals(fieldType, List.class)) {
            throw new JacksonDeserializerException(StringsUtil.format(" 该类 “{}” 中的 “{}” 字段不是 List 类型, 无法反序列化，请检查！", fieldOfClass.getName(), jsonFieldName));
        }
        // 字段类型中的泛型类型
        Class<?> fieldGenericType = TypesUtil.getEmbedGenericTypeClass(fieldOfClass, jsonFieldName);
        if (EnumsUtil.isNotEnum(fieldGenericType)) {
            throw new JacksonDeserializerException(StringsUtil.format(" 该类 “{}” 中的 “{}” 字段类型 List 中的泛型类型不是枚举类型, 无法反序列化，请检查！", fieldOfClass.getName(), jsonFieldName));
        }
        // 字段值
        List<?> filedValues = jsonParser.readValuesAs(new TypeReference<List<?>>() {
        }).next();
        List<E> result = new ArrayList<>();
        filedValues.forEach(filedValue -> result.add(EnumsUtil.capableToEnum(filedValue, (Class<E>) fieldGenericType)));
        return result;
    }

}
