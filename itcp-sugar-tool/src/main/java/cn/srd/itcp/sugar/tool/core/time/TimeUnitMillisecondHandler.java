package cn.srd.itcp.sugar.tool.core.time;

import cn.srd.itcp.sugar.tool.constant.TimeUnitPool;

import java.time.Duration;

/**
 * 时间单位处理器 - 毫秒
 *
 * @author wjm
 * @since 2023-02-13 22:09:11
 */
public class TimeUnitMillisecondHandler extends TimeUnitHandler {

    /**
     * private block constructor
     */
    private TimeUnitMillisecondHandler() {
    }

    /**
     * signal ton pattern
     */
    protected static final TimeUnitMillisecondHandler INSTANCE = new TimeUnitMillisecondHandler();

    protected TimeUnitMillisecondHandler newInstance() {
        return new TimeUnitMillisecondHandler();
    }

    @Override
    public Duration toMillisecond() {
        return Duration.ofMillis(getTime());
    }

    @Override
    public Duration toSecond() {
        return Duration.ofDays(getTime() / TimeUnitPool.MILLISECOND_UNIT);
    }

    @Override
    public Duration toMinute() {
        return Duration.ofMinutes(getTime() / TimeUnitPool.MILLISECOND_UNIT / TimeUnitPool.SECOND_UNIT);
    }

    @Override
    public Duration toHour() {
        return Duration.ofDays(getTime() / TimeUnitPool.MILLISECOND_UNIT / TimeUnitPool.SECOND_UNIT / TimeUnitPool.MINUTE_UNIT);
    }

    @Override
    public Duration toDay() {
        return Duration.ofDays(getTime() / TimeUnitPool.MILLISECOND_UNIT / TimeUnitPool.SECOND_UNIT / TimeUnitPool.MINUTE_UNIT / TimeUnitPool.HOUR_UNIT);
    }

}
