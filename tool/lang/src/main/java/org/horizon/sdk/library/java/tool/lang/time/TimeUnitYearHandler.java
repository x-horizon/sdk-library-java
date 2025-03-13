package org.horizon.sdk.library.java.tool.lang.time;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.horizon.sdk.library.java.contract.model.throwable.UnsupportedException;

import java.time.Duration;

/**
 * 时间单位处理器 - 年
 *
 * @author wjm
 * @since 2023-02-13 22:09
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
        throw new UnsupportedException();
    }

    @Override
    public Duration toSecond() {
        throw new UnsupportedException();
    }

    @Override
    public Duration toMinute() {
        throw new UnsupportedException();
    }

    @Override
    public Duration toHour() {
        throw new UnsupportedException();
    }

    @Override
    public Duration toDay() {
        throw new UnsupportedException();
    }

}