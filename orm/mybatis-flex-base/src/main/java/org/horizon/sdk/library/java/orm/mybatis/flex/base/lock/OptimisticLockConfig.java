package org.horizon.sdk.library.java.orm.mybatis.flex.base.lock;

import com.mybatisflex.annotation.Column;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * the global optimistic lock config
 *
 * @author wjm
 * @since 2023-11-12 21:06
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface OptimisticLockConfig {

    /**
     * <p>the optimistic lock column name.</p>
     *
     * <p>can replace {@link Column#version()} field annotation. example:</p>
     * <pre>{@code
     * @Column(value = "version", version = true)
     * private Long version;
     * }</pre>
     * <p>this configuration is equivalent to setting this field value to "version".</p>
     *
     * @return the optimistic lock column name
     */
    String columnName() default "";

}