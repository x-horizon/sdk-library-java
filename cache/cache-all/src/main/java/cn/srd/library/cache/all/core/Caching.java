package cn.srd.library.cache.all.core;

import java.lang.annotation.*;

/**
 * annotation can combine for following to use:
 * <pre>
 * {@link CacheRead}
 * {@link CacheWrite}
 * {@link CacheEvict}
 * </pre>
 *
 * @author wjm
 * @since 2023-06-09 15:06:14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Caching {

    /**
     * see {@link CacheRead}
     *
     * @return see {@link CacheRead}
     */
    CacheRead[] read() default {};

    /**
     * see {@link CacheWrite}
     *
     * @return see {@link CacheWrite}
     */
    CacheWrite[] write() default {};

    /**
     * see {@link CacheEvict}
     *
     * @return see {@link CacheEvict}
     */
    CacheEvict[] evict() default {};

}
