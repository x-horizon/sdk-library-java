package cn.srd.library.java.tool.spring.webmvc.core;

import cn.srd.library.java.tool.spring.webmvc.support.SpringWebMVCExceptionHandler;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用 {@link SpringWebMVCExceptionHandler} 的功能
 *
 * @author wjm
 * @since 2022-07-16 18:16:22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(SpringWebMVCExceptionHandler.class)
public @interface EnableSpringWebMVCExceptionHandler {

}
