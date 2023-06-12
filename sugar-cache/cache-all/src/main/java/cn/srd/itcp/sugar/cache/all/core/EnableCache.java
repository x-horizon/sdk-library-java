package cn.srd.itcp.sugar.cache.all.core;

import cn.srd.itcp.sugar.cache.all.support.aspect.CacheEvictAspect;
import cn.srd.itcp.sugar.cache.all.support.aspect.CacheReadAspect;
import cn.srd.itcp.sugar.cache.all.support.aspect.CacheWriteAspect;
import cn.srd.itcp.sugar.cache.all.support.aspect.CachingAspect;
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
@Import({CacheReadAspect.class, CacheWriteAspect.class, CacheEvictAspect.class, CachingAspect.class})
public @interface EnableCache {

}
