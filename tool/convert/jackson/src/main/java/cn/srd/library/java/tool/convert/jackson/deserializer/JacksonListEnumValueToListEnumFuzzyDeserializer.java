// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.jackson.deserializer;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.tool.convert.jackson.JacksonFieldNameRegister;
import cn.srd.library.java.tool.convert.jackson.Jacksons;
import cn.srd.library.java.tool.lang.convert.Converts;
import cn.srd.library.java.tool.lang.enums.Enums;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Classes;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.object.Types;
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
 * @param <E> the data type after deserialize
 * @author wjm
 * @since 2023-06-16 17:26:48
 */
public class JacksonListEnumValueToListEnumFuzzyDeserializer<E extends Enum<E>> extends JsonDeserializer<List<E>> {

    @SneakyThrows
    @Override
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public List<E> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        String jsonFieldName = jsonParser.getCurrentName();
        Class<?> fieldOfClass = jsonParser.getParsingContext().getParent().getCurrentValue().getClass();

        Field matchField = null;
        String actualFieldName = JacksonFieldNameRegister.getInstance().getClassFieldName(jsonFieldName);
        if (Nil.isNotBlank(actualFieldName)) {
            matchField = Classes.getFieldDeep(fieldOfClass, actualFieldName);
        }
        if (Nil.isNull(matchField)) {
            matchField = Classes.getFieldFuzzy(fieldOfClass, jsonFieldName);
        }

        Assert.of().setMessage("jackson deserializer: cannot deserializer field [{}] on class [{}] because the json field name [{}] unable to match field, please check!", jsonFieldName, fieldOfClass.getSimpleName())
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfNull(matchField);

        Class<?> fieldGenericType = Types.getEmbedGenericTypeClass(fieldOfClass, matchField.getName());
        Assert.of().setMessage("jackson deserializer: cannot deserializer field [{}] on class [{}] because the generic type in List.class is not Enum, please check!", jsonFieldName, fieldOfClass.getSimpleName())
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfTrue(Enums.isNotEnum(fieldGenericType));

        return Converts.toList(
                jsonParser.readValuesAs(Jacksons.<List<?>>newTypeReference()).next(),
                filedValue -> Converts.toEnumByValue(filedValue, (Class<E>) fieldGenericType)
        );
    }

}
