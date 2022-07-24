package cn.srd.itcp.sugar.tools.core;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Stream;

/**
 * 时间工具
 * <pre>
 * 关键类：
 * Instant：瞬时实例；
 * LocalDate：本地日期，不包含具体时间，如：2020-01-14；
 * LocalTime：本地时间，不包含日期；
 * LocalDateTime：组合了日期和时间，但不包含时差和时区信息；
 * ZonedDateTime：最完整的日期时间，包含时区和相对UTC或格林威治的时差；
 * DateTimeFormatter：时间格式；
 * </pre>
 *
 * @author wjm
 * @date 2020/8/11 15:27
 */
public class TimeUtil extends LocalDateTimeUtil {

    private TimeUtil() {
    }

    /**
     * 获取当前时间 String，支持所有的日期格式，默认格式：yyyy-MM-dd HH:mm:ss
     * <pre>
     * 示例：
     * 格式为：yyyy-MM-dd HH:mm:ss => 获取到的String：2020-11-21 18:43:36
     * 格式为：yyyy-MM-dd          => 获取到的String：2020-11-21
     * 格式为：HH:mm:ss            => 获取到的String：18:43:36
     *
     * @param format
     * @return
     * @see DatePattern#NORM_DATETIME_PATTERN
     */
    public static String getCurrentTimeString(@Nullable String format) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format == null ? DatePattern.NORM_DATETIME_PATTERN : format));
    }

    /**
     * LocalDateTime => Long
     *
     * @param localDateTime
     * @return
     */
    public static Long toLong(LocalDateTime localDateTime) {
        return Objects.isNull(localDateTime) ? null : localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取日期最大值
     *
     * @param dates
     * @return
     */
    @Nullable
    public static Date max(Date... dates) {
        if (Objects.isAllEmpty(dates)) {
            return null;
        }
        return Stream.of(dates).filter(Objects::isNotEmpty).max(Comparator.naturalOrder()).orElse(null);
    }

}
