package cn.srd.itcp.sugar.cache.all.core;

import cn.srd.itcp.sugar.cache.all.support.CacheEvictAspect;
import cn.srd.itcp.sugar.cache.all.support.CacheReadAspect;
import cn.srd.itcp.sugar.cache.all.support.CacheWriteAspect;
import cn.srd.itcp.sugar.cache.all.support.CachingAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author wjm
 * @since 2023-06-09 15:06:14
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({CacheReadAspect.class, CacheWriteAspect.class, CacheEvictAspect.class, CachingAspect.class})
public @interface EnableCache {

}
