package cn.srd.itcp.sugar.component.convert.jackson.support;

import cn.srd.itcp.sugar.tool.core.time.TimeUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.SneakyThrows;

import java.time.LocalDate;

/**
 * Jackson 序列化处理器：LocalDate =&gt; String
 *
 * @author wjm
 * @since 2023-06-21 12:01:01
 */
public class JacksonLocalDateToStringSerializer extends JsonSerializer<LocalDate> {

    @SneakyThrows
    @Override
    public void serialize(LocalDate from, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeObject(TimeUtil.formatNormal(from));
    }

}