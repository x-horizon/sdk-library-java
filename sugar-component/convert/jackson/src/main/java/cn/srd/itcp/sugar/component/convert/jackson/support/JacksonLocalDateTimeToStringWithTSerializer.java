package cn.srd.itcp.sugar.component.convert.jackson.support;

import cn.srd.itcp.sugar.tool.core.time.TimeUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.SneakyThrows;

import java.time.LocalDateTime;

/**
 * Jackson 序列化处理器：LocalDateTime =&gt; String，如：2011-12-03T10:15:30
 *
 * @author wjm
 * @since 2022-11-14 21:16:51
 */
public class JacksonLocalDateTimeToStringWithTSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    @SneakyThrows
    public void serialize(LocalDateTime from, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeObject(TimeUtil.toStringWithDateTimeAndT(from));
    }

}
