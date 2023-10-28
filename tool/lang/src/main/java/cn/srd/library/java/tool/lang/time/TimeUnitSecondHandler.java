package cn.srd.library.java.tool.lang.time;

import cn.srd.library.java.contract.constant.time.TimeUnitConstant;
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
        return Duration.ofMillis(getTime() * TimeUnitConstant.MILLISECOND);
    }

    @Override
    public Duration toSecond() {
        return Duration.ofSeconds(getTime());
    }

    @Override
    public Duration toMinute() {
        return Duration.ofMinutes(getTime() / TimeUnitConstant.SECOND);
    }

    @Override
    public Duration toHour() {
        return Duration.ofHours(getTime() / TimeUnitConstant.SECOND / TimeUnitConstant.MINUTE);
    }

    @Override
    public Duration toDay() {
        return Duration.ofDays(getTime() / TimeUnitConstant.SECOND / TimeUnitConstant.MINUTE / TimeUnitConstant.HOUR);
    }

}
