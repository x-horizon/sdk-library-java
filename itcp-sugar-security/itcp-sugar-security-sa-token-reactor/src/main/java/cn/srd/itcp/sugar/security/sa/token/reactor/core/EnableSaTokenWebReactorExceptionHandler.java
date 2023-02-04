package cn.srd.itcp.sugar.security.sa.token.reactor.core;

import cn.srd.itcp.sugar.security.sa.token.reactor.support.SaTokenWebReactorExceptionHandler;

import java.lang.annotation.*;

/**
 * 启用 {@link SaTokenWebReactorExceptionHandler} 的功能
 *
 * @author wjm
 * @since 2023-02-04 17:49:16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface EnableSaTokenWebReactorExceptionHandler {

}
