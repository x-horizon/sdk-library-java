// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.jackson.deserializer;

import cn.srd.library.java.contract.constant.jvm.SuppressWarningConstant;
import cn.srd.library.java.contract.model.throwable.DeserializerException;
import cn.srd.library.java.tool.convert.jackson.Jacksons;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.enums.Enums;
import cn.srd.library.java.tool.lang.object.Types;
import cn.srd.library.java.tool.lang.text.Strings;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;

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
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public List<E> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        String jsonFieldName = jsonParser.getCurrentName();
        Class<?> fieldOfClass = jsonParser.getParsingContext().getParent().getCurrentValue().getClass();
        Class<?> fieldGenericType = Types.getEmbedGenericTypeClass(fieldOfClass, jsonFieldName);
        if (Enums.isNotEnum(fieldGenericType)) {
            throw new DeserializerException(Strings.format("jackson deserializer: cannot deserializer field [{}] on class [{}] because the generic type in List.class is not Enum, please check!", jsonFieldName, fieldOfClass.getSimpleName()));
        }
        return Collections.toList(
                jsonParser.readValuesAs(Jacksons.<List<?>>newTypeReference()).next(),
                filedValue -> Enums.toEnumByFieldValue(filedValue, (Class<E>) fieldGenericType)
        );
    }

}
