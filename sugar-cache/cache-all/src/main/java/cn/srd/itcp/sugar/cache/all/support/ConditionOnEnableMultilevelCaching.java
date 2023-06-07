package cn.srd.itcp.sugar.cache.all.support;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author wjm
 * @since 2023-06-07 16:48:52
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(EnableMultilevelCachingCondition.class)
public @interface ConditionOnEnableMultilevelCaching {
}
