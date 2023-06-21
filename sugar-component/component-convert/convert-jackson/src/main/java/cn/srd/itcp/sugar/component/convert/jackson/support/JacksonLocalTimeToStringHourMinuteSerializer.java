package cn.srd.itcp.sugar.component.convert.jackson.support;

import cn.srd.itcp.sugar.tool.core.time.TimeUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.SneakyThrows;

import java.time.LocalTime;

/**
 * Jackson 序列化处理器：LocalTime =&gt; String，如：14:12
 *
 * @author wjm
 * @since 2023-06-21 12:01:01
 */
public class JacksonLocalTimeToStringHourMinuteSerializer extends JsonSerializer<LocalTime> {

    @SneakyThrows
    @Override
    public void serialize(LocalTime from, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeObject(TimeUtil.toStringHourMinute(from));
    }

}