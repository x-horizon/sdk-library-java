package cn.srd.itcp.sugar.tool.core.time;

import cn.srd.itcp.sugar.tool.constant.TimeUnitPool;
import cn.srd.itcp.sugar.tool.core.StringsUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;

/**
 * 时间单位处理器
 *
 * @author wjm
 * @since 2023-02-13 22:09:11
 */
@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@Accessors(chain = true)
public abstract class TimeUnitHandler {

    /**
     * {@link TimeUnit} 与 {@link TimeUnitHandler} 的映射
     */
    protected static final Map<TimeUnit, TimeUnitHandler> TIME_UNIT_MAPPING_HANDLER_MAP = new EnumMap<>(TimeUnit.class);

    /**
     * {@link TimeUnitPool} 与 {@link TimeUnitHandler} 的映射
     */
    protected static final Map<TimeUnitPool, TimeUnitHandler> TIME_UNIT_POOL_MAPPING_HANDLER_MAP = new EnumMap<>(TimeUnitPool.class);

    /**
     * 定义如何将 时间字符串 转换为 时间单位，如 “2s” 如何转换为 “s”
     */
    protected static final List<UnaryOperator<String>> CONVERT_TO_TIME_UNIT_FUNCTIONS = new ArrayList<>();

    /**
     * 时间，如 “2s” 中的 “2”
     */
    private Long time;

    /**
     * 时间单位，如 “2s” 中的 “s”
     */
    private String timeUnit;

    static {
        establishTimeUnitMapping();
        establishHandlerMapping();
        initConvertToTimeUnitFunction();
    }

    /**
     * 建立 {@link TimeUnit} 与 {@link TimeUnitHandler} 的映射
     */
    private static void establishTimeUnitMapping() {
        TIME_UNIT_MAPPING_HANDLER_MAP.put(TimeUnit.NANOSECONDS, TimeUnitNanosecondHandler.INSTANCE);
        TIME_UNIT_MAPPING_HANDLER_MAP.put(TimeUnit.MICROSECONDS, TimeUnitMicrosecondHandler.INSTANCE);
        TIME_UNIT_MAPPING_HANDLER_MAP.put(TimeUnit.MILLISECONDS, TimeUnitMillisecondHandler.INSTANCE);
        TIME_UNIT_MAPPING_HANDLER_MAP.put(TimeUnit.SECONDS, TimeUnitSecondHandler.INSTANCE);
        TIME_UNIT_MAPPING_HANDLER_MAP.put(TimeUnit.MINUTES, TimeUnitMinuteHandler.INSTANCE);
        TIME_UNIT_MAPPING_HANDLER_MAP.put(TimeUnit.HOURS, TimeUnitHourHandler.INSTANCE);
        TIME_UNIT_MAPPING_HANDLER_MAP.put(TimeUnit.DAYS, TimeUnitDayHandler.INSTANCE);
    }

    /**
     * 建立 {@link TimeUnitPool} 与 {@link TimeUnitHandler} 的映射
     */
    private static void establishHandlerMapping() {
        TIME_UNIT_POOL_MAPPING_HANDLER_MAP.put(TimeUnitPool.NANOSECOND, TimeUnitNanosecondHandler.INSTANCE);
        TIME_UNIT_POOL_MAPPING_HANDLER_MAP.put(TimeUnitPool.MICROSECOND, TimeUnitMicrosecondHandler.INSTANCE);
        TIME_UNIT_POOL_MAPPING_HANDLER_MAP.put(TimeUnitPool.MILLISECOND, TimeUnitMillisecondHandler.INSTANCE);
        TIME_UNIT_POOL_MAPPING_HANDLER_MAP.put(TimeUnitPool.SECOND, TimeUnitSecondHandler.INSTANCE);
        TIME_UNIT_POOL_MAPPING_HANDLER_MAP.put(TimeUnitPool.MINUTE, TimeUnitMinuteHandler.INSTANCE);
        TIME_UNIT_POOL_MAPPING_HANDLER_MAP.put(TimeUnitPool.HOUR, TimeUnitHourHandler.INSTANCE);
        TIME_UNIT_POOL_MAPPING_HANDLER_MAP.put(TimeUnitPool.DAY, TimeUnitDayHandler.INSTANCE);
        TIME_UNIT_POOL_MAPPING_HANDLER_MAP.put(TimeUnitPool.MONTH, TimeUnitMonthHandler.INSTANCE);
        TIME_UNIT_POOL_MAPPING_HANDLER_MAP.put(TimeUnitPool.YEAR, TimeUnitYearHandler.INSTANCE);
    }

    /**
     * 初始化 “如何将 时间字符串 转换为 时间单位” 的转换逻辑
     */
    private static void initConvertToTimeUnitFunction() {
        addConvertToTimeUnitFunctionWithRemoveAllDigit();
    }

    /**
     * 使用去除 时间字符串 中的 时间 作为转换逻辑
     */
    private static void addConvertToTimeUnitFunctionWithRemoveAllDigit() {
        addConvertToTimeUnitFunction(StringsUtil::removeAllDigit);
    }

    /**
     * 提供给外部添加 时间字符串 转换为 时间单位 的处理逻辑
     *
     * @param convertToTimeUnitFunctions 时间字符串 转换为 时间单位 的处理逻辑
     */
    @SafeVarargs
    public static void addConvertToTimeUnitFunction(UnaryOperator<String>... convertToTimeUnitFunctions) {
        CONVERT_TO_TIME_UNIT_FUNCTIONS.addAll(Arrays.asList(convertToTimeUnitFunctions));
    }

    /**
     * 创建实例
     *
     * @return instance
     */
    protected abstract TimeUnitHandler newInstance();

    /**
     * 转换为毫秒
     *
     * @return {@link Duration}
     */
    public abstract Duration toMillisecond();

    /**
     * 转换为秒
     *
     * @return {@link Duration}
     */
    public abstract Duration toSecond();

    /**
     * 转换为分钟
     *
     * @return {@link Duration}
     */
    public abstract Duration toMinute();

    /**
     * 转换为小时
     *
     * @return {@link Duration}
     */
    public abstract Duration toHour();

    /**
     * 转换为日
     *
     * @return {@link Duration}
     */
    public abstract Duration toDay();

}
