package cn.srd.library.java.tool.spring.webmvc;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author wjm
 * @since 2023-10-07 15:23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(GlobalControllerAdvicePackagePathReplacer.class)
public @interface EnableGlobalControllerAdvicePackagePathReplacer {

    String[] value();

}
