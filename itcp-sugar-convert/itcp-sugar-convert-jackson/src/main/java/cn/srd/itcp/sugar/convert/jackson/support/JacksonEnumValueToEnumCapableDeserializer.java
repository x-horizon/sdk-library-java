package cn.srd.itcp.sugar.convert.jackson.support;

import cn.srd.itcp.sugar.convert.jackson.exception.JacksonDeserializerException;
import cn.srd.itcp.sugar.tool.constant.StringPool;
import cn.srd.itcp.sugar.tool.core.CollectionsUtil;
import cn.srd.itcp.sugar.tool.core.EnumsUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import cn.srd.itcp.sugar.tool.core.StringsUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Jackson 反序列化处理器：Enum 属性值 =&gt; Enum（适用于 json 字段名与类字段名不同时的场景）
 *
 * @author wjm
 * @since 2020/12/15 17:02
 */
public class JacksonEnumValueToEnumCapableDeserializer<E extends Enum<E>> extends JsonDeserializer<E> {

    @Override
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public E deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        // json 中该字段的字段名
        String jsonFieldName = jsonParser.getCurrentName();
        // 字段所在的类
        Class<?> fieldOfClass = jsonParser.getCurrentValue().getClass();
        // 根据 json 中该字段的字段名 与 该字段所在类的所有字段名进行匹配后，可能匹配上的字段
        List<Field> mayMatchFields = Stream.of(fieldOfClass.getDeclaredFields()).filter(declaredFiled -> StringsUtil.containsAnyIgnoreCase(declaredFiled.getName(), jsonFieldName)).collect(Collectors.toList());
        if (mayMatchFields.size() == 0) {
            throw new JacksonDeserializerException(StringsUtil.format("反序列化失败：无法匹配要反序列化的字段，字段名：{} 无法找到其对应匹配的字段，请检查!", jsonFieldName));
        }
        if (mayMatchFields.size() > 1) {
            throw new JacksonDeserializerException(StringsUtil.format("反序列化失败：无法匹配要反序列化的字段，字段名：{} 对应匹配的字段有多个：{}，请检查!", jsonFieldName, StringsUtil.join(StringPool.COMMA, mayMatchFields)));
        }
        // 字段类型
        Class<?> fieldType = CollectionsUtil.getFirst(mayMatchFields).getType();
        if (EnumsUtil.isNotEnum(fieldType)) {
            throw new JacksonDeserializerException(StringsUtil.format(" 该类 “{}” 中的 “{}” 字段不是枚举类型, 无法反序列化，请检查！", fieldOfClass.getName(), jsonFieldName));
        }
        // 字段值
        String fieldValue = jsonParser.getText();
        return EnumsUtil.capableToEnum(Objects.getActualValue(fieldValue), (Class<E>) fieldType);
    }

}
