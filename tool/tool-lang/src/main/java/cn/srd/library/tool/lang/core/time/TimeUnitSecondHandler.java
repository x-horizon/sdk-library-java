package cn.srd.library.tool.lang.core.time;

import cn.srd.library.tool.constant.core.TimeUnitPool;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Duration;

/**
 * 时间单位处理器 - 秒
 *
 * @author wjm
 * @since 2023-02-13 22:09:11
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUnitSecondHandler extends TimeUnitHandler {

    /**
     * singleton pattern
     */
    protected static final TimeUnitSecondHandler INSTANCE = new TimeUnitSecondHandler();

    protected TimeUnitSecondHandler newInstance() {
        return new TimeUnitSecondHandler();
    }

    @Override
    public Duration toMillisecond() {
        return Duration.ofMillis(getTime() * TimeUnitPool.MILLISECOND_UNIT);
    }

    @Override
    public Duration toSecond() {
        return Duration.ofSeconds(getTime());
    }

    @Override
    public Duration toMinute() {
        return Duration.ofMinutes(getTime() / TimeUnitPool.SECOND_UNIT);
    }

    @Override
    public Duration toHour() {
        return Duration.ofHours(getTime() / TimeUnitPool.SECOND_UNIT / TimeUnitPool.MINUTE_UNIT);
    }

    @Override
    public Duration toDay() {
        return Duration.ofDays(getTime() / TimeUnitPool.SECOND_UNIT / TimeUnitPool.MINUTE_UNIT / TimeUnitPool.HOUR_UNIT);
    }

}
