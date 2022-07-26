package cn.srd.itcp.sugar.tools.web;

import java.lang.annotation.*;

/**
 * 启用拦截 {@link WebExceptionHandler} 的响应结果通知
 *
 * @author wjm
 * @see WebExceptionHandler
 * @see WebResponseAdvice
 * @since 2022-07-16 18:16:22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface EnableWebResponseAdvice {

}
