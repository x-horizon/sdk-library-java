package cn.srd.itcp.sugar.cache.redisson.common.core;

import cn.srd.itcp.sugar.cache.contract.core.CapableExpirationCacheTemplate;
import cn.srd.itcp.sugar.tool.core.Objects;
import org.springframework.cache.support.NullValue;

/**
 * Redisson 缓存模板
 *
 * @author wjm
 * @since 2023-06-05 16:41:28
 */
public interface RedissonCacheTemplate extends CapableExpirationCacheTemplate {

    /**
     * 是否为 {@link NullValue}
     *
     * @param input 输入对象
     * @return 是否为 {@link NullValue}
     */
    default boolean isNullValue(Object input) {
        return Objects.isNotNull(input) && Objects.equals(NullValue.class, input.getClass());
    }

    /**
     * 是否不为 {@link NullValue}
     *
     * @param input 输入对象
     * @return 是否不为 {@link NullValue}
     */
    default boolean isNotNullValue(Object input) {
        return !isNullValue(input);
    }

    /**
     * 定义 {@link NullValue} 的转换方式
     *
     * @param input 输入对象
     * @return 根据是否为 {@link NullValue} 进行转换
     */
    default Object convertWithNullValue(Object input) {
        return isNullValue(input) ? null : input;
    }

}
