package cn.srd.library.tool.lang.core.time;

import cn.srd.library.tool.constant.core.TimeUnitPool;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Duration;

/**
 * 时间单位处理器 - 日
 *
 * @author wjm
 * @since 2023-02-13 22:09:11
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
        return Duration.ofMillis(getTime() * TimeUnitPool.HOUR_UNIT * TimeUnitPool.MINUTE_UNIT * TimeUnitPool.SECOND_UNIT * TimeUnitPool.MILLISECOND_UNIT);
    }

    @Override
    public Duration toSecond() {
        return Duration.ofSeconds(getTime() * TimeUnitPool.HOUR_UNIT * TimeUnitPool.MINUTE_UNIT * TimeUnitPool.SECOND_UNIT);
    }

    @Override
    public Duration toMinute() {
        return Duration.ofMinutes(getTime() * TimeUnitPool.HOUR_UNIT * TimeUnitPool.MINUTE_UNIT);
    }

    @Override
    public Duration toHour() {
        return Duration.ofHours(getTime() * TimeUnitPool.HOUR_UNIT);
    }

    @Override
    public Duration toDay() {
        return Duration.ofDays(getTime());
    }

}
