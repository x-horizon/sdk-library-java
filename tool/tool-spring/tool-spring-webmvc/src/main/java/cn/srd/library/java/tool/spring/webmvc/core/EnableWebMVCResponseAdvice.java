package cn.srd.library.java.tool.spring.webmvc.core;

import cn.srd.library.java.tool.spring.webmvc.support.WebMVCExceptionHandler;
import cn.srd.library.java.tool.spring.webmvc.support.WebMVCResponseAdvice;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用拦截 {@link WebMVCResponseAdvice} 的响应结果通知
 *
 * @author wjm
 * @see WebMVCExceptionHandler
 * @see WebMVCResponseAdvice
 * @since 2022-07-16 18:16:22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(WebMVCResponseAdvice.class)
public @interface EnableWebMVCResponseAdvice {

}
