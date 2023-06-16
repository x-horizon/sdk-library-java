package cn.srd.itcp.sugar.component.convert.jackson.support;

import cn.srd.itcp.sugar.component.convert.jackson.exception.JacksonDeserializerException;
import cn.srd.itcp.sugar.tool.constant.StringPool;
import cn.srd.itcp.sugar.tool.core.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Jackson 反序列化处理器：List&lt;Enum 属性值&gt; =&gt; List&lt;Enum&gt;（适用于 json 字段名与类字段名不同时的场景）
 * TODO wjm 这些类的实现逻辑高度相似，待优化：{@link JacksonEnumValueToEnumCapableDeserializer} {@link JacksonListEnumValueToListEnumCapableDeserializer} {@link JacksonEnumValueToEnumDeserializer} {@link JacksonListEnumValueToListEnumDeserializer}
 *
 * @author wjm
 * @since 2023-06-16 17:26:48
 */
public class JacksonListEnumValueToListEnumCapableDeserializer<E extends Enum<E>> extends JsonDeserializer<List<E>> {

    @SneakyThrows
    @Override
    @SuppressWarnings("unchecked")
    public List<E> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        // json 中该字段的字段名
        String jsonFieldName = jsonParser.getCurrentName();
        // 字段所在的类
        Class<?> fieldOfClass = jsonParser.getParsingContext().getParent().getCurrentValue().getClass();
        // 根据 json 中该字段的字段名 与 该字段所在类（包括父类）的所有字段名进行匹配后，可能匹配上的字段（json 字段名包含类字段名 或 类字段名包含 json 字段名）
        List<Field> mayMatchFields = Stream.of(ClassesUtil.getAllFieldsContainSuper(fieldOfClass)).filter(declaredFiled -> StringsUtil.containsAnyIgnoreCase(declaredFiled.getName(), jsonFieldName) || StringsUtil.containsAnyIgnoreCase(jsonFieldName, declaredFiled.getName())).toList();
        if (mayMatchFields.isEmpty()) {
            throw new JacksonDeserializerException(StringsUtil.format("反序列化失败：无法匹配要反序列化的字段，该类 “{}” 中的字段名：{} 无法找到其对应匹配的字段，请检查!", fieldOfClass.getName(), jsonFieldName));
        }
        if (mayMatchFields.size() > 1) {
            throw new JacksonDeserializerException(StringsUtil.format("反序列化失败：无法匹配要反序列化的字段，该类 “{}” 中的字段名：{} 对应匹配的字段有多个：{}，请检查!", fieldOfClass.getName(), jsonFieldName, StringsUtil.join(StringPool.COMMA, mayMatchFields)));
        }
        // 字段类型
        Class<?> fieldType = CollectionsUtil.getFirst(mayMatchFields).getType();
        if (EnumsUtil.isNotEnum(fieldType)) {
            throw new JacksonDeserializerException(StringsUtil.format(" 该类 “{}” 中的 “{}” 字段不是枚举类型, 无法反序列化，请检查！", fieldOfClass.getName(), jsonFieldName));
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
