package cn.srd.library.java.tool.spring.webmvc.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * enable webmvc response body advice
 *
 * @author wjm
 * @see WebMvcResponseBodyAdviceRegistrar
 * @see WebMvcAutoConfigurer#webMVCResponseBodyAdvice()
 * @since 2020-07-16 18:16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(WebMvcResponseBodyAdviceRegistrar.class)
public @interface EnableWebMvcResponseBodyAdvice {

    String[] advicePackagePaths() default {};

}