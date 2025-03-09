package org.horizon.sdk.library.java.tool.lang.time;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.horizon.sdk.library.java.contract.constant.time.TimeUnitConstant;

import java.time.Duration;

/**
 * 时间单位处理器 - 小时
 *
 * @author wjm
 * @since 2023-02-13 22:09
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUnitHourHandler extends TimeUnitHandler {

    /**
     * singleton pattern
     */
    protected static final TimeUnitHourHandler INSTANCE = new TimeUnitHourHandler();

    protected TimeUnitHourHandler newInstance() {
        return new TimeUnitHourHandler();
    }

    @Override
    public Duration toMillisecond() {
        return Duration.ofMillis(getTime() * TimeUnitConstant.MINUTE * TimeUnitConstant.SECOND * TimeUnitConstant.MILLISECOND);
    }

    @Override
    public Duration toSecond() {
        return Duration.ofSeconds(getTime() * TimeUnitConstant.MINUTE * TimeUnitConstant.SECOND);
    }

    @Override
    public Duration toMinute() {
        return Duration.ofMinutes(getTime() * TimeUnitConstant.MINUTE);
    }

    @Override
    public Duration toHour() {
        return Duration.ofHours(getTime());
    }

    @Override
    public Duration toDay() {
        return Duration.ofDays(getTime() / TimeUnitConstant.HOUR);
    }

}