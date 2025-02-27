package org.horizon.sdk.library.java.tool.lang.time;

import org.horizon.sdk.library.java.contract.model.throwable.UnsupportedException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Duration;

/**
 * 时间单位处理器 - 月
 *
 * @author wjm
 * @since 2023-02-13 22:09
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUnitMonthHandler extends TimeUnitHandler {

    /**
     * singleton pattern
     */
    protected static final TimeUnitMonthHandler INSTANCE = new TimeUnitMonthHandler();

    protected TimeUnitMonthHandler newInstance() {
        return new TimeUnitMonthHandler();
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