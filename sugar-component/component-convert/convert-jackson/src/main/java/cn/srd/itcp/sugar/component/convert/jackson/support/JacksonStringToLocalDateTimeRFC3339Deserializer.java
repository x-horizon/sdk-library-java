package cn.srd.itcp.sugar.component.convert.jackson.support;

import cn.srd.itcp.sugar.tool.constant.TimePool;
import cn.srd.itcp.sugar.tool.core.time.TimeUtil;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;

/**
 * Jackson 反序列化处理器：String =&gt; LocalDateTime（遵循 RFC3339 标准，仅支持如：2006-01-02T15:04:05Z07:00）
 *
 * @author wjm
 * @since 2023-03-28 10:00:01
 */
public class JacksonStringToLocalDateTimeRFC3339Deserializer extends StdConverter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String from) {
        return TimeUtil.parse(from, TimePool.DATETIME_RFC3339_CHINA_TIMEZONE_PATTERN);
    }

}
