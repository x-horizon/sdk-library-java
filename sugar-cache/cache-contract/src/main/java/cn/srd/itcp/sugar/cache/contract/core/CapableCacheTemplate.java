package cn.srd.itcp.sugar.cache.contract.core;

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
    <T> boolean compareAndSet(String key, T expectedValue, T updateValue);

}
