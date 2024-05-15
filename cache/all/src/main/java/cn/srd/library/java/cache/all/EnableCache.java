package cn.srd.library.java.cache.all;

import cn.srd.library.java.cache.all.autoconfigue.CacheAutoConfigurer;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * provide an annotation to enable cache system
 *
 * @author wjm
 * @see CacheSwitcher
 * @see CacheAutoConfigurer
 * @since 2023-06-09 15:06
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CacheSwitcher.class)
public @interface EnableCache {

}