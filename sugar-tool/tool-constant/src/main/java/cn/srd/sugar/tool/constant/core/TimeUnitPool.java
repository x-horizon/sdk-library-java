package cn.srd.sugar.tool.constant.core;

import lombok.Getter;

/**
 * 时间单位
 *
 * @author wjm
 * @since 2023-02-11 17:54:19
 */
@Getter
public enum TimeUnitPool {

    /**
     * nanosecond
     */
    NANOSECOND("ns", "NS", "nanosecond", "Nanosecond", "NANOSECOND", "纳秒"),

    /**
     * microsecond
     */
    MICROSECOND("microsecond", "Microsecond", "MICROSECOND", "微秒"),

    /**
     * millisecond
     */
    MILLISECOND("ms", "MS", "millisecond", "Millisecond", "MILLISECOND", "毫秒"),

    /**
     * second
     */
    SECOND("s", "S", "second", "Second", "SECOND", "秒"),

    /**
     * minute
     */
    MINUTE("m", "M", "minute", "Minute", "MINUTE", "分"),

    /**
     * hour
     */
    HOUR("h", "H", "hour", "Hour", "HOUR", "时", "小时"),

    /**
     * day
     */
    DAY("d", "D", "day", "Day", "DAY", "天"),

    /**
     * month
     */
    MONTH("month", "Month", "MONTH", "月"),

    /**
     * year
     */
    YEAR("y", "Y", "year", "Year", "YEAR", "年"),

    ;

    /**
     * 毫秒单位
     */
    public static final int MILLISECOND_UNIT = 1000;

    /**
     * 秒单位
     */
    public static final int SECOND_UNIT = 60;

    /**
     * 分钟单位
     */
    public static final short MINUTE_UNIT = 60;

    /**
     * 小时单位
     */
    public static final short HOUR_UNIT = 24;

    TimeUnitPool(String... names) {
        this.names = names;
    }

    private final String[] names;

}
