package cn.srd.sugar.tool.lang.core.time;

import cn.hutool.core.date.Week;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * the weekday type
 *
 * @author wjm
 * @since 2023-06-28 11:10:00
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
            case Week.MONDAY -> WeekdayTypeEnum.MONDAY;
            case Week.TUESDAY -> WeekdayTypeEnum.TUESDAY;
            case Week.WEDNESDAY -> WeekdayTypeEnum.WEDNESDAY;
            case Week.THURSDAY -> WeekdayTypeEnum.THURSDAY;
            case Week.FRIDAY -> WeekdayTypeEnum.FRIDAY;
            case Week.SATURDAY -> WeekdayTypeEnum.SATURDAY;
            case Week.SUNDAY -> WeekdayTypeEnum.SUNDAY;
        };
    }

}
