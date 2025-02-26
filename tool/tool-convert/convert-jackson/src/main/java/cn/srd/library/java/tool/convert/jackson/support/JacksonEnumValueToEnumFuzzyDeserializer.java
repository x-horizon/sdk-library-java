package cn.srd.library.java.tool.convert.jackson.support;

import cn.srd.library.java.tool.convert.jackson.core.JacksonFieldNameRegister;
import cn.srd.library.java.tool.convert.jackson.exception.JacksonDeserializerException;
import cn.srd.library.java.tool.lang.core.ClassesUtil;
import cn.srd.library.java.tool.lang.core.EnumsUtil;
import cn.srd.library.java.tool.lang.core.StringsUtil;
import cn.srd.library.java.tool.lang.core.object.Objects;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

/**
 * Jackson 反序列化处理器：Enum 属性值 =&gt; Enum（适用于 json 字段名与类字段名不同时的场景）
 *
 * @author wjm
 * @since 2020/12/15 17:02
 */
public class JacksonEnumValueToEnumFuzzyDeserializer<E extends Enum<E>> extends JsonDeserializer<E> {

    @Override
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public E deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        String jsonFieldName = jsonParser.getCurrentName();
        Class<?> fieldOfClass = jsonParser.getCurrentValue().getClass();

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

        Class<?> fieldType = matchField.getType();
        if (EnumsUtil.isNotEnum(fieldType)) {
            throw new JacksonDeserializerException(StringsUtil.format("jackson deserializer: cannot deserializer field [{}] on class [{}] because the generic type is not Enum, please check!", jsonFieldName, fieldOfClass.getSimpleName()));
        }
        return EnumsUtil.capableToEnum(Objects.getActualValue(jsonParser.getText()), (Class<E>) fieldType);
    }

}
