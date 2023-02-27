package cn.srd.itcp.sugar.security.sa.token.webflux.core;

import cn.srd.itcp.sugar.security.sa.token.webflux.support.SaTokenWebReactorExceptionHandler;
import org.springframework.context.annotation.Import;

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
@Import(SaTokenWebReactorExceptionHandler.class)
public @interface EnableSaTokenWebReactorExceptionHandler {

}
