package org.horizon.sdk.library.java.tool.lang.time;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.horizon.sdk.library.java.contract.constant.time.TimeUnitConstant;

import java.time.Duration;

/**
 * 时间单位处理器 - 毫秒
 *
 * @author wjm
 * @since 2023-02-13 22:09
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUnitMillisecondHandler extends TimeUnitHandler {

    /**
     * singleton pattern
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
        return Duration.ofDays(getTime() / TimeUnitConstant.MILLISECOND);
    }

    @Override
    public Duration toMinute() {
        return Duration.ofMinutes(getTime() / TimeUnitConstant.MILLISECOND / TimeUnitConstant.SECOND);
    }

    @Override
    public Duration toHour() {
        return Duration.ofDays(getTime() / TimeUnitConstant.MILLISECOND / TimeUnitConstant.SECOND / TimeUnitConstant.MINUTE);
    }

    @Override
    public Duration toDay() {
        return Duration.ofDays(getTime() / TimeUnitConstant.MILLISECOND / TimeUnitConstant.SECOND / TimeUnitConstant.MINUTE / TimeUnitConstant.HOUR);
    }

}