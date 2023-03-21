package cn.srd.itcp.sugar.tool.core.time;

import java.time.Duration;

/**
 * 时间单位处理器 - 微秒
 *
 * @author wjm
 * @since 2023-03-21 10:15:36
 */
public class TimeUnitMicrosecondHandler extends TimeUnitHandler {

    /**
     * private block constructor
     */
    private TimeUnitMicrosecondHandler() {
    }

    /**
     * singleton pattern
     */
    protected static final TimeUnitMicrosecondHandler INSTANCE = new TimeUnitMicrosecondHandler();

    protected TimeUnitMicrosecondHandler newInstance() {
        return new TimeUnitMicrosecondHandler();
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
