package cn.srd.itcp.sugar.tool.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 时间单位
 *
 * @author wjm
 * @since 2023-02-11 17:54:19
 */
@Getter
@AllArgsConstructor
public enum TimeUnitPool {

    /**
     * millisecond
     */
    MILLISECOND("ms", "MS", "millisecond", "Millisecond", "MILLISECOND", "毫秒", "毫秒钟"),

    /**
     * second
     */
    SECOND("s", "S", "second", "Second", "SECOND", "秒", "秒钟"),

    /**
     * minute
     */
    MINUTE("m", "M", "minute", "Minute", "MINUTE", "分", "分钟"),

    /**
     * hour
     */
    HOUR("h", "H", "hour", "Hour", "HOUR", "时", "小时"),

    /**
     * day
     */
    DAY("d", "D", "day", "Day", "DAY", "天", "天数"),

    /**
     * month
     */
    MONTH("m", "M", "month", "Month", "MONTH", "月", "月数"),

    /**
     * year
     */
    YEAR("y", "Y", "year", "Year", "YEAR", "年", "年数"),

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

    private final String name1;
    private final String name2;
    private final String name3;
    private final String name4;
    private final String name5;
    private final String name6;
    private final String name7;

}
