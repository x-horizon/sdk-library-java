package cn.srd.library.security.sa.token.spring.webmvc.core;

import cn.srd.library.security.sa.token.spring.webmvc.support.SaTokenWebMVCExceptionHandler;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用 {@link SaTokenWebMVCExceptionHandler} 的功能
 *
 * @author wjm
 * @since 2022-07-16 18:16:22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(SaTokenWebMVCExceptionHandler.class)
public @interface EnableSaTokenWebMVCExceptionHandler {

}
