package org.horizon.sdk.library.java.tool.lang.time;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.horizon.sdk.library.java.contract.constant.time.TimeUnitConstant;

import java.time.Duration;

/**
 * 时间单位处理器 - 日
 *
 * @author wjm
 * @since 2023-02-13 22:09
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUnitDayHandler extends TimeUnitHandler {

    /**
     * singleton pattern
     */
    protected static final TimeUnitDayHandler INSTANCE = new TimeUnitDayHandler();

    protected TimeUnitDayHandler newInstance() {
        return new TimeUnitDayHandler();
    }

    @Override
    public Duration toMillisecond() {
        return Duration.ofMillis(getTime() * TimeUnitConstant.HOUR * TimeUnitConstant.MINUTE * TimeUnitConstant.SECOND * TimeUnitConstant.MILLISECOND);
    }

    @Override
    public Duration toSecond() {
        return Duration.ofSeconds(getTime() * TimeUnitConstant.HOUR * TimeUnitConstant.MINUTE * TimeUnitConstant.SECOND);
    }

    @Override
    public Duration toMinute() {
        return Duration.ofMinutes(getTime() * TimeUnitConstant.HOUR * TimeUnitConstant.MINUTE);
    }

    @Override
    public Duration toHour() {
        return Duration.ofHours(getTime() * TimeUnitConstant.HOUR);
    }

    @Override
    public Duration toDay() {
        return Duration.ofDays(getTime());
    }

}