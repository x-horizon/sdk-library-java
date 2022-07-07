package cn.srd.itcp.sugar.security.sa.token.core;

import cn.dev33.satoken.annotation.SaCheckLogin;

import java.lang.annotation.*;

/**
 * 登录认证：只有登录后才能进入该方法；
 * <pre>
 *   可标记在函数上；
 *   可标记在类上，效果等同于标注在此类的所有方法上）；
 * </pre>
 *
 * @author wjm
 * @date 2022-07-07
 */
@SaCheckLogin(type = SaTokens.USER_TYPE)
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SaTokenLogin {

}
