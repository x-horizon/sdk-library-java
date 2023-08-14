package cn.srd.library.java.tool.spring.webflux.core;

import cn.srd.library.java.tool.spring.webflux.support.WebFluxExceptionHandler;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用 {@link WebFluxExceptionHandler} 的功能
 *
 * @author wjm
 * @since 2023-02-04 17:49:16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(WebFluxExceptionHandler.class)
public @interface EnableWebFluxExceptionHandler {

}
