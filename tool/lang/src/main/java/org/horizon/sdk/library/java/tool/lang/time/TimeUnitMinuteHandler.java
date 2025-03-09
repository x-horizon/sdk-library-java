package org.horizon.sdk.library.java.tool.lang.time;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.horizon.sdk.library.java.contract.constant.time.TimeUnitConstant;

import java.time.Duration;

/**
 * 时间单位处理器 - 分钟
 *
 * @author wjm
 * @since 2023-02-13 22:09
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUnitMinuteHandler extends TimeUnitHandler {

    /**
     * singleton pattern
     */
    protected static final TimeUnitMinuteHandler INSTANCE = new TimeUnitMinuteHandler();

    protected TimeUnitMinuteHandler newInstance() {
        return new TimeUnitMinuteHandler();
    }

    @Override
    public Duration toMillisecond() {
        return Duration.ofMillis(getTime() * TimeUnitConstant.SECOND * TimeUnitConstant.MILLISECOND);
    }

    @Override
    public Duration toSecond() {
        return Duration.ofSeconds(getTime() * TimeUnitConstant.SECOND);
    }

    @Override
    public Duration toMinute() {
        return Duration.ofMinutes(getTime());
    }

    @Override
    public Duration toHour() {
        return Duration.ofHours(getTime() / TimeUnitConstant.MINUTE);
    }

    @Override
    public Duration toDay() {
        return Duration.ofDays(getTime() / TimeUnitConstant.MINUTE / TimeUnitConstant.HOUR);
    }

}