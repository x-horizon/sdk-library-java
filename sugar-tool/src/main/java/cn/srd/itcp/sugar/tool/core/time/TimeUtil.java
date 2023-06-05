package cn.srd.itcp.sugar.tool.core.time;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.srd.itcp.sugar.tool.constant.TimePool;
import cn.srd.itcp.sugar.tool.constant.TimeUnitPool;
import cn.srd.itcp.sugar.tool.core.EnumsUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import cn.srd.itcp.sugar.tool.core.StringsUtil;
import cn.srd.itcp.sugar.tool.core.validation.Nullable;
import io.vavr.control.Try;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;
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
 * @since 2020/8/11 15:27
 */
public class TimeUtil extends LocalDateTimeUtil {

    /**
     * private block constructor
     */
    private TimeUtil() {
    }

    /**
     * 持续时间是否为正数，不包括 0
     *
     * @param input 输入参数
     * @return 持续时间是否为 0 或正数
     */
    public static boolean isPositive(Duration input) {
        return Objects.isNotNull(input) && !input.isNegative() && !input.isZero();
    }

    /**
     * 持续时间是否为负数，不包括 0
     *
     * @param input 输入参数
     * @return 持续时间是否为负数，不包括 0
     */
    public static boolean isNegative(Duration input) {
        return Objects.isNotNull(input) && input.isNegative();
    }

    /**
     * 持续时间是否为 0
     *
     * @param input 输入参数
     * @return 持续时间是否为 0
     */
    public static boolean isZero(Duration input) {
        return Objects.isNotNull(input) && input.isZero();
    }

    /**
     * 获取当前时间 String，支持所有的日期格式，默认格式：yyyy-MM-dd HH:mm:ss
     * <pre>
     * 示例：
     * 格式为：yyyy-MM-dd HH:mm:ss =&gt; 获取到的String：2020-11-21 18:43:36
     * 格式为：yyyy-MM-dd          =&gt; 获取到的String：2020-11-21
     * 格式为：HH:mm:ss            =&gt; 获取到的String：18:43:36
     * </pre>
     *
     * @param format 时间字符串
     * @return 当前时间对象
     * @see DatePattern#NORM_DATETIME_PATTERN
     */
    public static String getCurrentTimeString(@Nullable String format) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format == null ? DatePattern.NORM_DATETIME_PATTERN : format));
    }

    /**
     * LocalDateTime =&gt; String，如：2011-12-03 10:15:30
     *
     * @param input 输入时间对象
     * @return 时间字符串
     */
    public static String toStringWithDateTime(@Nullable LocalDateTime input) {
        return Objects.isNull(input) ? null : StringsUtil.replace(toStringWithDateTimeAndT(input), "T", " ");
    }

    /**
     * LocalDateTime =&gt; String，如：2011-12-03T10:15:30
     *
     * @param input 输入时间对象
     * @return 时间字符串
     */
    public static String toStringWithDateTimeAndT(@Nullable LocalDateTime input) {
        return Objects.isNull(input) ? null : DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(input);
    }

    /**
     * LocalDateTime =&gt; String，遵循 RFC3339 标准，如：2006-01-02T15:04:05Z07:00
     *
     * @param input 输入时间对象
     * @return 时间字符串
     */
    public static String toStringWithRFC3339DateTime(@Nullable LocalDateTime input) {
        return Objects.isNull(input) ? null : TimePool.DATETIME_FORMATTER_RFC3339.format(input);
    }

    /**
     * LocalDateTime =&gt; Long
     *
     * @param input 输入时间对象
     * @return 时间戳
     */
    public static Long toLong(@Nullable LocalDateTime input) {
        return Objects.isNull(input) ? null : input.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * LocalDate =&gt; Long
     *
     * @param input 输入时间对象
     * @return 时间戳
     */
    public static Long toLong(@Nullable LocalDate input) {
        return Objects.isNull(input) ? null : input.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * Long =&gt; LocalDateTime
     *
     * @param input 输入时间戳
     * @return 时间对象
     */
    public static LocalDateTime toLocalDateTime(@Nullable Long input) {
        return Objects.isNull(input) ? null : LocalDateTime.ofInstant(Instant.ofEpochMilli(input), ZoneId.systemDefault());
    }

    /**
     * Long =&gt; LocalDate
     *
     * @param input 输入时间戳
     * @return 时间对象
     */
    public static LocalDate toLocalDate(@Nullable Long input) {
        return Objects.isNull(input) ? null : LocalDate.ofInstant(Instant.ofEpochMilli(input), ZoneId.systemDefault());
    }

    /**
     * 获取日期最大值
     *
     * @param dates 输入日期对象
     * @return 日期最大值
     */
    @Nullable
    public static Date max(Date... dates) {
        if (Objects.isAllEmpty((Object[]) dates)) {
            return null;
        }
        return Stream.of(dates).filter(Objects::isNotEmpty).max(Comparator.naturalOrder()).orElse(null);
    }

    /**
     * {@link TimeUnitHandler} wrapper
     *
     * @param time     时间
     * @param timeUnit 时间单位
     * @return {@link TimeUnitHandler}
     */
    public static TimeUnitHandler wrapper(long time, TimeUnit timeUnit) {
        return TimeUnitHandler.TIME_UNIT_MAPPING_HANDLER_MAP.get(timeUnit).newInstance().setTime(time);
    }

    /**
     * {@link TimeUnitHandler} wrapper
     *
     * @param time         时间
     * @param timeUnitPool 时间单位
     * @return {@link TimeUnitHandler}
     */
    public static TimeUnitHandler wrapper(long time, TimeUnitPool timeUnitPool) {
        return TimeUnitHandler.TIME_UNIT_POOL_MAPPING_HANDLER_MAP.get(timeUnitPool).newInstance().setTime(time);
    }

    /**
     * {@link TimeUnitHandler} wrapper
     *
     * @param timeFormat 时间字符串，如 "2s"、"2h"
     * @return {@link TimeUnitHandler}
     */
    public static TimeUnitHandler wrapper(String timeFormat) {
        for (UnaryOperator<String> convertToTimeUnitFunction : TimeUnitHandler.CONVERT_TO_TIME_UNIT_FUNCTIONS) {
            String timeUnit = convertToTimeUnitFunction.apply(timeFormat);
            TimeUnitPool timeUnitPool = EnumsUtil.capableToEnum(timeUnit, TimeUnitPool.class);
            if (Objects.isNotNull(timeUnitPool)) {
                long time = Try.of(() -> Long.parseLong(StringsUtil.removeAny(timeFormat, timeUnit)))
                        .onFailure(throwable -> {
                            throw new RuntimeException(StringsUtil.format("invalid time by remove [{}] from input [{}]", timeUnit, timeFormat), throwable);
                        })
                        .get();
                return wrapper(time, timeUnitPool);
            }
        }
        throw new RuntimeException(StringsUtil.format("invalid time format by input [{}]", timeFormat));
    }

}
