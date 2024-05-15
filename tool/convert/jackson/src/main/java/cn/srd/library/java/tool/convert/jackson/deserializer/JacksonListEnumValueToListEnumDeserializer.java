// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.jackson.deserializer;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.tool.convert.jackson.Jacksons;
import cn.srd.library.java.tool.lang.convert.Converts;
import cn.srd.library.java.tool.lang.enums.Enums;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Types;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;

import java.util.List;

/**
 * Jackson 反序列化处理器：List&lt;Enum 属性值&gt; =&gt; List&lt;Enum&gt;
 *
 * @param <E> the data type after deserialize
 * @author wjm
 * @since 2022-09-13 09:45
 */
public class JacksonListEnumValueToListEnumDeserializer<E extends Enum<E>> extends JsonDeserializer<List<E>> {

    @SneakyThrows
    @Override
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public List<E> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        String jsonFieldName = jsonParser.getCurrentName();
        Class<?> fieldOfClass = jsonParser.getParsingContext().getParent().getCurrentValue().getClass();
        Class<?> fieldGenericType = Types.getEmbedGenericTypeClass(fieldOfClass, jsonFieldName);

        Assert.of().setMessage("jackson deserializer: cannot deserializer field [{}] on class [{}] because the generic type in List.class is not Enum, please check!", jsonFieldName, fieldOfClass.getSimpleName())
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfTrue(Enums.isNotEnum(fieldGenericType));

        return Converts.toList(
                jsonParser.readValuesAs(Jacksons.<List<?>>newTypeReference()).next(),
                filedValue -> Converts.toEnumByValue(filedValue, (Class<E>) fieldGenericType)
        );
    }

}