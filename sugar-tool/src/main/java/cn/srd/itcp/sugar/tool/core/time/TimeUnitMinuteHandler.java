package cn.srd.itcp.sugar.tool.core.time;

import cn.srd.itcp.sugar.tool.constant.TimeUnitPool;

import java.time.Duration;

/**
 * 时间单位处理器 - 分钟
 *
 * @author wjm
 * @since 2023-02-13 22:09:11
 */
public class TimeUnitMinuteHandler extends TimeUnitHandler {

    /**
     * private block constructor
     */
    private TimeUnitMinuteHandler() {
    }

    /**
     * singleton pattern
     */
    protected static final TimeUnitMinuteHandler INSTANCE = new TimeUnitMinuteHandler();

    protected TimeUnitMinuteHandler newInstance() {
        return new TimeUnitMinuteHandler();
    }

    @Override
    public Duration toMillisecond() {
        return Duration.ofMillis(getTime() * TimeUnitPool.SECOND_UNIT * TimeUnitPool.MILLISECOND_UNIT);
    }

    @Override
    public Duration toSecond() {
        return Duration.ofSeconds(getTime() * TimeUnitPool.SECOND_UNIT);
    }

    @Override
    public Duration toMinute() {
        return Duration.ofMinutes(getTime());
    }

    @Override
    public Duration toHour() {
        return Duration.ofHours(getTime() / TimeUnitPool.MINUTE_UNIT);
    }

    @Override
    public Duration toDay() {
        return Duration.ofDays(getTime() / TimeUnitPool.MINUTE_UNIT / TimeUnitPool.HOUR_UNIT);
    }

}
