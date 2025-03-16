package org.horizon.sdk.library.java.tool.enums.autoconfigure;

import org.horizon.sdk.library.java.tool.enums.EnumAutowired;
import org.horizon.sdk.library.java.tool.enums.strategy.EnumAutowiredCollector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * enable {@link EnumAutowired} and specify the package paths to scan all enums marked with {@link EnumAutowired}
 *
 * @author wjm
 * @see EnumAutowired
 * @see EnumAutowiredRegistrar
 * @see EnumAutoConfigurer#enumAutowiredCollector()
 * @see EnumAutowiredCollector
 * @since 2021-09-08 16:07
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnumAutowiredRegistrar.class)
public @interface EnableEnumAutowired {

    /**
     * the package paths to scan all enums marked with {@link EnumAutowired}
     *
     * @return the package paths to scan all enums marked with {@link EnumAutowired}
     */
    String[] scanPackagePaths() default {};

}