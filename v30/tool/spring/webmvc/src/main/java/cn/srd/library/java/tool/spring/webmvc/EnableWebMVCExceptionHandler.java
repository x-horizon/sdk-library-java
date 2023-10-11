package cn.srd.library.java.tool.spring.webmvc;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author wjm
 * @since 2022-07-16 18:16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(WebMVCExceptionHandler.class)
public @interface EnableWebMVCExceptionHandler {

}
