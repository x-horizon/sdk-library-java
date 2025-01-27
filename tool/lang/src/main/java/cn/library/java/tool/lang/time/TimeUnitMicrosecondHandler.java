package cn.library.java.tool.lang.time;

import cn.library.java.contract.model.throwable.UnsupportedException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Duration;

/**
 * 时间单位处理器 - 微秒
 *
 * @author wjm
 * @since 2023-03-21 10:15
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUnitMicrosecondHandler extends TimeUnitHandler {

    /**
     * singleton pattern
     */
    protected static final TimeUnitMicrosecondHandler INSTANCE = new TimeUnitMicrosecondHandler();

    protected TimeUnitMicrosecondHandler newInstance() {
        return new TimeUnitMicrosecondHandler();
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