package org.horizon.library.java.tool.lang.time;

import org.dromara.hutool.core.date.Week;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * the weekday type
 *
 * @author wjm
 * @since 2023-06-28 11:10
 */
@Getter
@AllArgsConstructor
public enum WeekdayTypeEnum {

    /**
     * monday
     */
    MONDAY(1, "周一"),

    /**
     * tuesday
     */
    TUESDAY(2, "周二"),

    /**
     * wednesday
     */
    WEDNESDAY(3, "周三"),

    /**
     * thursday
     */
    THURSDAY(4, "周四"),

    /**
     * friday
     */
    FRIDAY(5, "周五"),

    /**
     * saturday
     */
    SATURDAY(6, "周六"),

    /**
     * sunday
     */
    SUNDAY(7, "周日"),
    ;

    /**
     * the enum code
     */
    private final int code;

    /**
     * the enum description
     */
    private final String description;

    /**
     * get all weekday type
     *
     * @return all weekday type
     */
    public static List<WeekdayTypeEnum> all() {
        return List.of(
                WeekdayTypeEnum.MONDAY,
                WeekdayTypeEnum.TUESDAY,
                WeekdayTypeEnum.WEDNESDAY,
                WeekdayTypeEnum.THURSDAY,
                WeekdayTypeEnum.FRIDAY,
                WeekdayTypeEnum.SATURDAY,
                WeekdayTypeEnum.SUNDAY
        );
    }

    /**
     * convert {@link Week} to {@link WeekdayTypeEnum}
     *
     * @param week see {@link Week}
     * @return see {@link WeekdayTypeEnum}
     */
    public static WeekdayTypeEnum convertByHutoolWeek(Week week) {
        return switch (week) {
            case MONDAY -> WeekdayTypeEnum.MONDAY;
            case TUESDAY -> WeekdayTypeEnum.TUESDAY;
            case WEDNESDAY -> WeekdayTypeEnum.WEDNESDAY;
            case THURSDAY -> WeekdayTypeEnum.THURSDAY;
            case FRIDAY -> WeekdayTypeEnum.FRIDAY;
            case SATURDAY -> WeekdayTypeEnum.SATURDAY;
            case SUNDAY -> WeekdayTypeEnum.SUNDAY;
        };
    }

}