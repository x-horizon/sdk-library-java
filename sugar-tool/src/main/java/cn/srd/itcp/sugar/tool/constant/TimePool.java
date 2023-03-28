package cn.srd.itcp.sugar.tool.constant;

import cn.hutool.core.date.DatePattern;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 时间相关常量
 *
 * @author wjm
 * @since 2022-11-14 21:16:51
 */
public class TimePool extends DatePattern {

    /**
     * 上海时区 格式1
     */
    public static final String SHANG_HAI_TIMEZONE_PATTERN_1 = "Asia/Shanghai";

    /**
     * 东八时区 格式1
     */
    public static final String EAST_EIGHTH_TIMEZONE_PATTERN_1 = "+8";

    /**
     * 东八时区 格式2
     */
    public static final String EAST_EIGHTH_TIMEZONE_PATTERN_2 = "+08:00";

    /**
     * 时间格式 yyyy-MM-dd HH:mm:ss
     */
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间格式 yyyy-MM-dd HH:mm:ss.S
     */
    public static final String DATETIME_MS1_PATTERN = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 时间格式 yyyy-MM-dd HH:mm:ss.SS
     */
    public static final String DATETIME_MS2_PATTERN = "yyyy-MM-dd HH:mm:ss.SS";

    /**
     * 时间格式 yyyy-MM-dd HH:mm:ss.SSS
     */
    public static final String DATETIME_MS3_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 时间格式 yyyy-MM-dd HH:mm:ss.SSSS
     */
    public static final String DATETIME_MS4_PATTERN = "yyyy-MM-dd HH:mm:ss.SSSS";

    /**
     * 时间格式 yyyy-MM-dd HH:mm:ss.SSSSS
     */
    public static final String DATETIME_MS5_PATTERN = "yyyy-MM-dd HH:mm:ss.SSSSS";

    /**
     * 时间格式 yyyy-MM-dd HH:mm:ss.SSSSSS
     */
    public static final String DATETIME_MS6_PATTERN = "yyyy-MM-dd HH:mm:ss.SSSSSS";

    /**
     * 时间格式 RFC3339 标准 yyyy-MM-dd'T'HH:mm:ss+08:00（东八时区）
     */
    public static final String DATETIME_RFC3339_CHINA_TIMEZONE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss" + EAST_EIGHTH_TIMEZONE_PATTERN_2;

    /**
     * 时间格式化器 - RFC3339 标准，see {@link #DATETIME_RFC3339_CHINA_TIMEZONE_PATTERN}
     */
    public static final DateTimeFormatter DATETIME_FORMATTER_RFC3339 = DateTimeFormatter.ofPattern(DATETIME_RFC3339_CHINA_TIMEZONE_PATTERN).withZone(ZoneId.of(SHANG_HAI_TIMEZONE_PATTERN_1));

}