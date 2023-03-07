package cn.srd.itcp.sugar.tool.core.time;

import cn.srd.itcp.sugar.tool.constant.TimeUnitPool;

import java.time.Duration;

/**
 * 时间单位处理器 - 小时
 *
 * @author wjm
 * @since 2023-02-13 22:09:11
 */
public class TimeUnitHourHandler extends TimeUnitHandler {

    /**
     * private block constructor
     */
    private TimeUnitHourHandler() {
    }

    /**
     * singleton pattern
     */
    protected static final TimeUnitHourHandler INSTANCE = new TimeUnitHourHandler();

    protected TimeUnitHourHandler newInstance() {
        return new TimeUnitHourHandler();
    }

    @Override
    public Duration toMillisecond() {
        return Duration.ofMillis(getTime() * TimeUnitPool.MINUTE_UNIT * TimeUnitPool.SECOND_UNIT * TimeUnitPool.MILLISECOND_UNIT);
    }

    @Override
    public Duration toSecond() {
        return Duration.ofSeconds(getTime() * TimeUnitPool.MINUTE_UNIT * TimeUnitPool.SECOND_UNIT);
    }

    @Override
    public Duration toMinute() {
        return Duration.ofMinutes(getTime() * TimeUnitPool.MINUTE_UNIT);
    }

    @Override
    public Duration toHour() {
        return Duration.ofHours(getTime());
    }

    @Override
    public Duration toDay() {
        return Duration.ofDays(getTime() / TimeUnitPool.HOUR_UNIT);
    }

}
