package cn.srd.itcp.sugar.convert.jackson.support;

import cn.srd.itcp.sugar.tool.constant.TimeZone;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Jackson 序列化处理器：LocalDateTime =&gt; String，包含年月日时分秒，东八时区
 *
 * @author wjm
 * @since 2022-11-14 21:16:51
 */
public class JacksonLocalDateTimeToStringWithEastEighthTimeZoneSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    @SneakyThrows
    public void serialize(LocalDateTime prepareToSerializerLocalDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeObject(prepareToSerializerLocalDateTime.toInstant(ZoneOffset.of(TimeZone.EAST_EIGHTH_TIME_ZONE)).toString());
    }

}
