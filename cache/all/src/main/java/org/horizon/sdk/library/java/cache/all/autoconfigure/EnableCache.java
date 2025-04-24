package org.horizon.sdk.library.java.cache.all.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * provide an annotation to enable cache system
 *
 * @author wjm
 * @see CacheRegistrar
 * @see CacheAutoConfigurer
 * @since 2023-06-09 15:06
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CacheRegistrar.class)
public @interface EnableCache {

}