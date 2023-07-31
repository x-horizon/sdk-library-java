package cn.srd.itcp.sugar.tool.core.time;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.date.TemporalAccessorUtil;
import cn.hutool.core.date.Week;
import cn.srd.itcp.sugar.tool.constant.StringPool;
import cn.srd.itcp.sugar.tool.constant.TimePool;
import cn.srd.itcp.sugar.tool.constant.TimeUnitPool;
import cn.srd.itcp.sugar.tool.core.EnumsUtil;
import cn.srd.itcp.sugar.tool.core.StringsUtil;
import cn.srd.itcp.sugar.tool.core.object.Objects;
import cn.srd.itcp.sugar.tool.core.validation.Nullable;
import io.vavr.control.Try;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
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
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUtil extends LocalDateTimeUtil {

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
     * get {@link LocalDateTime} now
     *
     * @return the {@link LocalDateTime} now
     */
    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
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
    public static String getCurrentDateTime(@Nullable String format) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format == null ? DatePattern.NORM_DATETIME_PATTERN : format));
    }

    /**
     * get {@link LocalDate} now
     *
     * @return the {@link LocalDate} now
     */
    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    /**
     * get {@link LocalTime} now
     *
     * @return the {@link LocalTime} now
     */
    public static LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    /**
     * get current weekday
     *
     * @return current weekday
     */
    public static WeekdayTypeEnum getCurrentWeekday() {
        return WeekdayTypeEnum.convertByHutoolWeek(Week.of(getCurrentDate().getDayOfWeek()));
    }

    /**
     * 获取最早的日期，see {@link TimePool#EARLIEST_DATE}
     *
     * @return 最早的日期
     */
    public static LocalDate getEarliestDate() {
        return toLocalDate(TimePool.EARLIEST_DATE);
    }

    /**
     * 获取最晚的日期，see {@link TimePool#LATEST_DATE}
     *
     * @return 最晚的日期
     */
    public static LocalDate getLatestDate() {
        return toLocalDate(TimePool.LATEST_DATE);
    }

    /**
     * LocalTime =&gt; String，如：14:12
     *
     * @param input 输入时间对象
     * @return 时间字符串
     */
    public static String toStringHourMinute(@Nullable LocalTime input) {
        return Objects.isNull(input) ? StringPool.EMPTY : input.format(DateTimeFormatter.ofPattern(TimePool.TIME_HOUR_MINUTE_PATTERN));
    }

    /**
     * LocalTime =&gt; String，如：14:12:11
     *
     * @param input 输入时间对象
     * @return 时间字符串
     */
    public static String toStringHourMinuteSecond(@Nullable LocalTime input) {
        return Objects.isNull(input) ? StringPool.EMPTY : input.format(DateTimeFormatter.ofPattern(TimePool.TIME_HOUR_MINUTE_SECOND_PATTERN));
    }

    /**
     * LocalDateTime =&gt; String，自定义时间格式；
     *
     * @param input  输入时间对象
     * @param format 格式化，see {@link TimePool}
     * @return 时间字符串
     */
    public static String toStringWithDateTime(@Nullable LocalDateTime input, String format) {
        return Objects.isNull(input) ? StringPool.EMPTY : DateTimeFormatter.ofPattern(format).format(input);
    }

    /**
     * LocalDateTime =&gt; String，如：2011-12-03 10:15:30
     *
     * @param input 输入时间对象
     * @return 时间字符串
     */
    public static String toStringWithDateTimeMS0(@Nullable LocalDateTime input) {
        return Objects.isNull(input) ? StringPool.EMPTY : DateTimeFormatter.ofPattern(TimePool.DATETIME_MS0_PATTERN).format(input);
    }

    /**
     * LocalDateTime =&gt; String，如：2011-12-03 10:15:30.9
     *
     * @param input 输入时间对象
     * @return 时间字符串
     */
    public static String toStringWithDateTimeMS1(@Nullable LocalDateTime input) {
        return Objects.isNull(input) ? StringPool.EMPTY : DateTimeFormatter.ofPattern(TimePool.DATETIME_MS1_PATTERN).format(input);
    }

    /**
     * LocalDateTime =&gt; String，如：2011-12-03 10:15:30.97
     *
     * @param input 输入时间对象
     * @return 时间字符串
     */
    public static String toStringWithDateTimeMS2(@Nullable LocalDateTime input) {
        return Objects.isNull(input) ? StringPool.EMPTY : DateTimeFormatter.ofPattern(TimePool.DATETIME_MS2_PATTERN).format(input);
    }

    /**
     * LocalDateTime =&gt; String，如：2011-12-03 10:15:30.974
     *
     * @param input 输入时间对象
     * @return 时间字符串
     */
    public static String toStringWithDateTimeMS3(@Nullable LocalDateTime input) {
        return Objects.isNull(input) ? StringPool.EMPTY : DateTimeFormatter.ofPattern(TimePool.DATETIME_MS3_PATTERN).format(input);
    }

    /**
     * LocalDateTime =&gt; String，如：2011-12-03 10:15:30.9745
     *
     * @param input 输入时间对象
     * @return 时间字符串
     */
    public static String toStringWithDateTimeMS4(@Nullable LocalDateTime input) {
        return Objects.isNull(input) ? StringPool.EMPTY : DateTimeFormatter.ofPattern(TimePool.DATETIME_MS4_PATTERN).format(input);
    }

    /**
     * LocalDateTime =&gt; String，如：2011-12-03 10:15:30.97451
     *
     * @param input 输入时间对象
     * @return 时间字符串
     */
    public static String toStringWithDateTimeMS5(@Nullable LocalDateTime input) {
        return Objects.isNull(input) ? StringPool.EMPTY : DateTimeFormatter.ofPattern(TimePool.DATETIME_MS5_PATTERN).format(input);
    }

    /**
     * LocalDateTime =&gt; String，如：2011-12-03 10:15:30.974515
     *
     * @param input 输入时间对象
     * @return 时间字符串
     */
    public static String toStringWithDateTimeMS6(@Nullable LocalDateTime input) {
        return Objects.isNull(input) ? StringPool.EMPTY : DateTimeFormatter.ofPattern(TimePool.DATETIME_MS6_PATTERN).format(input);
    }

    /**
     * LocalDateTime =&gt; String，如：2011-12-03T10:15:30.974515（如果纳秒后面为 0，则省略，如：2011-12-03T10:15:30.97451）
     *
     * @param input 输入时间对象
     * @return 时间字符串
     */
    public static String toStringWithISODateTime(@Nullable LocalDateTime input) {
        return Objects.isNull(input) ? StringPool.EMPTY : DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(input);
    }

    /**
     * LocalDateTime =&gt; String，遵循 RFC3339 标准，如：2006-01-02T15:04:05Z07:00
     *
     * @param input 输入时间对象
     * @return 时间字符串
     */
    public static String toStringWithRFC3339DateTime(@Nullable LocalDateTime input) {
        return Objects.isNull(input) ? StringPool.EMPTY : TimePool.DATETIME_FORMATTER_RFC3339.format(input);
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
     * String =&gt; LocalDate
     *
     * @param input 输入时间字符串
     * @return 时间对象
     */
    public static LocalDate toLocalDate(@Nullable String input) {
        return Objects.isNull(input) ? null : LocalDate.parse(input);
    }

    /**
     * String =&gt; LocalTime
     *
     * @param input 输入时间字符串
     * @return 时间对象
     */
    public static LocalTime toLocalTime(@Nullable String input) {
        return Objects.isNull(input) ? null : LocalTime.parse(input);
    }

    /**
     * Duration =&gt; {@link DurationWrapper}
     *
     * @param duration the actual duration to convert
     * @return {@link DurationWrapper}
     */
    public static DurationWrapper toDurationWrapper(@NonNull Duration duration) {
        if (hasMillisecond(duration)) {
            return DurationWrapper.builder().negativeIs(isNegative(duration)).time(duration.toMillis()).timeUnit(TimeUnit.MILLISECONDS).build();
        }
        return DurationWrapper.builder().negativeIs(isNegative(duration)).time(duration.getSeconds()).timeUnit(TimeUnit.SECONDS).build();
    }

    /**
     * copy from TimeoutUtils#hasMillis(Duration)
     *
     * @param duration the actual duration to inspect. Never null.
     * @return true if the duration contains millisecond information.
     */
    public static boolean hasMillisecond(Duration duration) {
        return duration.toMillis() % 1000 != 0;
    }

    /**
     * is the current time between the beginning time and the end time
     *
     * @param beginTime the beginning time(range include the beginning time)
     * @param endTime   the end time(range include the end time)
     * @return between the beginning time and the end time or not
     */
    public static boolean isIn(LocalDateTime beginTime, LocalDateTime endTime) {
        return isIn(getCurrentDateTime(), beginTime, endTime, true, true);
    }

    /**
     * see {@link #isIn(LocalDateTime, LocalDateTime)}
     *
     * @param beginTime        the beginning time
     * @param endTime          the end time
     * @param includeBeginTime is the checked time range include the beginning time
     * @param includeEndTime   is the checked time range include the end time
     * @return between the beginning time and the end time or not
     */
    public static boolean isIn(LocalDateTime beginTime, LocalDateTime endTime, boolean includeBeginTime, boolean includeEndTime) {
        return isIn(getCurrentDateTime(), beginTime, endTime, includeBeginTime, includeEndTime);
    }

    /**
     * see {@link #isIn(LocalDateTime, LocalDateTime)}
     *
     * @param beginTime the beginning time(range include the beginning time)
     * @param endTime   the end time(range include the end time)
     * @return between the beginning time and the end time or not
     */
    public static boolean isIn(LocalDate beginTime, LocalDate endTime) {
        return isIn(getCurrentDate(), beginTime, endTime, true, true);
    }

    /**
     * see {@link #isIn(LocalDateTime, LocalDateTime)}
     *
     * @param beginTime        the beginning time
     * @param endTime          the end time
     * @param includeBeginTime is the checked time range include the beginning time
     * @param includeEndTime   is the checked time range include the end time
     * @return between the beginning time and the end time or not
     */
    public static boolean isIn(LocalDate beginTime, LocalDate endTime, boolean includeBeginTime, boolean includeEndTime) {
        return isIn(getCurrentDate(), beginTime, endTime, includeBeginTime, includeEndTime);
    }

    /**
     * see {@link #isIn(LocalDateTime, LocalDateTime)}
     *
     * @param beginTime the beginning time(range include the beginning time)
     * @param endTime   the end time(range include the end time)
     * @return between the beginning time and the end time or not
     */
    public static boolean isIn(LocalTime beginTime, LocalTime endTime) {
        return isIn(getCurrentTime(), beginTime, endTime, true, true);
    }

    /**
     * see {@link #isIn(LocalDateTime, LocalDateTime)}
     *
     * @param beginTime        the beginning time
     * @param endTime          the end time
     * @param includeBeginTime is the checked time range include the beginning time
     * @param includeEndTime   is the checked time range include the end time
     * @return between the beginning time and the end time or not
     */
    public static boolean isIn(LocalTime beginTime, LocalTime endTime, boolean includeBeginTime, boolean includeEndTime) {
        return isIn(getCurrentTime(), beginTime, endTime, includeBeginTime, includeEndTime);
    }

    /**
     * see {@link #isIn(LocalDateTime, LocalDateTime)}
     *
     * @param beginTime the beginning time(range include the beginning time)
     * @param endTime   the end time(range include the end time)
     * @return between the beginning time and the end time or not
     */
    public static boolean isIn(WeekdayTypeEnum beginTime, WeekdayTypeEnum endTime) {
        return isIn(getCurrentWeekday(), beginTime, endTime, true, true);
    }

    /**
     * see {@link #isIn(LocalDateTime, LocalDateTime)}
     *
     * @param beginTime        the beginning time
     * @param endTime          the end time
     * @param includeBeginTime is the checked time range include the beginning time
     * @param includeEndTime   is the checked time range include the end time
     * @return between the beginning time and the end time or not
     */
    public static boolean isIn(WeekdayTypeEnum beginTime, WeekdayTypeEnum endTime, boolean includeBeginTime, boolean includeEndTime) {
        return isIn(getCurrentWeekday(), beginTime, endTime, includeBeginTime, includeEndTime);
    }

    /**
     * is the checked time between the beginning time and the end time
     *
     * @param checkedTime the checked time
     * @param beginTime   the beginning time
     * @param endTime     the end time
     * @return between the beginning time and the end time or not
     */
    public static boolean isIn(WeekdayTypeEnum checkedTime, WeekdayTypeEnum beginTime, WeekdayTypeEnum endTime) {
        return isIn(checkedTime, beginTime, endTime, true, true);
    }

    /**
     * see {@link #isIn(WeekdayTypeEnum, WeekdayTypeEnum, WeekdayTypeEnum)}
     *
     * @param checkedTime      the checked time
     * @param beginTime        the beginning time
     * @param endTime          the end time
     * @param includeBeginTime is the checked time range include the beginning time
     * @param includeEndTime   is the checked time range include the end time
     * @return between the beginning time and the end time or not
     */
    public static boolean isIn(WeekdayTypeEnum checkedTime, WeekdayTypeEnum beginTime, WeekdayTypeEnum endTime, boolean includeBeginTime, boolean includeEndTime) {
        return includeBeginTime ? checkedTime.getCode() >= beginTime.getCode() : checkedTime.getCode() > beginTime.getCode()
                &&
                includeEndTime ? checkedTime.getCode() <= endTime.getCode() : checkedTime.getCode() < endTime.getCode();
    }

    /**
     * see {@link #isIn(TemporalAccessor, TemporalAccessor, TemporalAccessor, boolean, boolean)}
     *
     * @param checkedTime the time will be checked
     * @param beginTime   the beginning time
     * @param endTime     the end time
     * @return is the checked time between the beginning time and the end time
     */
    public static boolean isIn(TemporalAccessor checkedTime, TemporalAccessor beginTime, TemporalAccessor endTime) {
        return isIn(checkedTime, beginTime, endTime, true, true);
    }

    /**
     * see {@link TemporalAccessorUtil#isIn(TemporalAccessor, TemporalAccessor, TemporalAccessor, boolean, boolean)}
     *
     * @param checkedTime      the time will be checked
     * @param beginTime        the beginning time
     * @param endTime          the end time
     * @param includeBeginTime is the checked time range include the beginning time
     * @param includeEndTime   is the checked time range include the end time
     * @return is the checked time between the beginning time and the end time
     */
    public static boolean isIn(TemporalAccessor checkedTime, TemporalAccessor beginTime, TemporalAccessor endTime, boolean includeBeginTime, boolean includeEndTime) {
        return TemporalAccessorUtil.isIn(checkedTime, beginTime, endTime, includeBeginTime, includeEndTime);
    }

    /**
     * see {@link #isIn(LocalDateTime, LocalDateTime)}
     *
     * @param beginTime the beginning time(range include the beginning time)
     * @param endTime   the end time(range include the end time)
     * @return between the beginning time and the end time or not
     */
    public static boolean isNotIn(LocalDateTime beginTime, LocalDateTime endTime) {
        return !isIn(beginTime, endTime);
    }

    /**
     * see {@link #isIn(LocalDateTime, LocalDateTime)}
     *
     * @param beginTime        the beginning time
     * @param endTime          the end time
     * @param includeBeginTime is the checked time range include the beginning time
     * @param includeEndTime   is the checked time range include the end time
     * @return between the beginning time and the end time or not
     */
    public static boolean isNotIn(LocalDateTime beginTime, LocalDateTime endTime, boolean includeBeginTime, boolean includeEndTime) {
        return !isIn(beginTime, endTime, includeBeginTime, includeEndTime);
    }

    /**
     * see {@link #isIn(LocalDate, LocalDate)}
     *
     * @param beginTime the beginning time(range include the beginning time)
     * @param endTime   the end time(range include the end time)
     * @return between the beginning time and the end time or not
     */
    public static boolean isNotIn(LocalDate beginTime, LocalDate endTime) {
        return !isIn(beginTime, endTime);
    }

    /**
     * see {@link #isIn(LocalDate, LocalDate)}
     *
     * @param beginTime        the beginning time
     * @param endTime          the end time
     * @param includeBeginTime is the checked time range include the beginning time
     * @param includeEndTime   is the checked time range include the end time
     * @return between the beginning time and the end time or not
     */
    public static boolean isNotIn(LocalDate beginTime, LocalDate endTime, boolean includeBeginTime, boolean includeEndTime) {
        return !isIn(beginTime, endTime, includeBeginTime, includeEndTime);
    }

    /**
     * see {@link #isIn(LocalTime, LocalTime)}
     *
     * @param beginTime the beginning time(range include the beginning time)
     * @param endTime   the end time(range include the end time)
     * @return between the beginning time and the end time or not
     */
    public static boolean isNotIn(LocalTime beginTime, LocalTime endTime) {
        return !isIn(beginTime, endTime);
    }

    /**
     * see {@link #isIn(LocalTime, LocalTime)}
     *
     * @param beginTime        the beginning time
     * @param endTime          the end time
     * @param includeBeginTime is the checked time range include the beginning time
     * @param includeEndTime   is the checked time range include the end time
     * @return between the beginning time and the end time or not
     */
    public static boolean isNotIn(LocalTime beginTime, LocalTime endTime, boolean includeBeginTime, boolean includeEndTime) {
        return !isIn(beginTime, endTime, includeBeginTime, includeEndTime);
    }

    /**
     * see {@link #isIn(WeekdayTypeEnum, WeekdayTypeEnum)}
     *
     * @param beginTime the beginning time(range include the beginning time)
     * @param endTime   the end time(range include the end time)
     * @return between the beginning time and the end time or not
     */
    public static boolean isNotIn(WeekdayTypeEnum beginTime, WeekdayTypeEnum endTime) {
        return !isIn(beginTime, endTime);
    }

    /**
     * see {@link #isIn(WeekdayTypeEnum, WeekdayTypeEnum)}
     *
     * @param beginTime        the beginning time
     * @param endTime          the end time
     * @param includeBeginTime is the checked time range include the beginning time
     * @param includeEndTime   is the checked time range include the end time
     * @return between the beginning time and the end time or not
     */
    public static boolean isNotIn(WeekdayTypeEnum beginTime, WeekdayTypeEnum endTime, boolean includeBeginTime, boolean includeEndTime) {
        return !isIn(beginTime, endTime, includeBeginTime, includeEndTime);
    }

    /**
     * see {@link #isIn(WeekdayTypeEnum, WeekdayTypeEnum, WeekdayTypeEnum)}
     *
     * @param checkedTime the checked time
     * @param beginTime   the beginning time
     * @param endTime     the end time
     * @return between the beginning time and the end time or not
     */
    public static boolean isNotIn(WeekdayTypeEnum checkedTime, WeekdayTypeEnum beginTime, WeekdayTypeEnum endTime) {
        return !isIn(checkedTime, beginTime, endTime);
    }

    /**
     * see {@link #isIn(WeekdayTypeEnum, WeekdayTypeEnum, WeekdayTypeEnum, boolean, boolean)}
     *
     * @param checkedTime      the checked time
     * @param beginTime        the beginning time
     * @param endTime          the end time
     * @param includeBeginTime is the checked time range include the beginning time
     * @param includeEndTime   is the checked time range include the end time
     * @return between the beginning time and the end time or not
     */
    public static boolean isNotIn(WeekdayTypeEnum checkedTime, WeekdayTypeEnum beginTime, WeekdayTypeEnum endTime, boolean includeBeginTime, boolean includeEndTime) {
        return !isIn(checkedTime, beginTime, endTime, includeBeginTime, includeEndTime);
    }

    /**
     * see {@link #isIn(TemporalAccessor, TemporalAccessor, TemporalAccessor)}
     *
     * @param checkedTime the time will be checked
     * @param beginTime   the beginning time
     * @param endTime     the end time
     * @return is the checked time between the beginning time and the end time
     */
    public static boolean isNotIn(TemporalAccessor checkedTime, TemporalAccessor beginTime, TemporalAccessor endTime) {
        return !isIn(checkedTime, beginTime, endTime);
    }

    /**
     * see {@link #isIn(TemporalAccessor, TemporalAccessor, TemporalAccessor, boolean, boolean)}
     *
     * @param checkedTime      the time will be checked
     * @param beginTime        the beginning time
     * @param endTime          the end time
     * @param includeBeginTime is the checked time range include the beginning time
     * @param includeEndTime   is the checked time range include the end time
     * @return is the checked time between the beginning time and the end time
     */
    public static boolean isNotIn(TemporalAccessor checkedTime, TemporalAccessor beginTime, TemporalAccessor endTime, boolean includeBeginTime, boolean includeEndTime) {
        return !isIn(checkedTime, beginTime, endTime, includeBeginTime, includeEndTime);
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
