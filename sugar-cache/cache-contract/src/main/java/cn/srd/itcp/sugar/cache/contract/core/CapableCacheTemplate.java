package cn.srd.itcp.sugar.cache.contract.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

import java.util.List;

/**
 * 缓存模板（更多缓存操作）
 *
 * @author wjm
 * @since 2023-06-05 16:41:28
 */
public interface CapableCacheTemplate extends CacheTemplate {

    /**
     * 若旧缓存为 <code>expectedValue</code>，则将其更新为 <code>updateValue</code>，否则不进行操作
     *
     * @param key           缓存 key 名
     * @param expectedValue 期望值
     * @param updateValue   更新至
     * @param <T>           要缓存对象的类型
     * @return true 代表设置成功，false 代表未设置
     */
    @CanIgnoreReturnValue
    <T> boolean compareAndSet(String key, T expectedValue, T updateValue);

    /**
     * 获取指定命名空间下的所有缓存对象
     *
     * @param namespace 命名空间
     * @param <T>       缓存对象的类型
     * @return 缓存对象
     */
    <T> List<T> getByNamespace(String namespace);

    /**
     * 模糊查询缓存
     *
     * @param pattern key 表达式，如 cache:*
     * @param <T>     缓存对象的类型
     * @return 缓存对象
     */
    <T> List<T> getByPattern(String pattern);

    /**
     * 删除指定命名空间下的所有缓存对象
     *
     * @param namespace 命名空间
     * @return 受影响个数
     */
    @CanIgnoreReturnValue
    int deleteByNamespace(String namespace);

    /**
     * 删除缓存对象
     *
     * @param pattern key 表达式，如 cache:*
     * @return 受影响个数
     */
    @CanIgnoreReturnValue
    int deleteByPattern(String pattern);

}
