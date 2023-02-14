package cn.srd.itcp.sugar.component.convert.jackson.support;

import cn.srd.itcp.sugar.tool.core.time.TimeUtil;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Jackson 反序列化处理器：String =&gt; LocalDateTime（只包含日期，如 2038-01-01）
 *
 * @author wjm
 * @since 2022-07-20 11:37:28
 */
public class JacksonStringToLocalDateTimeDeserializer extends StdConverter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String value) {
        return TimeUtil.parse(value, DateTimeFormatter.ISO_DATE);
    }

}
