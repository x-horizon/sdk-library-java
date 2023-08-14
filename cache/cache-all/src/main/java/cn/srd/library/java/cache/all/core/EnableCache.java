package cn.srd.library.java.cache.all.core;

import cn.srd.library.java.cache.all.support.aspect.*;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * provide an annotation to enable cache system
 *
 * @author wjm
 * @since 2023-06-09 15:06:14
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({
        CacheReadAspect.class,
        CacheReadAllAspect.class,
        CacheWriteAspect.class,
        CacheWriteBatchAspect.class,
        CacheEvictAspect.class,
        CachingAspect.class,
})
public @interface EnableCache {

}
