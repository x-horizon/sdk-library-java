package cn.srd.itcp.sugar.component.convert.jackson.support;

import cn.srd.itcp.sugar.tool.core.time.TimeUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.SneakyThrows;

import java.time.LocalDateTime;

/**
 * Jackson 序列化处理器：LocalDateTime =&gt; Long
 *
 * @author wjm
 * @since 2022-10-14 18:16:31
 */
public class JacksonLocalDateTimeToLongSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    @SneakyThrows
    public void serialize(LocalDateTime prepareToSerializerLocalDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeObject(TimeUtil.toLong(prepareToSerializerLocalDateTime));
    }

}

