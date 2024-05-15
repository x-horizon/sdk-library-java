// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.jackson.serializer;

import cn.srd.library.java.tool.lang.time.Times;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import lombok.SneakyThrows;

import java.time.LocalDateTime;

/**
 * Jackson 序列化处理器：LocalDateTime =&gt; String，如：2011-12-03 10:15:30.9745
 *
 * @author wjm
 * @since 2023-07-27 21:50
 */
public class JacksonLocalDateTimeToStringMS4Serializer extends JsonSerializer<LocalDateTime> {

    @Override
    @SneakyThrows
    public void serialize(LocalDateTime from, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeObject(Times.toStringWithDateTimeMS4(from));
    }

    @Override
    @SneakyThrows
    public void serializeWithType(LocalDateTime value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        WritableTypeId typeIdDef = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(value, JsonToken.VALUE_STRING));
        serialize(value, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, typeIdDef);
    }

}