package org.horizon.sdk.library.java.tool.lang.time;

import io.vavr.control.Try;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.dromara.hutool.core.date.TemporalAccessorUtil;
import org.dromara.hutool.core.date.TimeUtil;
import org.dromara.hutool.core.date.Week;
import org.horizon.sdk.library.java.contract.constant.text.SymbolConstant;
import org.horizon.sdk.library.java.contract.constant.time.TimeConstant;
import org.horizon.sdk.library.java.contract.constant.time.TimePatternConstant;
import org.horizon.sdk.library.java.contract.constant.time.TimeUnitType;
import org.horizon.sdk.library.java.contract.constant.time.TimeZoneType;
import org.horizon.sdk.library.java.contract.model.throwable.LibraryJavaInternalException;
import org.horizon.sdk.library.java.tool.lang.convert.Converts;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.text.Strings;

import java.time.*;
import java.time.chrono.ChronoLocalDate;
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
 * @since 2020-08-11 15:27
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Times {

    /**
     * 时间格式化器 - RFC3339 标准，see {@link TimePatternConstant#DATETIME_RFC3339_EAST_EIGHTH_TIMEZONE}
     */
    private static final DateTimeFormatter RFC3339_FORMATTER = DateTimeFormatter.ofPattern(TimePatternConstant.DATETIME_RFC3339_EAST_EIGHTH_TIMEZONE).withZone(ZoneId.of(TimeZoneType.SHANG_HAI.getValue()));

    /**
     * 持续时间是否为正数，不包括 0
     *
     * @param input 输入参数
     * @return 持续时间是否为 0 或正数
     */
    public static boolean isPositive(Duration input) {
        return Nil.isNotNull(input) && !input.isNegative() && !input.isZero();
    }

    /**
     * 持续时间是否为负数，不包括 0
     *
     * @param input 输入参数
     * @return 持续时间是否为负数，不包括 0
     */
    public static boolean isNegative(Duration input) {
        return Nil.isNotNull(input) && input.isNegative();
    }

    /**
     * 持续时间是否为 0
     *
     * @param input 输入参数
     * @return 持续时间是否为 0
     */
    public static boolean isZero(Duration input) {
        return Nil.isNotNull(input) && input.isZero();
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
     * @see TimePatternConstant#DATETIME_MS0
     */
    public static String getCurrentDateTime(String format) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format == null ? TimePatternConstant.DATETIME_MS0 : format));
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
     * 获取最早的日期，see {@link TimeConstant#EARLIEST_DATE}
     *
     * @return 最早的日期
     */
    public static LocalDate getEarliestDate() {
        return toLocalDate(TimeConstant.EARLIEST_DATE);
    }

    /**
     * 获取最晚的日期，see {@link TimeConstant#LATEST_DATE}
     *
     * @return 最晚的日期
     */
    public static LocalDate getLatestDate() {
        return toLocalDate(TimeConstant.LATEST_DATE);
    }

    /**
     * see {@link TimeUtil#formatNormal(ChronoLocalDate)}
     *
     * @param input the input element
     * @return after convert
     */
    public static String toStringYearMonthDay(ChronoLocalDate input) {
        return TimeUtil.formatNormal(input);
    }

    /**
     * LocalTime =&gt; String，如：14:12
     *
     * @param input 输入时间对象
     * @return 时间字符串
     */
    public static String toStringHourMinute(LocalTime input) {
        return Nil.isNull(input) ? SymbolConstant.EMPTY : input.format(DateTimeFormatter.ofPattern(TimePatternConstant.HOUR_MINUTE));
    }

    /**
     * LocalTime =&gt; String，如：14:12:11
     *
     * @param input 输入时间对象
     * @return 时间字符串
     */
    public static String toStringHourMinuteSecond(LocalTime input) {
        return Nil.isNull(input) ? SymbolConstant.EMPTY : input.format(DateTimeFormatter.ofPattern(TimePatternConstant.HOUR_MINUTE_SECOND));
    }

    /**
     * LocalDateTime =&gt; String，自定义时间格式；
     *
     * @param input  输入时间对象
     * @param format 格式化，see {@link TimePatternConstant}
     * @return 时间字符串
     */
    public static String toStringWithDateTime(LocalDateTime input, String format) {
        return Nil.isNull(input) ? SymbolConstant.EMPTY : DateTimeFormatter.ofPattern(format).format(input);
    }

    /**
     * LocalDateTime =&gt; String，如：2011-12-03 10:15
     *
     * @param input 输入时间对象
     * @return 时间字符串
     */
    public static String toStringWithoutSecond(LocalDateTime input) {
        return Nil.isNull(input) ? SymbolConstant.EMPTY : DateTimeFormatter.ofPattern(TimePatternConstant.DATETIME_WITHOUT_SECOND).format(input);
    }

    /**
     * LocalDateTime =&gt; String，如：2011-12-03 10:15:30
     *
     * @param input 输入时间对象
     * @return 时间字符串
     */
    public static String toStringWithDateTimeMS0(LocalDateTime input) {
        return Nil.isNull(input) ? SymbolConstant.EMPTY : DateTimeFormatter.ofPattern(TimePatternConstant.DATETIME_MS0).format(input);
    }

    /**
     * LocalDateTime =&gt; String，如：2011-12-03 10:15:30.9
     *
     * @param input 输入时间对象
     * @return 时间字符串
     */
    public static String toStringWithDateTimeMS1(LocalDateTime input) {
        return Nil.isNull(input) ? SymbolConstant.EMPTY : DateTimeFormatter.ofPattern(TimePatternConstant.DATETIME_MS1).format(input);
    }

    /**
     * LocalDateTime =&gt; String，如：2011-12-03 10:15:30.97
     *
     * @param input 输入时间对象
     * @return 时间字符串
     */
    public static String toStringWithDateTimeMS2(LocalDateTime input) {
        return Nil.isNull(input) ? SymbolConstant.EMPTY : DateTimeFormatter.ofPattern(TimePatternConstant.DATETIME_MS2).format(input);
    }

    /**
     * LocalDateTime =&gt; String，如：2011-12-03 10:15:30.974
     *
     * @param input 输入时间对象
     * @return 时间字符串
     */
    public static String toStringWithDateTimeMS3(LocalDateTime input) {
        return Nil.isNull(input) ? SymbolConstant.EMPTY : DateTimeFormatter.ofPattern(TimePatternConstant.DATETIME_MS3).format(input);
    }

    /**
     * LocalDateTime =&gt; String，如：2011-12-03 10:15:30.9745
     *
     * @param input 输入时间对象
     * @return 时间字符串
     */
    public static String toStringWithDateTimeMS4(LocalDateTime input) {
        return Nil.isNull(input) ? SymbolConstant.EMPTY : DateTimeFormatter.ofPattern(TimePatternConstant.DATETIME_MS4).format(input);
    }

    /**
     * LocalDateTime =&gt; String，如：2011-12-03 10:15:30.97451
     *
     * @param input 输入时间对象
     * @return 时间字符串
     */
    public static String toStringWithDateTimeMS5(LocalDateTime input) {
        return Nil.isNull(input) ? SymbolConstant.EMPTY : DateTimeFormatter.ofPattern(TimePatternConstant.DATETIME_MS5).format(input);
    }

    /**
     * LocalDateTime =&gt; String，如：2011-12-03 10:15:30.974515
     *
     * @param input 输入时间对象
     * @return 时间字符串
     */
    public static String toStringWithDateTimeMS6(LocalDateTime input) {
        return Nil.isNull(input) ? SymbolConstant.EMPTY : DateTimeFormatter.ofPattern(TimePatternConstant.DATETIME_MS6).format(input);
    }

    /**
     * LocalDateTime =&gt; String，如：2011-12-03T10:15:30.974515（如果纳秒后面为 0，则省略，如：2011-12-03T10:15:30.97451）
     *
     * @param input 输入时间对象
     * @return 时间字符串
     */
    public static String toStringWithISODateTime(LocalDateTime input) {
        return Nil.isNull(input) ? SymbolConstant.EMPTY : DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(input);
    }

    /**
     * LocalDateTime =&gt; String，遵循 RFC3339 标准，如：2006-01-02T15:04:05Z07:00
     *
     * @param input 输入时间对象
     * @return 时间字符串
     */
    public static String toStringWithRFC3339DateTime(LocalDateTime input) {
        return Nil.isNull(input) ? SymbolConstant.EMPTY : RFC3339_FORMATTER.format(input);
    }

    /**
     * LocalDateTime =&gt; Long
     *
     * @param input 输入时间对象
     * @return 时间戳
     */
    public static Long toLong(LocalDateTime input) {
        return Nil.isNull(input) ? null : input.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * LocalDate =&gt; Long
     *
     * @param input 输入时间对象
     * @return 时间戳
     */
    public static Long toLong(LocalDate input) {
        return Nil.isNull(input) ? null : input.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * Long =&gt; LocalDateTime
     *
     * @param input 输入时间戳
     * @return 时间对象
     */
    public static LocalDateTime toLocalDateTime(Long input) {
        return Nil.isNull(input) ? null : LocalDateTime.ofInstant(Instant.ofEpochMilli(input), ZoneId.systemDefault());
    }

    /**
     * Long =&gt; LocalDate
     *
     * @param input 输入时间戳
     * @return 时间对象
     */
    public static LocalDate toLocalDate(Long input) {
        return Nil.isNull(input) ? null : LocalDate.ofInstant(Instant.ofEpochMilli(input), ZoneId.systemDefault());
    }

    /**
     * String =&gt; LocalDate
     *
     * @param input 输入时间字符串
     * @return 时间对象
     */
    public static LocalDate toLocalDate(CharSequence input) {
        return Nil.isNull(input) ? null : LocalDate.parse(input);
    }

    /**
     * see {@link TimeUtil#parseDate(CharSequence, DateTimeFormatter)}
     *
     * @param input     the input element
     * @param formatter the formatter
     * @return after convert
     */
    public static LocalDate toLocalDate(CharSequence input, DateTimeFormatter formatter) {
        return TimeUtil.parseDate(input, formatter);
    }

    /**
     * see {@link TimeUtil#parse(CharSequence, String)}
     *
     * @param input  the input element
     * @param format the format string
     * @return after convert
     */
    public static LocalDateTime toLocalDateTime(CharSequence input, String format) {
        return TimeUtil.parse(input, format);
    }

    /**
     * String =&gt; LocalTime
     *
     * @param input 输入时间字符串
     * @return 时间对象
     */
    public static LocalTime toLocalTime(String input) {
        return Nil.isNull(input) ? null : LocalTime.parse(input);
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
        return TimeUtil.isIn(getCurrentDateTime(), beginTime, endTime, true, true);
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
        return TimeUtil.isIn(getCurrentDateTime(), beginTime, endTime, includeBeginTime, includeEndTime);
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

    public static Date max(Date... dates) {
        return Stream.of(dates).filter(Nil::isNotNull).max(Comparator.naturalOrder()).orElse(null);
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
     * @param timeUnitType 时间单位
     * @return {@link TimeUnitHandler}
     */
    public static TimeUnitHandler wrapper(long time, TimeUnitType timeUnitType) {
        return TimeUnitHandler.TIME_UNIT_POOL_MAPPING_HANDLER_MAP.get(timeUnitType).newInstance().setTime(time);
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
            TimeUnitType timeUnitType = Converts.toEnumByValue(timeUnit, TimeUnitType.class);
            if (Nil.isNotNull(timeUnitType)) {
                long time = Try.of(() -> Long.parseLong(Strings.removeAll(timeFormat, timeUnit)))
                        .onFailure(throwable -> {
                            throw new LibraryJavaInternalException(Strings.format("invalid time by remove [{}] from input [{}]", timeUnit, timeFormat), throwable);
                        })
                        .get();
                return wrapper(time, timeUnitType);
            }
        }
        throw new LibraryJavaInternalException(Strings.format("invalid time format by input [{}]", timeFormat));
    }

    public static boolean isValidTimeFormat(String timeFormat) {
        try {
            wrapper(timeFormat);
            return true;
        } catch (Exception ignore) {
            return false;
        }
    }

    public static boolean isInvalidTimeFormat(String timeFormat) {
        return !isValidTimeFormat(timeFormat);
    }

}