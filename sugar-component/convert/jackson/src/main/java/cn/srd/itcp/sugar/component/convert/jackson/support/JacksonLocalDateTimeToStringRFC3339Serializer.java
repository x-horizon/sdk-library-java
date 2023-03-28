package cn.srd.itcp.sugar.component.convert.jackson.support;

import cn.srd.itcp.sugar.tool.core.time.TimeUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.SneakyThrows;

import java.time.LocalDateTime;

/**
 * Jackson 序列化处理器：LocalDateTime =&gt; String，遵循 RFC3339 标准，如：2006-01-02T15:04:05Z07:00
 *
 * @author wjm
 * @since 2023-03-28 10:00:01
 */
public class JacksonLocalDateTimeToStringRFC3339Serializer extends JsonSerializer<LocalDateTime> {

    @Override
    @SneakyThrows
    public void serialize(LocalDateTime from, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeObject(TimeUtil.toStringWithRFC3339DateTime(from));
    }

}
