// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.jackson.deserializer;

import cn.srd.library.java.contract.constant.jvm.SuppressWarningConstant;
import cn.srd.library.java.contract.model.throwable.DeserializerException;
import cn.srd.library.java.tool.convert.jackson.JacksonFieldNameRegister;
import cn.srd.library.java.tool.lang.enums.Enums;
import cn.srd.library.java.tool.lang.object.Classes;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.object.Objects;
import cn.srd.library.java.tool.lang.text.Strings;
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
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public E deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        String jsonFieldName = jsonParser.getCurrentName();
        Class<?> fieldOfClass = jsonParser.getCurrentValue().getClass();

        Field matchField = null;
        String actualFieldName = JacksonFieldNameRegister.getInstance().getClassFieldName(jsonFieldName);
        if (Nil.isNotBlank(actualFieldName)) {
            matchField = Classes.getFieldDeep(fieldOfClass, actualFieldName);
        }
        if (Nil.isNull(matchField)) {
            matchField = Classes.getFieldFuzzy(fieldOfClass, jsonFieldName);
        }
        if (Nil.isNull(matchField)) {
            throw new DeserializerException(Strings.format("jackson deserializer: cannot deserializer field [{}] on class [{}] because the json field name [{}] unable to match field, please check!", jsonFieldName, fieldOfClass.getSimpleName()));
        }

        Class<?> fieldType = matchField.getType();
        if (Enums.isNotEnum(fieldType)) {
            throw new DeserializerException(Strings.format("jackson deserializer: cannot deserializer field [{}] on class [{}] because the generic type is not Enum, please check!", jsonFieldName, fieldOfClass.getSimpleName()));
        }
        return Enums.toEnumByFieldValue(Objects.getActualValue(jsonParser.getText()), (Class<E>) fieldType);
    }

}
