package cn.srd.itcp.sugar.spring.tool.web.mvc.core.web;

import java.lang.annotation.*;

/**
 * 启用拦截 {@link WebMVCExceptionHandler} 的响应结果通知
 *
 * @author wjm
 * @see WebMVCExceptionHandler
 * @see WebMVCResponseAdvice
 * @since 2022-07-16 18:16:22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface EnableWebMVCResponseAdvice {

}
