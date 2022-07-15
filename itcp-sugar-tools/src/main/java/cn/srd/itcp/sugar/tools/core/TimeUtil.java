package cn.srd.itcp.sugar.tools.core;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
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
public class TimeUtil extends DateUtil {

    private TimeUtil() {
    }

    // ************* LocalDate、LocalDateTime、LocalTime、String、Date 互转 *************

    /**
     * LocalDateTime => Date<br>
     * LocalDateTime包含日期与时间
     *
     * @param localDateTime
     * @return
     */
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDate => Date<br>
     * LocalDate只包含日期，默认时间为 00:00:00
     *
     * @param localDate
     * @return
     */
    public static Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalTime => Date<br>
     * LocalDate只包含时间，默认日期为 1970/1/1
     *
     * @param localTime
     * @return
     */
    public static Date toDate(LocalTime localTime) {
        return Date.from(LocalDateTime.parse(localTime.toString(), new DateTimeFormatterBuilder().append(DateTimeFormatter.ISO_LOCAL_TIME).parseDefaulting(ChronoField.EPOCH_DAY, 0).toFormatter()).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * String => Date，支持格式：yyyy-MM-dd HH:mm:ss、yyyy-MM-dd、HH:mm:ss
     *
     * @param dateString
     * @return
     */
    @Nullable
    public static Date toDate(String dateString) {
        if (Objects.isEmpty(dateString)) {
            return null;
        }

        DateTimeFormatter dateTimeFormatter;
        try {
            dateTimeFormatter = DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN);
            return toDate(LocalDateTime.parse(dateString, dateTimeFormatter));
        } catch (Exception ignored) {
        }

        try {
            dateTimeFormatter = DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN);
            return toDate(LocalDate.parse(dateString, dateTimeFormatter));
        } catch (Exception ignored) {
        }

        try {
            dateTimeFormatter = DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN);
            return toDate(LocalTime.parse(dateString, dateTimeFormatter));
        } catch (Exception ignored) {
        }

