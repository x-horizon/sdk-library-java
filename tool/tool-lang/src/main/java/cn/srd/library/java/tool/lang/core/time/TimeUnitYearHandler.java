package cn.srd.library.java.tool.lang.core.time;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Duration;

/**
 * 时间单位处理器 - 年
 *
 * @author wjm
 * @since 2023-02-13 22:09:11
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUnitYearHandler extends TimeUnitHandler {

    /**
     * singleton pattern
     */
    protected static final TimeUnitYearHandler INSTANCE = new TimeUnitYearHandler();

    protected TimeUnitYearHandler newInstance() {
        return new TimeUnitYearHandler();
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
