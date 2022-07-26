package cn.srd.itcp.sugar.convert.jackson.support;

import cn.srd.itcp.sugar.convert.jackson.exception.JacksonDeserializerException;
import cn.srd.itcp.sugar.tools.constant.StringPool;
import cn.srd.itcp.sugar.tools.core.CollectionsUtil;
import cn.srd.itcp.sugar.tools.core.Objects;
import cn.srd.itcp.sugar.tools.core.StringsUtil;
import cn.srd.itcp.sugar.tools.core.asserts.Assert;
import cn.srd.itcp.sugar.tools.core.enums.EnumsUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Jackson 反序列化：Enum属性值 => Enum
 *
 * @author wjm
 * @since 2020/12/15 17:02
 */
public class JacksonCapableToEnumDeserializer<E extends Enum<E>> extends JsonDeserializer<E> {

    @Override
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public E deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        String prepareToDeserializerValue = jsonParser.getText();
        String prepareToDeserializerFieldName = jsonParser.getCurrentName();
        Class<?> classContainPrepareToDeserializerField = jsonParser.getCurrentValue().getClass();
        List<Field> mayMatchFields = Stream.of(classContainPrepareToDeserializerField.getDeclaredFields()).filter(declaredFiled -> StringsUtil.containsAnyIgnoreCase(declaredFiled.getName(), prepareToDeserializerFieldName)).collect(Collectors.toList());
        if (mayMatchFields.size() == 0) {
            Assert.INSTANCE.set(StringsUtil.format("反序列化失败：无法匹配要反序列化的字段，字段名：{} 无法找到其对应匹配的字段，请检查!", prepareToDeserializerFieldName)).throwsNow();
        }
        if (mayMatchFields.size() > 1) {
            Assert.INSTANCE.set(StringsUtil.format("反序列化失败：无法匹配要反序列化的字段，字段名：{} 对应匹配的字段有多个：{}，请检查!", prepareToDeserializerFieldName, StringsUtil.join(StringPool.COMMA, mayMatchFields))).throwsNow();
        }
        Class<?> prepareToDeserializerClass = CollectionsUtil.getFirst(mayMatchFields).getType();
        if (EnumsUtil.isNotEnum(prepareToDeserializerClass)) {
            throw new JacksonDeserializerException(StringsUtil.format(" 该类 “{}” 中的 “{}” 字段不是枚举类型, 无法反序列化，请检查！", classContainPrepareToDeserializerField.getName(), prepareToDeserializerFieldName));
        }
        return EnumsUtil.capableToEnum(Objects.getActualValue(prepareToDeserializerValue), (Class<E>) prepareToDeserializerClass);
    }

}