        return null;
    }

    /**
     * 按照 format 转换 Date<br>
     * 支持格式：{@link #getCurrentTime(String)}
     *
     * @param date
     * @param format
     * @return
     */
    @Nullable
    public static Date toDate(Date date, @Nullable String format) {
        format = format == null ? DatePattern.NORM_DATETIME_PATTERN : format;

        if (Objects.equals(format, DatePattern.NORM_DATETIME_PATTERN)) {
            return toDate(toLocalDateTime(date));
        }

        if (Objects.equals(format, DatePattern.NORM_DATE_PATTERN)) {
            return toDate(toLocalDate(date));
        }

        if (Objects.equals(format, DatePattern.NORM_TIME_PATTERN)) {
            return toDate(toLocalTime(date));
        }

        return null;
    }

    /**
     * 按照 format 转换 Date 为 String<br>
     * 支持格式：{@link #getCurrentTimeString(String)}
     *
     * @param date
     * @param format
     * @return
     */
    public static String toString(Date date, @Nullable String format) {
        return toLocalDateTime(date).format(DateTimeFormatter.ofPattern(format == null ? DatePattern.NORM_DATETIME_PATTERN : format));
    }

    /**
     * Date => LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * Date => LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate toLocalDate(Date date) {
        return toLocalDateTime(date).toLocalDate();
    }

    /**
     * Date => LocalTime
     *
     * @param date
     * @return
     */
    public static LocalTime toLocalTime(Date date) {
        return toLocalDateTime(date).toLocalTime();
    }

    /**
     * 秒转为00:00:00
     *
     * @param second
     * @return
     */
    public static String getTimeStrBySecond(int second) {
        int hourSecond = 60 * 60;
        int minuteSecond = 60;
        if (second <= 0) {
            return "00:00:00";
        }

        int hours = second / hourSecond;
        if (hours > 0) {
            second -= hours * hourSecond;
        }

        int minutes = second / minuteSecond;
        if (minutes > 0) {
            second -= minutes * minuteSecond;
        }

        return (hours >= 10 ? (hours + "") : ("0" + hours) + ":" + (minutes >= 10 ? (minutes + "") : ("0" + minutes)) + ":" + (second >= 10 ? (second + "") : ("0" + second)));
    }

    // ************* 获取日期 *************

    /**
     * 获取当前时间 Date
     * <pre>
     * 支持格式：
     * yyyy-MM-dd HH:mm:ss
     * yyyy-MM-dd
     * HH:mm:ss
     *
     * 默认格式：yyyy-MM-dd HH:mm:ss
     *
     * 示例：
     * 格式为：yyyy-MM-dd HH:mm:ss => 获取到的Date：Sat Nov 21 18:43:36 CST 2020
     * 格式为：yyyy-MM-dd          => 获取到的Date：Sat Nov 21 00:00:00 CST 2020
     * 格式为：HH:mm:ss            => 获取到的Date：Thu Jan 01 18:43:36 CST 1970，默认 1970/1/1
     *
     * 不支持的日期格式返回null
     *
     * @param format
     * @return
     */
    @Nullable
    public static Date getCurrentTime(@Nullable String format) {
        format = format == null ? DatePattern.NORM_DATETIME_PATTERN : format;

        if (Objects.equals(format, DatePattern.NORM_DATETIME_PATTERN)) {
            return toDate(LocalDateTime.now());
        }

        if (Objects.equals(format, DatePattern.NORM_DATE_PATTERN)) {
            return toDate(LocalDate.now());
        }

        if (Objects.equals(format, DatePattern.NORM_TIME_PATTERN)) {
            return toDate(LocalTime.now());
        }

        return null;
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
     * 根据日期字符串获取当前月第几周<br>
     * 日期字符串需要为 yyyy-MM-dd 或 长度大于 yyyy-MM-dd 的格式，否则返回 null
     * <pre>
     * 规则：表示将星期一视为第一天，计算第一周至少需要4天；
     *
     * WeekFields.of(DayOfWeek.MONDAY, 1)：表示将星期一视为第一天，计算第一周至少需要1天；
     * WeekFields.ISO：ISO-8601标准的自然周，等价于 WeekFields.of(DayOfWeek.MONDAY, 4)，表示将星期一视为第一天，计算第一周至少需要4天；
     * 时间中若最早的时期小于最小天数，则称为第0周，如果它具有至少最小的天数则称为第1周。
     * 这里使用WeekFields.ISO
     *
     * @param dateString
     * @return
     */
    @Nullable
    public static Integer getWeekOfMonth(String dateString) {
        return getWeekOfMonth(toDate(transformedOnlyDate(dateString)));
    }

    /**
     * {@link #getWeekOfMonth(String)}
     *
     * @param date
     * @return
     */
    @Nullable
    public static Integer getWeekOfMonth(Date date) {
        return getWeekOfMonth(toLocalDate(date));
    }

    /**
     * {@link #getWeekOfMonth(String)}
     *
     * @param localDate
     * @return
     */
    @Nullable
    public static Integer getWeekOfMonth(LocalDate localDate) {
        try {
            int currentWeek = localDate.get(WeekFields.ISO.weekOfMonth());
            // 时间中若最早的时期小于最小天数，为第0周，此时返回第一周
            return currentWeek == 0 ? 1 : currentWeek;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据日期字符串获取当前周第几天<br>
     * 日期字符串需要为 yyyy-MM-dd 或 长度大于 yyyy-MM-dd 的格式，否则返回 null
     *
     * @param dateString
     * @return
     */
    @Nullable
    public static Integer getDayOfWeek(String dateString) {
        return getDayOfWeek(toDate(transformedOnlyDate(dateString)));
    }

    /**
     * {@link #getDayOfWeek(String)}
     *
     * @param date
     * @return
     */
    @Nullable
    public static Integer getDayOfWeek(Date date) {
        return getDayOfWeek(toLocalDate(date));
    }

    /**
     * {@link #getDayOfWeek(String)}
     *
     * @param localDate
     * @return
     */
    @Nullable
    public static Integer getDayOfWeek(LocalDate localDate) {
        try {
            return localDate.getDayOfWeek().getValue();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据日期字符串获取当前周第几天的英文表示，如：MONDAY<br>
     * 日期字符串需要为 yyyy-MM-dd 或 长度大于 yyyy-MM-dd 的格式，否则返回 null
     *
     * @param dateString
     * @return
     */
    @Nullable
    public static String getDayEnglishOfWeek(String dateString) {
        return getDayEnglishOfWeek(toDate(transformedOnlyDate(dateString)));
    }

    /**
     * {@link #getDayEnglishOfWeek(String)}
     *
     * @param date
     * @return
     */
    @Nullable
    public static String getDayEnglishOfWeek(Date date) {
        return getDayEnglishOfWeek(toLocalDate(date));
    }

    /**
     * {@link #getDayEnglishOfWeek(String)}
     *
     * @param localDate
     * @return
     */
    @Nullable
    public static String getDayEnglishOfWeek(LocalDate localDate) {
        try {
            return localDate.getDayOfWeek().toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据日期字符串获取当前月第几天<br>
     * 日期字符串需要为 yyyy-MM-dd 或 长度大于 yyyy-MM-dd 的格式，否则返回 null
     *
     * @param dateString
     * @return
     */
    @Nullable
    public static Integer getDayOfMonth(String dateString) {
        return getDayOfMonth(toDate(transformedOnlyDate(dateString)));
    }

    /**
     * {@link #getDayOfMonth(String)}
     *
     * @param date
     * @return
     */
    @Nullable
    public static Integer getDayOfMonth(Date date) {
        return getDayOfMonth(toLocalDate(date));
    }

    /**
     * {@link #getDayOfMonth(String)}
     *
     * @param localDate
     * @return
     */
    @Nullable
    public static Integer getDayOfMonth(LocalDate localDate) {
        try {
            return localDate.getDayOfMonth();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据日期字符串获取当前年第几天<br>
     * 日期字符串需要为 yyyy-MM-dd 或 长度大于 yyyy-MM-dd 的格式，否则返回 null
     *
     * @param dateString
     * @return
     */
    @Nullable
    public static Integer getDayOfYear(String dateString) {
        return getDayOfYear(toDate(transformedOnlyDate(dateString)));
    }

    /**
     * {@link #getDayOfYear(String)}
     *
     * @param date
     * @return
     */
    @Nullable
    public static Integer getDayOfYear(Date date) {
        return getDayOfYear(toLocalDate(date));
    }

    /**
     * {@link #getDayOfYear(String)}
     *
     * @param localDate
     * @return
     */
    @Nullable
    public static Integer getDayOfYear(LocalDate localDate) {
        try {
            return localDate.getDayOfYear();
        } catch (Exception e) {
            return null;
        }
    }

    // ************* 日期计算 *************

    /**
     * 日期 + 秒
     *
     * @param date
     * @param seconds
     * @return
     */
    public static Date plusSeconds(Date date, Long seconds) {
        return toDate(toLocalDateTime(date).plusSeconds(seconds));
    }

    /**
     * 日期 + 分钟
     *
     * @param date
     * @param minutes
     * @return
     */
    public static Date plusMinutes(Date date, Long minutes) {
        return toDate(toLocalDateTime(date).plusMinutes(minutes));
    }

    /**
     * 日期 + 小时
     *
     * @param date
     * @param hours
     * @return
     */
    public static Date plusHours(Date date, Long hours) {
        return toDate(toLocalDateTime(date).plusHours(hours));
    }

    /**
     * 日期 + 日
     *
     * @param date
     * @param days
     * @return
     */
    public static Date plusDays(Date date, Long days) {
        return toDate(toLocalDateTime(date).plusDays(days));
    }

    /**
     * 日期 + 月
     *
     * @param date
     * @param months
     * @return
     */
    public static Date plusMonths(Date date, Long months) {
        return toDate(toLocalDateTime(date).plusMonths(months));
    }

    /**
     * 日期 + 年
     *
     * @param date
     * @param years
     * @return
     */
    public static Date plusYears(Date date, Long years) {
        return toDate(toLocalDateTime(date).plusYears(years));
    }

    /**
     * 日期 - 秒
     *
     * @param date
     * @param seconds
     * @return
     */
    public static Date minusSeconds(Date date, Long seconds) {
        return toDate(toLocalDateTime(date).minusSeconds(seconds));
    }

    /**
     * 日期 - 分钟
     *
     * @param date
     * @param minutes
     * @return
     */
    public static Date minusMinutes(Date date, Long minutes) {
        return toDate(toLocalDateTime(date).minusMinutes(minutes));
    }

    /**
     * 日期 - 小时
     *
     * @param date
     * @param hours
     * @return
     */
    public static Date minusHours(Date date, Long hours) {
        return toDate(toLocalDateTime(date).minusHours(hours));
    }

    /**
     * 日期 - 日
     *
     * @param date
     * @param days
     * @return
     */
    public static Date minusDays(Date date, Long days) {
        return toDate(toLocalDateTime(date).minusDays(days));
    }

    /**
     * 日期 - 月
     *
     * @param date
     * @param months
     * @return
     */
    public static Date minusMonths(Date date, Long months) {
        return toDate(toLocalDateTime(date).minusMonths(months));
    }

    /**
     * 日期 - 年
     *
     * @param date
     * @param years
     * @return
     */
    public static Date minusYears(Date date, Long years) {
        return toDate(toLocalDateTime(date).minusYears(years));
    }

    // ************* 日期校验 *************

    /**
     * 校验日期，如果日期字符串不为 yyyy-MM-dd HH:mm:ss、yyyy-MM-dd、HH:mm:ss 其中的一种，返回 false，否则返回 true
     *
     * @param dateString
     * @return
     */
    public static boolean validDate(String dateString) {
        if (Objects.isEmpty(dateString)) {
            return false;
        }

        DateTimeFormatter dateTimeFormatter;
        try {
            dateTimeFormatter = DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN);
            LocalDateTime.parse(dateString, dateTimeFormatter);
        } catch (Exception isNotLocalDateTime) {
            try {
                dateTimeFormatter = DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN);
                LocalDate.parse(dateString, dateTimeFormatter);
            } catch (Exception isNotLocalDate) {
                try {
                    dateTimeFormatter = DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN);
                    LocalTime.parse(dateString, dateTimeFormatter);
                } catch (Exception isNotLocalTime) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 如果有一个日期不符合{@link #validDate(String)}，返回false
     *
     * @param dateStrings
     * @return
     */
    public static boolean validDate(String... dateStrings) {
        if (Objects.isEmpty(dateStrings)) {
            return false;
        }
        for (String dateString : dateStrings) {
            if (!validDate(dateString)) {
                return false;
            }
        }
        return true;
    }

    // ************* 比较日期大小 *************

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

    // ************* 其他 *************

    /**
     * <pre>
     * 截取日期字符串为 yyyy-MM-dd
     * 日期字符串需要为 yyyy-MM-dd 或 长度大于 yyyy-MM-dd 的格式，否则返回 null
     * </pre>
     *
     * @param dateString
     * @return
     */
    @Nullable
    public static String transformedOnlyDate(String dateString) {
        try {
            return dateString.substring(0, DatePattern.NORM_DATE_PATTERN.length());
        } catch (Exception e) {
            return null;
        }
    }

}
