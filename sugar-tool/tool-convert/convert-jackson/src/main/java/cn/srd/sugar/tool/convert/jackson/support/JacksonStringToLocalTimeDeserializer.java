package cn.srd.sugar.tool.convert.jackson.support;

import cn.srd.itcp.sugar.tool.core.time.TimeUtil;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalTime;

/**
 * Jackson 反序列化处理器：String =&gt; LocalTime
 *
 * @author wjm
 * @since 2023-06-21 12:01:01
 */
public class JacksonStringToLocalTimeDeserializer extends StdConverter<String, LocalTime> {

    @Override
    public LocalTime convert(String from) {
        return TimeUtil.toLocalTime(from);
    }

}
