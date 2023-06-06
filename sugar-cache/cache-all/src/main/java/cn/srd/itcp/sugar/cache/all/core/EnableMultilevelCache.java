package cn.srd.itcp.sugar.cache.all.core;

import org.springframework.cache.annotation.*;

import java.lang.annotation.*;

/**
 * enable multilevel cache, it could be effective for the following annotations:
 * <pre>
 * {@link Cacheable}
 * {@link CachePut}
 * {@link CacheEvict}
 * {@link Caching}
 * {@link CacheConfig}
 * </pre>
 *
 * @author wjm
 * @since 2023-06-06 16:14:13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface EnableMultilevelCache {

}
