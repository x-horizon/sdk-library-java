// package cn.srd.itcp.sugar.cache.all.core;
//
// import cn.srd.itcp.sugar.cache.all.support.manager.CacheType;
// import org.springframework.cache.annotation.CacheEvict;
// import org.springframework.cache.annotation.CachePut;
// import org.springframework.cache.annotation.Caching;
// import org.springframework.cache.annotation.EnableCaching;
// import org.springframework.cache.support.NullValue;
// import org.springframework.context.annotation.Import;
//
// import java.lang.annotation.*;
//
// /**
//  * enable multilevel cache, it could be effective for the following annotations:
//  * <pre>
//  * {@link CacheRead}
//  * {@link CachePut}
//  * {@link CacheEvict}
//  * {@link Caching}
//  * {@link CacheConfig}
//  * </pre>
//  *
//  * @author wjm
//  * @since 2023-06-07 16:48:52
//  */
// @Retention(RetentionPolicy.RUNTIME)
// @Target(ElementType.TYPE)
// @Documented
// @Import(EnableCaching.class)
// @EnableCaching
// public @interface EnableMultilevelCaching {
//
//     /**
//      * <pre>
//      * specify multilevel cache type, it will cache in asc order, example:
//      *
//      * define @{@link EnableMultilevelCaching}(cacheType = {CacheType.MAP, CacheType.REDIS})
//      *
//      *  when use @{@link CacheRead}:
//      *   the read order is: map -> redis -> function, once read, will not continue reading;
//      *   the write order is: redis -> map, it will write cache if function execute success;
//      *
//      *  when use @{@link CachePut}:
//      *   the write order is: redis -> map, it will write cache if function execute success;
//      *
//      *  when use @{@link CacheEvict}:
//      *   the evict order is: redis -> map, clear the cache data in redis first, and then clear the cache in map to avoid loading other requests from redis into caffeine in a short period of time;
//      * </pre>
//      *
//      * @return multilevel cache type
//      */
//     CacheType[] cacheTypes() default {CacheType.MAP};
//
//     /**
//      * if set it true, null value will be wrapped by {@link NullValue} and write to cache
//      *
//      * @return enable prevent cache penetrate
//      */
//     boolean enablePreventCachePenetrate() default false;
//
// }
