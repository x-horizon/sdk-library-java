package cn.srd.library.tool.convert.jackson.support;

import cn.srd.library.tool.constant.core.TimePool;
import cn.srd.library.tool.lang.core.time.TimeUtil;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;

/**
 * Jackson 反序列化处理器：String =&gt; LocalDateTime（仅支持如：2023-03-27 21:41:07.76971）
 *
 * @author wjm
 * @since 2023-03-28 10:00:01
 */
public class JacksonStringToLocalDateTimeMS5Deserializer extends StdConverter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String from) {
        return TimeUtil.parse(from, TimePool.DATETIME_MS5_PATTERN);
    }

}
