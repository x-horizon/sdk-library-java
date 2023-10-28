package cn.srd.library.java.tool.lang.time;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Duration;

/**
 * 时间单位处理器 - 纳秒
 *
 * @author wjm
 * @since 2023-03-21 10:15:36
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUnitNanosecondHandler extends TimeUnitHandler {

    /**
     * singleton pattern
     */
    protected static final TimeUnitNanosecondHandler INSTANCE = new TimeUnitNanosecondHandler();

    protected TimeUnitNanosecondHandler newInstance() {
        return new TimeUnitNanosecondHandler();
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
