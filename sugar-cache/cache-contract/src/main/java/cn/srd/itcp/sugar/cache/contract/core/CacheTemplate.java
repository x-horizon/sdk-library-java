package cn.srd.itcp.sugar.cache.contract.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 缓存模板
 *
 * @author wjm
 * @since 2023-06-05 16:41:28
 */
public interface CacheTemplate {

    /**
     * 设置缓存
     *
     * @param key   缓存 key 名
     * @param value 要缓存的对象
     * @param <T>   要缓存对象的类型
     */
    <T> void set(String key, T value);

    /**
     * 如果已存在缓存，将其覆盖，如果不存在缓存，不进行设置
     *
     * @param key   缓存 key 名
     * @param value 要缓存的对象
     * @param <T>   要缓存对象的类型
     * @return true 代表设置成功，false 代表未设置
     */
    @CanIgnoreReturnValue
    <T> boolean setIfExists(String key, T value);

    /**
     * 如果不存在缓存，则设置进去，否则不进行设置
     *
     * @param key   缓存 key 名
     * @param value 要缓存的对象
     * @param <T>   要缓存对象的类型
     * @return true 代表设置成功，false 代表未设置
     */
    @CanIgnoreReturnValue
    <T> boolean setIfAbsent(String key, T value);

    /**
     * 获取缓存
     *
     * @param key 缓存 key 名
     * @return 缓存对象
     */
    Object get(String key);

    /**
     * 获取缓存
     *
     * @param key   缓存 key 名
     * @param clazz 缓存对象的类对象
     * @param <T>   缓存对象的类型
     * @return 缓存对象
     */
    default <T> T get(String key, Class<T> clazz) {
        return clazz.cast(get(key));
    }

    /**
     * 获取多个缓存
     *
     * @param keys 缓存 key 名
     * @param <T>  缓存对象的类型
     * @return 缓存对象
     */
    <T> List<T> get(String... keys);

    /**
     * 获取多个缓存
     *
     * @param keys 缓存 key 名
     * @param <T>  缓存对象的类型
     * @return 缓存对象
     */
    <T> List<T> get(Collection<String> keys);

    /**
     * 获取多个缓存
     *
     * @param keys 缓存 key 名
     * @param <V>  缓存对象的类型
     * @return 缓存 key Mapping 缓存对象
     */
    <V> Map<String, V> getMap(String... keys);

    /**
     * 获取多个缓存
     *
     * @param keys 缓存 key 名
     * @param <V>  缓存对象的类型
     * @return 缓存 key Mapping 缓存对象
     */
    <V> Map<String, V> getMap(Collection<String> keys);

    /**
     * 获取旧的缓存对象，并将新的缓存对象设置进去
     *
     * @param key   缓存 key 名
     * @param value 要缓存的对象
     * @return 旧缓存对象
     */
    Object getAndSet(String key, Object value);

    /**
     * 获取旧的缓存对象，并将新的缓存对象设置进去
     *
     * @param key      缓存 key 名
     * @param value    要缓存的对象
     * @param oldClazz 旧缓存对象的类对象
     * @param <T>      要缓存对象的类型
     * @return 旧缓存对象
     */
    default <T> T getAndSet(String key, T value, Class<T> oldClazz) {
        return oldClazz.cast(getAndSet(key, value));
    }

    /**
     * 获取缓存对象，并将其删除
     *
     * @param key 缓存 key 名
     * @return 缓存对象
     */
    Object getAndDelete(String key);

    /**
     * 获取缓存对象，并将其删除
     *
     * @param key   缓存 key 名
     * @param clazz 缓存对象的类对象
     * @param <T>   缓存对象的类型
     * @return 缓存对象
     */
    default <T> T getAndDelete(String key, Class<T> clazz) {
        return clazz.cast(getAndDelete(key));
    }

    /**
     * 删除缓存对象
     *
     * @param key 缓存 key 名
     */
    void delete(String key);

    /**
     * 删除缓存对象
     *
     * @param keys 缓存 key 名
     * @return 受影响个数
     */
    @CanIgnoreReturnValue
    int delete(String... keys);

    /**
     * 删除缓存对象
     *
     * @param keys 缓存 key 名
     * @return 受影响个数
     */
    @CanIgnoreReturnValue
    int delete(Collection<String> keys);

}
