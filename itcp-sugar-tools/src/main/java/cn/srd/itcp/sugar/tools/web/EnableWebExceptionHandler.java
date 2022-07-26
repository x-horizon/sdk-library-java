package cn.srd.itcp.sugar.tools.web;

import java.lang.annotation.*;

/**
 * 启用 {@link WebExceptionHandler} 的功能
 *
 * @author wjm
 * @since 2022-07-16 18:16:22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface EnableWebExceptionHandler {

}
