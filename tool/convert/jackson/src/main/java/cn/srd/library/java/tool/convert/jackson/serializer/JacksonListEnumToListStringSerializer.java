// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.jackson.serializer;

import cn.srd.library.java.tool.lang.enums.Enums;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * Jackson 序列化处理器：List&lt;Enum&gt; =&gt; List&lt;Enum 内部 String 类型的值&gt;
 *
 * @author wjm
 * @since 2024-04-16 21:32
 */
public class JacksonListEnumToListStringSerializer extends JsonSerializer<List<Enum<?>>> {

    @Override
    @SneakyThrows
    public void serialize(List<Enum<?>> from, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        List<String> prepareToSerializerValues = new ArrayList<>();
        from.forEach(prepareToSerializerEnum -> prepareToSerializerValues.add(Enums.getFieldValue(prepareToSerializerEnum, String.class)));
        jsonGenerator.writeObject(prepareToSerializerValues);
    }

    @Override
    @SneakyThrows
    public void serializeWithType(List<Enum<?>> value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        WritableTypeId typeIdDef = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(value, JsonToken.START_ARRAY));
        serialize(value, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, typeIdDef);
    }

}