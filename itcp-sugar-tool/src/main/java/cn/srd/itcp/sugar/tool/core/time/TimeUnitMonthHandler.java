package cn.srd.itcp.sugar.tool.core.time;

import java.time.Duration;

/**
 * 时间单位处理器 - 月
 *
 * @author wjm
 * @since 2023-02-13 22:09:11
 */
public class TimeUnitMonthHandler extends TimeUnitHandler {

    /**
     * private block constructor
     */
    private TimeUnitMonthHandler() {
    }

    /**
     * singleton pattern
     */
    protected static final TimeUnitMonthHandler INSTANCE = new TimeUnitMonthHandler();

    protected TimeUnitMonthHandler newInstance() {
        return new TimeUnitMonthHandler();
    }

    @Override
    public Duration toMillisecond() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Duration toSecond() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Duration toMinute() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Duration toHour() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Duration toDay() {
        throw new UnsupportedOperationException();
    }

}
