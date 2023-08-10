package cn.srd.sugar.tool.convert.jackson.support;

import cn.srd.itcp.sugar.tool.core.object.Objects;
import cn.srd.sugar.tool.convert.jackson.core.JacksonFieldNameRegister;
import cn.srd.sugar.tool.convert.jackson.core.Jacksons;
import cn.srd.sugar.tool.convert.jackson.exception.JacksonDeserializerException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Jackson 反序列化处理器：List&lt;Enum 属性值&gt; =&gt; List&lt;Enum&gt;（适用于 json 字段名与类字段名不同时的场景）
 * TODO wjm 这些类的实现逻辑高度相似，待优化：{@link JacksonEnumValueToEnumFuzzyDeserializer} {@link JacksonListEnumValueToListEnumFuzzyDeserializer} {@link JacksonEnumValueToEnumDeserializer} {@link JacksonListEnumValueToListEnumDeserializer}
 * TODO wjm 待优化：应支持提前配置好，遇到某个字段名时直接获取对应要匹配的哪个类字段，而不是每次都要匹配，EnumAutowired 这种相关的匹配都可以这样优化
 *
 * @author wjm
 * @since 2023-06-16 17:26:48
 */
public class JacksonListEnumValueToListEnumFuzzyDeserializer<E extends Enum<E>> extends JsonDeserializer<List<E>> {

    @SneakyThrows
    @Override
    @SuppressWarnings("unchecked")
    public List<E> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        String jsonFieldName = jsonParser.getCurrentName();
        Class<?> fieldOfClass = jsonParser.getParsingContext().getParent().getCurrentValue().getClass();

        Field matchField = null;
        String actualFieldName = JacksonFieldNameRegister.getInstance().getClassFieldName(jsonFieldName);
        if (Objects.isNotBlank(actualFieldName)) {
            matchField = ClassesUtil.getFieldDeep(fieldOfClass, actualFieldName);
        }
        if (Objects.isNull(matchField)) {
            matchField = ClassesUtil.getFieldFuzzy(fieldOfClass, jsonFieldName);
        }
        if (Objects.isNull(matchField)) {
            throw new JacksonDeserializerException(StringsUtil.format("jackson deserializer: cannot deserializer field [{}] on class [{}] because the json field name [{}] unable to match field, please check!", jsonFieldName, fieldOfClass.getSimpleName()));
        }

        Class<?> fieldGenericType = TypesUtil.getEmbedGenericTypeClass(fieldOfClass, matchField.getName());
        if (EnumsUtil.isNotEnum(fieldGenericType)) {
            throw new JacksonDeserializerException(StringsUtil.format("jackson deserializer: cannot deserializer field [{}] on class [{}] because the generic type in List.class is not Enum, please check!", jsonFieldName, fieldOfClass.getSimpleName()));
        }
        return CollectionsUtil.toList(
                jsonParser.readValuesAs(Jacksons.<List<?>>newTypeReference()).next(),
                filedValue -> EnumsUtil.capableToEnum(filedValue, (Class<E>) fieldGenericType)
        );
    }

}
