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
    MILLISECOND("ms", "MS", "millisecond", "Millisecond", "MILLISECOND", "毫秒"),

    /**
     * second
     */
    SECOND("s", "S", "second", "Second", "SECOND", "秒"),

    /**
     * minute
     */
    MINUTE("m", "M", "minute", "Minute", "MINUTE", "分钟"),

    /**
     * hour
     */
    HOUR("h", "H", "hour", "Hour", "HOUR", "小时"),

    /**
     * day
     */
    DAY("d", "D", "day", "Day", "DAY", "天"),

    /**
     * month
     */
    MONTH("m", "M", "month", "Month", "MONTH", "月"),

    /**
     * year
     */
    YEAR("y", "Y", "year", "Year", "YEAR", "年"),

    ;

    private final String lowerCaseShortenedName;
    private final String upperCaseShortenedName;
    private final String lowerCaseFullName;
    private final String upperCaseFirstFullName;
    private final String upperCaseFullName;
    private final String chinese;

}
