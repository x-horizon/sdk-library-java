package org.horizon.sdk.library.java.tool.spring.contract.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author wjm
 * @since 2025-04-27 20:59
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(JvmPrinter.class)
public @interface EnableJvmPrinter {

}